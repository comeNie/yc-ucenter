package com.ai.yc.ucenter.service.atom.members.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperation;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.dao.mapper.interfaces.UcMembersMapper;
import com.ai.yc.ucenter.dao.mapper.interfaces.UcMembersOperationMapper;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.util.Global;
import com.ai.yc.ucenter.util.OperationCodeFactory;
import com.ai.yc.ucenter.util.UCDateUtils;


@Component
public class UcMembersOperationServiceAtomImpl implements IUcMembersOperationAtomService {
 

	//public static final long GET_AGAIN3 = Global.getCodeAgain(); //60秒再次获取

	public static final Integer RESULT_VALI_DIFFERENT = -1;//验证码与查到最新的验证码不符合
	public static final Integer RESULT_VALI_NOTIN= -2;//传入的验证码不存在
	public static final Integer RESULT_VALI_SUCCESS = 1;//验证成功
	public static final Integer RESULT_VALI_EXPIRED = -3; //验证码过期
	public static final Integer RESULT_UCMEMBER_ACTIVED = -4;//该账号已激活
	public static final Integer RESULT_UCMEMBER_NOTIN=-5;//该账号不存在
	
	@Override
	public String saveOperationcode(UcMembersGetOperationcodeRequest request) {
		int code = OperationCodeFactory.getInstance().getOperationCode();
		String operationCode = String.valueOf(code);

		UcMembersOperation record = new UcMembersOperation();
		BeanUtils.copyProperties(record, request);
		record.setOperationcode(operationCode);
		record.setOperationtime(String.valueOf(UCDateUtils.getSystime()));

		record.setUid(request.getUid());
		MapperFactory.getUcMembersOperationMapper().insert(record);
		return operationCode;
	}
	
	
	

	@Override
	public List<UcMembersOperation> getActiveMembe(UcMembersActiveRequest request) {
		
		UcMembersOperationCriteria example = new UcMembersOperationCriteria();
		Criteria criteria = example.createCriteria();
		
		criteria.andUidEqualTo(request.getUid());
		criteria.andOperationcodeEqualTo(request.getOperationcode());
		criteria.andOperationtypeEqualTo(request.getOperationtype());
		
		List<UcMembersOperation> list  = MapperFactory.getUcMembersOperationMapper().selectByExample(example);
		return list;
	}

	
	



	/**
	 * 激活码验证通过后，激活账号
	 */
	@Override
	public int updateActiveMember(UcMembersActiveRequest request) {
		Integer uid = request.getUid();
		String operationcode = request.getOperationcode();
		UcMembers record = new UcMembers();
		record.setEnablestatus("1");
		if(operationcode.equals(OperationtypeConstants.EMAIL_ACTIV)){
			record.setEmailcheck(1);
		}
		UcMembersCriteria example = new UcMembersCriteria();
		com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		MapperFactory.getUcMembersMapper().updateByExampleSelective(record, example);
		
		
		return MapperFactory.getUcMembersMapper().updateByExampleSelective(record, example);
	}
	
	
	private  UcMembers getUcMembers(Integer uid){
		UcMembersMapper 	ucMembersMapper = MapperFactory.getUcMembersMapper();
		return  ucMembersMapper.selectByPrimaryKey(uid);
	}
	
	public UcMembersOperation lastTimeOperation(Integer uid,String operationtype){
		UcMembersOperationMapper 	ucMembersOperationMapper = MapperFactory.getUcMembersOperationMapper();
		UcMembersOperationCriteria example = new UcMembersOperationCriteria();
		Criteria criteria = example.createCriteria();
		if(uid!=null)
		criteria.andUidEqualTo(uid);
		if(StringUtils.isNotBlank(operationtype)){
			criteria.andOperationtypeEqualTo(operationtype);
		}
		
		String lastTime = ucMembersOperationMapper.selectMaxTime(example);
		
		UcMembersOperationMapper 	operationCodeMapper = MapperFactory.getUcMembersOperationMapper();
		UcMembersOperation realOperation = new UcMembersOperation();
		realOperation.setUid(uid);
		realOperation.setOperationtime(lastTime);
		if(StringUtils.isNotBlank(operationtype)){
			
			realOperation.setOperationtype(operationtype);
		}
		

		UcMembersOperation u= operationCodeMapper.selectRealCode(realOperation);	
		return u;
	}
	   //激活码验证
@Override
 public  Integer processActivate(Integer uid , String validateCode,String operationtype,String arg){     
		//通过uid获取用户信息  
		UcMembers ucMembers = getUcMembers(uid);
		//获取最新的验证码
		UcMembersOperation realOperation = lastTimeOperation(uid,operationtype);	
		if(realOperation==null){
			return RESULT_VALI_NOTIN;
		}
		//如果传入的验证码和查到的最新验证码不符合，就返回
		if(!realOperation.getOperationcode().equals(validateCode)){		
			return RESULT_VALI_DIFFERENT;
		}		
		long reqOptime ;
		AtomicLong along ;
		if(realOperation!=null){
			reqOptime = Integer.valueOf(realOperation.getOperationtime());
	
			along = new AtomicLong();
			along.set(reqOptime);
			String mobliact = Global.getMobilActiveValid();
			String verifVa = Global.getVerifValid();
			Integer mobilActiveValid = StringUtils.isNotBlank(mobliact)?Integer.valueOf(mobliact):null;
			Integer verifValid = StringUtils.isNotBlank(verifVa)?Integer.valueOf(verifVa):null;
			
			along.getAndAdd((("activ").equals(arg))?mobilActiveValid:verifValid);
		}else{
			return RESULT_VALI_NOTIN;
		}
     //验证用户是否存在   
     if(ucMembers!=null) {    
         //验证用户激活状态    
         if(("0").equals(ucMembers.getEnablestatus()) || ("vali").equals(arg)) {   
             ///没激活  
            long sysdate = UCDateUtils.getSystime();//当前时间
//当前时间小于验证码生成时间+时间限制，则在有效期内
             if(sysdate<along.get()) {    
                 //验证激活码是否正确    
                 if(realOperation.getOperationcode().equals(validateCode)) {    
                     //激活成功， //并更新用户的激活状态，为已激活   
//                 	LOG.debug("验证码ID："+reqOperation.getOid()+"，验证码为："+reqOperation.getOperationcode()+" ，验证正确");
                 	return RESULT_VALI_SUCCESS;
                 } else {    
                 	return RESULT_VALI_DIFFERENT;
                 }    
             } else { 
             	return RESULT_VALI_EXPIRED;
             }    
         } else {  
         	return RESULT_UCMEMBER_ACTIVED;//该账号已被激活
         }    
     } else {  
     	return RESULT_UCMEMBER_NOTIN;//该账号不存在
     }    
         
 }   
}

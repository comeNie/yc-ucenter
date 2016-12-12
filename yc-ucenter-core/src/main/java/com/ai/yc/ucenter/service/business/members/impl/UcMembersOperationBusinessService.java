package com.ai.yc.ucenter.service.business.members.impl;


 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;
import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.EditMobileResultCodeConstants;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.constants.ResultCodeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.service.atom.members.impl.UcMembersOperationServiceAtomImpl;
import com.ai.yc.ucenter.service.base.UcBaseService;
import com.ai.yc.ucenter.service.business.members.IUcMembersOperationBusinessService;
import com.ai.yc.ucenter.util.UCDateUtils;
import com.ai.yc.ucenter.util.UcmembersValidators;



@Component
@Transactional
public class UcMembersOperationBusinessService extends UcBaseService implements IUcMembersOperationBusinessService {
	@Autowired
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;
	
	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;
	@Override
	public UcMembersGetOperationcodeResponse saveOperationcode(UcMembersGetOperationcodeRequest request) {
		UcMembersGetOperationcodeResponse response = new UcMembersGetOperationcodeResponse();
		
		List<String > listValidator  = beanValidator(request);
		if(listValidator != null&&!listValidator.isEmpty()){
			response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, listValidator+"", null);
			return response;
		}
		
		
		String operationtype = request.getOperationtype();
		//手机激活码、动态密码、邮箱验证码、邮箱激活连接发出后，60秒后可再次获取
//		if(!OperationtypeConstants.MOBILE_VALI.equals(operationtype)
//				|| !OperationtypeConstants.PASS_VALI.equals(operationtype)){
//			UcMembersOperation op = iUcMembersOperationAtomService.lastTimeOperation(request.getUid(), operationtype);
//			if(UCDateUtils.getSystime()>(Long.valueOf(op.getOperationtime())+60)){
//				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "手机激活码、动态密码、邮箱验证码、邮箱激活连接发出后，60秒后可再次获取", null);
//			}
//		}
		
		//手机验证码
		
		//校验：Uid只有手机/邮箱验证码和邮箱激活码用到，有值。
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype) || OperationtypeConstants.MOBILE_VALI.equals(operationtype)
				|| OperationtypeConstants.EMAIL_VALI.equals(operationtype) ){

			if(request.getUid()==null){

				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "Uid不能为空", null);
				return response;
			}
		}
		String responseUid = "";
		//1、生成手机激活码操作
		if(OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
			if(!UcmembersValidators.validateMobilephone(request.getUserinfo())){
				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号已被注册", null);
				return response;
			}
			
			//如果没有注册过，需根据手机号注册账号，账号状态为未激活，生成并返回手机激活码		
			else{
				UcMembers mobileUcmebers = new UcMembers();
				mobileUcmebers.setMobilephone(request.getUserinfo());
				mobileUcmebers.setUsername(iUcMembersAtomService.getUsername());
				mobileUcmebers.setEnablestatus("0");
				
				mobileUcmebers.setEmailcheck(0); 
				Integer regdate =(int)UCDateUtils.getSystime() ;
				mobileUcmebers.setRegdate(regdate);
				mobileUcmebers.setLastloginip("0");
				
				mobileUcmebers.setLogincount(0);
				mobileUcmebers.setModifydate(0);
				
				mobileUcmebers.setDomainName(request.getDomainname());
				mobileUcmebers.setCreatetime(regdate+"");
				mobileUcmebers.setThirduid("");
				mobileUcmebers.setUsersource("");
				mobileUcmebers.setSecques("");
				mobileUcmebers.setMyid("");
				mobileUcmebers.setMyidkey("");
				mobileUcmebers.setSystemsource("0");
				mobileUcmebers.setLogincount(0);
				mobileUcmebers.setLoginsystem("0");
				
				// --------
				mobileUcmebers.setLastlogintime(regdate);
				mobileUcmebers.setPassword("");
				mobileUcmebers.setEmail("");
				mobileUcmebers.setRegip("");
				mobileUcmebers.setSalt("");
				mobileUcmebers.setLoginmode("0");
				mobileUcmebers.setLoginway("");
				
			    responseUid = iUcMembersAtomService.insertMemberPo(mobileUcmebers);
			    if(StringUtils.isNotBlank(responseUid))
			    	request.setUid(Integer.valueOf(responseUid));
			
			}
		}//2、手机动态密码
		else if(OperationtypeConstants.DYN_PASS.equals(operationtype)){
			//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
			if(!UcmembersValidators.validateMobilephone(request.getUserinfo())){
				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该帐号已被注册", null);
				return response;
			}
		}// 密码操作验证码
		else if(OperationtypeConstants.PASS_VALI.equals(operationtype)){
			
			
			UcMembersCriteria example = new UcMembersCriteria();
			
			Criteria criteria = example.createCriteria();
			criteria.andMobilephoneEqualTo(request.getUserinfo());
			//验证激活状态下
			criteria.andEnablestatusEqualTo("1");
			List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
			
			if(list.size() > 0){
				UcMembers ucmPV = (UcMembers)list.get(0);
				request.setUid(ucmPV.getUid());
			} else {
				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号不存在", null);
				return response;
			}
			
//			if(ucmPV!=null){
//				request.setUid(ucmPV.getUid());
//			}else{
//				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号不存在", null);
//				return response;
//			}
			
		}
	
		String operationcode =  iUcMembersOperationAtomService.saveOperationcode(request);
		if(StringUtils.isNotBlank(operationcode)){
			
			
			Map<Object, Object> responseDate = new HashMap<Object, Object>();
			responseDate.put("uid", (StringUtils.isNotBlank(responseUid))?responseUid:request.getUid());
			responseDate.put("operationcode",operationcode);
			response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.SUCCESS_CODE, "成功", responseDate);
		}
		return response;
	}

	@Override
	@Transactional
	public UcMembersResponse checkActiveMembe(UcMembersActiveRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		String operationtype = request.getOperationtype();
	
		
		List<String > listValidator  = beanValidator(request);
		if(listValidator != null&&!listValidator.isEmpty()){
			response = (UcMembersResponse) addResponse(response,true,ResultCodeConstants.ERROR_CODE, listValidator+"", null);
			return response;
		}
		//判断激活码
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype)
				||OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			Integer result = processActivate(request.getUid(), request.getOperationcode(), request.getOperationtype(),"activ");
			
			if(result==UcMembersOperationServiceAtomImpl.RESULT_VALI_SUCCESS){
				int resultActive =   iUcMembersOperationAtomService.updateActiveMember(request);
				if(resultActive>0){
					
					response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.SUCCESS_CODE, "成功", null);
					return response;
				}
			}else if(result==UcMembersOperationServiceAtomImpl.RESULT_VALI_DIFFERENT 
					|| result==UcMembersOperationServiceAtomImpl.RESULT_VALI_NOTIN){
				response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.FAIL_CODE, "激活码错误", null);
				return response;
			}else if(result==UcMembersOperationServiceAtomImpl.RESULT_VALI_EXPIRED){
				response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.OVERDUE_ERROR, "验证超时，请重新发送激活码", null);
				return response;
			}
			
			
			
		}//判断验证码
		else{
				Integer result = processActivate(request.getUid(), request.getOperationcode(), request.getOperationtype(),"vali");
				if(result==UcMembersOperationServiceAtomImpl.RESULT_VALI_SUCCESS){
					response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.SUCCESS_CODE, "成功", null);
				}else if(result==UcMembersOperationServiceAtomImpl.RESULT_VALI_DIFFERENT 
						|| result==UcMembersOperationServiceAtomImpl.RESULT_VALI_NOTIN){
					response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.FAIL_CODE, "验证码错误", null);
					return response;
				}else if(result==UcMembersOperationServiceAtomImpl.RESULT_VALI_EXPIRED){
					response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.OVERDUE_ERROR, "验证超时，请重新发送验证码", null);
					return response;
				}

		}
		return response;
	}

	@Override
	public Integer processActivate(Integer uid, String validateCode, String operationtype, String string) {
		// TODO Auto-generated method stub
		return iUcMembersOperationAtomService.processActivate(uid, validateCode, operationtype, string);
	}

}

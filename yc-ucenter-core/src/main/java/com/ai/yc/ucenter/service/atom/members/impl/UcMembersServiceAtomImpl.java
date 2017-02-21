package com.ai.yc.ucenter.service.atom.members.impl;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetModeFlag;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginModeEnum;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.util.Global;
import com.ai.yc.ucenter.util.PasswordMD5Util;
import com.ai.yc.ucenter.util.UCDateUtils;
import com.alibaba.fastjson.JSON;



@Component
public class UcMembersServiceAtomImpl implements IUcMembersAtomService {
	
	private static final Log LOG = LogFactory.getLog(UcMembersServiceAtomImpl.class);

	@Override
	public List<UcMembers> loginMember(String username, String passMd5, String loginmode) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		//邮箱密码登录
		if(UcMembersLoginModeEnum.EMAILPASS_MODE.equals(loginmode)){
			criteria.andEmailEqualTo(username);
			criteria.andPasswordEqualTo(passMd5);
			criteria.andEnablestatusNotEqualTo("0");
			
		}
		//手机动态密码
		else if(UcMembersLoginModeEnum.PHONEDYNPASS_MODE.equals(loginmode)){
			
		}
		//手机密码
		else if(UcMembersLoginModeEnum.PHONEPASS_MODE.equals(loginmode)){
			criteria.andMobilephoneEqualTo(username);
			criteria.andPasswordEqualTo(passMd5);
			criteria.andEnablestatusNotEqualTo("0");
		}
		//用户名密码
		else if(UcMembersLoginModeEnum.USERPASS_MODE.equals(loginmode)){
			criteria.andUsernameEqualTo(username);
			criteria.andPasswordEqualTo(passMd5);
			criteria.andEnablestatusNotEqualTo("0");
		}		
		else if(UcMembersLoginModeEnum.ALL_MODE.equals(loginmode)){
			Criteria orUsername = example.or();
			orUsername.andUsernameEqualTo(username);
			orUsername.andPasswordEqualTo(passMd5);
			orUsername.andEnablestatusNotEqualTo("0");
			Criteria orMobile = example.or();
			orMobile.andMobilephoneEqualTo(username);
			orMobile.andPasswordEqualTo(passMd5);
			orMobile.andEnablestatusNotEqualTo("0");
			Criteria orEmail = example.or();
			orEmail.andEmailEqualTo(username);
			orEmail.andPasswordEqualTo(passMd5);
			orEmail.andEnablestatusNotEqualTo("0");
		}
//		criteria.andEnablestatusNotEqualTo("0");
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		
		return list;
	}

	@Override
	public String insertMember(UcMembersRegisterRequest request) throws Exception {
		UcMembers ucMembers =  new UcMembers();
		BeanUtils.copyProperties(ucMembers, request);
		String salt = PasswordMD5Util.creatSalt();
		ucMembers.setSalt(salt);
		ucMembers.setPassword(PasswordMD5Util.getPassSaltMd5(request.getPassword(),salt));
		ucMembers.setUsername(getUsername());
		//未激活
		ucMembers.setEnablestatus("0");
		
		//必填
		ucMembers.setEmailcheck(0); 
		Integer regdate =(int)UCDateUtils.getSystime() ;
		ucMembers.setRegdate(regdate);
		ucMembers.setLastloginip("0");
		
		ucMembers.setLogincount(1);
		ucMembers.setModifydate(0);
		
		ucMembers.setDomainName(request.getDomainname());
		ucMembers.setCreatetime(regdate+"");
		ucMembers.setThirduid("");
		ucMembers.setUsersource("");
		ucMembers.setSecques("");
		ucMembers.setMyid("");
		ucMembers.setMyidkey("");
		ucMembers.setSystemsource(request.getSystemsource());
		ucMembers.setLogincount(0);
		ucMembers.setLoginsystem("0");
		// -------
		ucMembers.setLastlogintime(regdate);
		ucMembers.setMobilephone("");
		ucMembers.setDomainName("CN");
		// mobilephone null domain_name null
		
		LOG.debug("insert ucMembers : " + JSON.toJSON(ucMembers));
		int insertCount = MapperFactory.getUcMembersMapper().insert(ucMembers);
		if(insertCount>0){
			
			Integer newId = MapperFactory.getUcMembersMapper().selectPrimaryKey(ucMembers).getUid();
		
			return String.valueOf(newId);
		}else{
			return null;
		}

	}

	/**
	 * 用邮箱或是手机注册的账号，账号的用户名则用邮箱或手机
	 * @param request
	 * @return
	 */
	public String getUsername(){
		
		//String loginway = request.getLoginway();
		ThreadLocalRandom tlr = ThreadLocalRandom.current();

		StringBuffer username = new StringBuffer(Global.getUsernamPrefix());
		Integer nameLength = (StringUtils.isBlank(Global.getUsernamLength()))?11:Integer.valueOf(Global.getUsernamLength());
		
		for(int i = 0; i < nameLength; i++){
			username.append(tlr.nextInt(0,9));  
		}
		

		
//		if(Constants.LoginWayConstant.EMAIL_PASS.equals(loginway)){
//			username.append(request.getEmail());
//		}else if(Constants.LoginWayConstant.MOBILE_DYNA.equals(loginway) || Constants.LoginWayConstant.MOBILE_PASS.equals(loginway)){
//			username.append(request.getMobilephone());
//		}else if(Constants.LoginWayConstant.USER_PASS.equals(loginway)){
//			username.append(request.getUsername());
//		}
		return username+"";
	}

	@Override
	public List<UcMembers> getMember(UcMembersGetRequest request) {
	
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
	
		String reqUsername = request.getUsername();
		//用户id获取
		if(UcMembersGetModeFlag.USERID_FLAG.equals(request.getGetmode())){
			criteria.andUidEqualTo(Integer.parseInt(reqUsername));
			criteria.andEnablestatusEqualTo("1");
		}//邮箱获取
		else if(UcMembersGetModeFlag.EMAIL_FLAG.equals(request.getGetmode())){
			criteria.andEmailEqualTo(reqUsername);
			criteria.andEnablestatusEqualTo("1");
		}//手机获取
		else if(UcMembersGetModeFlag.MOBILE_FLAG.equals(request.getGetmode())){
			criteria.andMobilephoneEqualTo(reqUsername);
			criteria.andEnablestatusEqualTo("1");
		}//用户名获取
		else if(UcMembersGetModeFlag.USERNAME_FLAG.equals(request.getGetmode())){
			criteria.andUsernameEqualTo(reqUsername);
			criteria.andEnablestatusEqualTo("1");
			//用户名或邮箱或手机获取
		}else if(UcMembersGetModeFlag.UME_FLAG.equals(request.getGetmode())){
			Criteria orUsername = example.or();
			orUsername.andUsernameEqualTo(request.getUsername());
			orUsername.andEnablestatusEqualTo("1");
			Criteria orMobile = example.or();
			orMobile.andMobilephoneEqualTo(request.getUsername());
			orMobile.andEnablestatusEqualTo("1");
			Criteria orEmail = example.or();
			orEmail.andEmailEqualTo(request.getUsername());
			orEmail.andEnablestatusEqualTo("1");
		}else if(!StringUtils.isBlank(request.getUserId())){
			criteria.andUidEqualTo(Integer.parseInt(request.getUserId()));
		}
		List<UcMembers> list = MapperFactory.getUcMembersMapper().selectByExample(example);
	
		return list;
	}

	
	
	@Override
	public int updateMobilephone(UcMembersEditMobileRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(request.getUid());
		UcMembers ucMembers = new UcMembers();
		ucMembers.setMobilephone(request.getMobilephone());
		ucMembers.setDomainName(request.getDomainname());
		return MapperFactory.getUcMembersMapper().updateByExampleSelective(ucMembers, example);
	}

	@Override
	public int updateEmail(UcMembersEditEmailRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(request.getUid());
		UcMembers ucMembers = new UcMembers();
		ucMembers.setEmail(request.getEmail());
		ucMembers.setEmailcheck(1);
		return MapperFactory.getUcMembersMapper().updateByExampleSelective(ucMembers, example);

	}

	@Override
	public int updatePassword(UcMembers ucMembers) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(ucMembers.getUid());
		return MapperFactory.getUcMembersMapper().updateByExampleSelective(ucMembers, example);
		
	}

	@Override
	public int checkEmail(UcMembersCheckEmailRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(request.getEmail());
		
		criteria.andEnablestatusEqualTo("1");
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		return list.size();
	}

	@Override
	public int checkMobilephone(UcMembersCheckeMobileRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andMobilephoneEqualTo(request.getMobilephone());
		criteria.andEnablestatusEqualTo("1");
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		return list.size();
	}

	

	@Override
	@Transactional
	public UcMembers getSalt(UcMembersLoginRequest request){
		
		String reqUsername = request.getUsername();
		String loginmode = request.getLoginmode();
		//邮箱密码登录
		if(UcMembersLoginModeEnum.EMAILPASS_MODE.equals(loginmode)){
			UcMembers record = new UcMembers();
			record.setEmail(reqUsername);
			UcMembers obj = MapperFactory.getUcMembersMapper().selectSalt(record);

			return obj;
		}
		//手机动态密码
		else if(UcMembersLoginModeEnum.PHONEDYNPASS_MODE.equals(loginmode)){
			return null;
		}
		//手机密码
		else if(UcMembersLoginModeEnum.PHONEPASS_MODE.equals(loginmode)){
			UcMembers record = new UcMembers();
			record.setMobilephone(reqUsername);
			UcMembers obj = MapperFactory.getUcMembersMapper().selectSalt(record);

			return obj;
		}
		//用户名密码
		else if(UcMembersLoginModeEnum.USERPASS_MODE.equals(loginmode)){
			UcMembers record = new UcMembers();
			record.setUsername(reqUsername);
			UcMembers obj = MapperFactory.getUcMembersMapper().selectSalt(record);
			return obj;
		}
		else if(UcMembersLoginModeEnum.ALL_MODE.equals(loginmode)){
			UcMembersCriteria example = new UcMembersCriteria();
			Criteria criteria = example.createCriteria();
			Criteria orUsername = example.or();
			orUsername.andUsernameEqualTo(request.getUsername());
			orUsername.andEnablestatusEqualTo("1");
			Criteria orMobile = example.or();
			orMobile.andMobilephoneEqualTo(request.getUsername());
			orMobile.andEnablestatusEqualTo("1");
			Criteria orEmail = example.or();
			orEmail.andEmailEqualTo(request.getUsername());
			orEmail.andEnablestatusEqualTo("1");
			List<UcMembers> list = MapperFactory.getUcMembersMapper().selectByExample(example);
			
			if(list.size() > 0)
			{
				return list.get(0);
			}
			else
			{
				return null;
			}
		}
		return null;
	
	}

	@Override
	public UcMembers getUcMembersbyUid(Integer uid) {
		
		return MapperFactory.getUcMembersMapper().selectByPrimaryKey(uid);
	}

	@Override
	public String insertMemberPo(UcMembers ucMembers) {
		LOG.debug("insert ucMembers : " + JSON.toJSON(ucMembers));
		int insertCount =  MapperFactory.getUcMembersMapper().insert(ucMembers);
		if(insertCount>0){
			Integer newId = MapperFactory.getUcMembersMapper().selectPrimaryKey(ucMembers).getUid();	
			return newId+"";
		}else{
			return "";
		}
	}

	@Override
	public Integer updateUserName(Integer uid, String username) {
		// 如果数据库中的username、email、mobilephone有等于入参username的话
		UcMembersCriteria exampleX = new UcMembersCriteria();
		Criteria criteriaX = exampleX.createCriteria();
		Criteria orUsernameX = exampleX.or();
		orUsernameX.andUsernameEqualTo(username);
		orUsernameX.andEnablestatusEqualTo("1");
		Criteria orMobileX = exampleX.or();
		orMobileX.andMobilephoneEqualTo(username);
		orMobileX.andEnablestatusEqualTo("1");
		Criteria orEmailX = exampleX.or();
		orEmailX.andEmailEqualTo(username);
		orEmailX.andEnablestatusEqualTo("1");
		List<UcMembers> list = MapperFactory.getUcMembersMapper().selectByExample(exampleX);
		if(list.size() > 0)
		{
			return 0;
		}
		
		UcMembers record = new UcMembers();
		record.setUsername(username);
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		return MapperFactory.getUcMembersMapper().updateByExampleSelective(record, example);
		
	}

	@Override
	public List<UcMembers> getMemberByUsername(String username) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andEnablestatusEqualTo("1");
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		return list;
	}

	@Override
	public int delMember(Integer uid) {
		
		return MapperFactory.getUcMembersMapper().deleteByPrimaryKey(uid);
	}


	

//public static void main(String[] args) {
//	ThreadLocalRandom tlr = ThreadLocalRandom.current();
//	StringBuffer nameRandom = new StringBuffer("yiyu");
//	for(int i = 0; i < 16; i++){
//		nameRandom.append(tlr.nextInt(0,9));  
//	}
//	System.out.println(nameRandom.toString());
//}
}

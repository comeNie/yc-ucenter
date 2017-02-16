package com.ai.yc.ucenter.api.ucpubilc.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.del.UcMembersDelRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.editusername.UcMembersEditUserNameRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcDelMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPasswordResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditUserNameResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterResp;

/**
 * Ucenter用户查询服务<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author houbin
 */
@Path("/ucpublic")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IUcPublicSV {

    /**
     * 登录验证
     * <p/>
     *   
     *
     * @param UcMembersLoginRequest
     * @return UcMembersLoginResponse
     * @throws BusinessException,SystemException
     * @author houbin
     * @ApiDocMethod
     * @ApiCode UCPUB_0001
     * @RestRelativeURL ucpublic/ucLoginMember
	 */
	@POST
	@Path("/ucLoginMember")
	PubResponse<UcLoginMemberResp> ucLoginMember(UcMembersLoginRequest request) throws BusinessException,SystemException;


	   /**
  * 注册接口
  * <p/>
  *
  *
  * @param UcMembersRegisterRequest
  * @return UcMembersLoginResponse
  * @throws BusinessException,SystemException
  * @author houbin
  * @ApiDocMethod
  * @ApiCode UCPUB_0002
  * @RestRelativeURL ucpublic/ucRegisterMember
	 */
	@POST
	@Path("/ucRegisterMember")
	PubResponse<UcRegisterResp> ucRegisterMember(UcMembersRegisterRequest request) throws BusinessException,SystemException;
	   /**
  * 获取用户信息
  * <p/>
  *
  *
  * @param condition
  * @return
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0003
  * @RestRelativeURL ucpublic/ucGetMember
	 */
	@POST
	@Path("/ucGetMember")
	PubResponse<UcGetMemberResp> ucGetMember(UcMembersGetRequest request) throws BusinessException,SystemException;
	   /**
  * 修改/绑定手机
  * <p/>
  *
  *
  * @param condition
  * @return
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0004
  * @RestRelativeURL ucpublic/ucEditMobilephone
	 */
	@POST
	@Path("/ucEditMobilephone")
	PubResponse<UcEditMobilephoneResp> ucEditMobilephone(UcMembersEditMobileRequest request) throws BusinessException,SystemException;
	   /**
  * 修改/绑定邮箱
  * <p/>
  *
  *
  * @param condition
  * @return
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0005
  * @RestRelativeURL ucpublic/ucEditEmail
	 */
	@POST
	@Path("/ucEditEmail")
	PubResponse<UcEditEmailResp> ucEditEmail(UcMembersEditEmailRequest request) throws BusinessException,SystemException;
	
	/**
  * 修改用户名
  * <p/>
  * 
  * @param UcMembersEditUserNameRequest 
  * @return UcMembersResponse
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0011
  * @RestRelativeURL ucpublic/ucEditUserName
	 */
	@POST
	@Path("/ucEditUserName")
	PubResponse<UcEditUserNameResp> ucEditUserName(UcMembersEditUserNameRequest request) throws BusinessException,SystemException;
	
	
	/**
  * 修改用户密码
  * <p/>
  *
  *
  * @param condition
  * @return
  * @throws BusinessException,SystemException
  * @author houbin
  * @ApiDocMethod
  * @ApiCode UCPUB_0006
  * @RestRelativeURL ucpublic/ucEditPassword
	 */
	@POST
	@Path("/ucEditPassword")
	PubResponse<UcEditPasswordResp> ucEditPassword(UcMembersEditPassRequest request) throws BusinessException,SystemException;
	/**
  * 检查邮箱合法性
  * <p/>
  *
  *
  * @param condition
  * @return
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0007
  * @RestRelativeURL ucpublic/ucCheckeEmail
	 */
	@POST
	@Path("/ucCheckeEmail")
	PubResponse<UcCheckeEmailResp> ucCheckeEmail(UcMembersCheckEmailRequest request) throws BusinessException,SystemException;
	/**
  * 检查移动电话合法性接口
  * <p/>
  * @param uid  用户id  （修改绑定操作传值，注册不传。）
  * @param mobilephone 移动电话（必填）
  * @return  message   0：失败，1：成功
  * @return  code  1:成功，-10: 手机号格式有误，-11：手机号已经被注册  
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0008
  * @RestRelativeURL ucpublic/ucCheckeMobilephone
	 */
	@POST
	@Path("/ucCheckeMobilephone")
	PubResponse<UcCheckeMobilephoneResp> ucCheckeMobilephone(UcMembersCheckeMobileRequest request) throws BusinessException,SystemException;
	/**
  * 删除用户
  * <p/>
  * 
  * @param UcMembersDelRequest 
  * @return UcMembersResponse
  * @throws BusinessException,SystemException
  * @ApiDocMethod
  * @ApiCode UCPUB_0012
  * @RestRelativeURL ucpublic/ucDelMember
	 */
	@POST
	@Path("/ucDelMember")
	PubResponse<UcDelMemberResp> ucDelMember (UcMembersDelRequest request) throws BusinessException,SystemException;
	
}

package com.ai.yc.ucenter.api.members.interfaces;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;

/**
 * Ucenter用户查询服务<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
@Path("/ucmembers")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
public interface IUcMembersSV {

    /**
     * 登录验证
     * <p/>
     *   
     *
     * @param UcMembersLoginRequest
     * @return UcMembersLoginResponse
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0001
     * @RestRelativeURL ucmembers/ucLoginMember
	 */
	@POST
	@Path("/ucLoginMember")
	UcMembersLoginResponse ucLoginMember(UcMembersLoginRequest request) throws BusinessException,SystemException;
	
	   /**
     * 注册接口
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0002
     * @RestRelativeURL ucmembers/ucRegisterMember
	 */
	@POST
	@Path("/ucRegisterMember")
	UcMembersLoginResponse ucRegisterMember(UcMembersRegisterRequest request) throws BusinessException,SystemException;
	   /**
     * 获取用户信息
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0003
     * @RestRelativeURL ucmembers/ucGetMember
	 */
	@POST
	@Path("/ucGetMember")
	UcMembersGetResponse ucGetMember(UcMembersGetRequest request) throws BusinessException,SystemException;
	   /**
     * 修改/绑定手机
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0004
     * @RestRelativeURL ucmembers/ucEditMobilephone
	 */
	@POST
	@Path("/ucEditMobilephone")
	UcMembersResponse ucEditMobilephone(UcMembersEditMobileRequest request) throws BusinessException,SystemException;
	   /**
     * 修改/绑定邮箱
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0005
     * @RestRelativeURL ucmembers/ucEditEmail
	 */
	@POST
	@Path("/ucEditEmail")
	UcMembersResponse ucEditEmail(UcMembersEditEmailRequest request) throws BusinessException,SystemException;
	
	/**
     * 修改用户密码
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0006
     * @RestRelativeURL ucmembers/ucEditPassword
	 */
	@POST
	@Path("/ucEditPassword")
	UcMembersResponse ucEditPassword(UcMembersEditPassRequest request) throws BusinessException,SystemException;
	/**
     * 检查邮箱合法性
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0007
     * @RestRelativeURL ucmembers/ucCheckeEmail
	 */
	@POST
	@Path("/ucCheckeEmail")
	UcMembersResponse ucCheckeEmail(UcMembersCheckEmailRequest request) throws BusinessException,SystemException;
	/**
     * 检查移动电话合法性接口
     * <p/>
     *
     *
     * @param condition
     * @return
     * @throws BusinessException,SystemException
     * @ApiDocMethod
     * @ApiCode UC_0008
     * @RestRelativeURL ucmembers/ucCheckeMobilephone
	 */
	@POST
	@Path("/ucCheckeMobilephone")
	UcMembersResponse ucCheckeMobilephone(UcMembersCheckeMobileRequest request) throws BusinessException,SystemException;
	
	
   
}

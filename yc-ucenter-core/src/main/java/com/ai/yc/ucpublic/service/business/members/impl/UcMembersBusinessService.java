package com.ai.yc.ucpublic.service.business.members.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.yc.ucenter.api.members.param.UcBeanUtils;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.del.UcMembersDelRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.editusername.UcMembersEditUserNameRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetModeFlag;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse.UcMembersGetDate;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginModeEnum;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckEmailRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobileRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcDelMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobileRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPassRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPasswordResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditUserNameResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterResp;
import com.ai.yc.ucenter.constants.CheckEmailResultCodeConstants;
import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.Constants;
import com.ai.yc.ucenter.constants.EditMobileResultCodeConstants;
import com.ai.yc.ucenter.constants.EditPassResultCodeConstants;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.constants.RegResultCodeConstants;
import com.ai.yc.ucenter.constants.ResultCodeConstants;
import com.ai.yc.ucenter.constants.eunm.DelMemberResultCodeConstantsEnum;
import com.ai.yc.ucenter.constants.eunm.EditUsernameResultCodeConstantsEnum;
import com.ai.yc.ucenter.constants.eunm.MessageCodeConstantsEnum;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.service.atom.members.impl.UcMembersOperationServiceAtomImpl;
import com.ai.yc.ucenter.util.LoginValidators;
import com.ai.yc.ucenter.util.PasswordMD5Util;
import com.ai.yc.ucenter.util.UcmembersValidators;
import com.ai.yc.ucpublic.service.base.UcBaseService;
import com.ai.yc.ucpublic.service.business.members.IUcMembersBusinessService;

@Component
@Transactional
public class UcMembersBusinessService extends UcBaseService implements IUcMembersBusinessService {

	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;
	@Autowired
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;

	@Override
	public UcMembers getUcMembers(UcMembersLoginRequest request) {
		UcMembers ucMembers = iUcMembersAtomService.getSalt(request);
		return ucMembers;
	}

	/**
	 * 用户注册
	 * 修改纪录
	 * 入参修改domain_name，添加属性systemsource
	 * 需要在Atom层添加字段systemsource！！！！！！
	 * 出参与原来代码保持不变
	 */
	@Override
	public PubResponse<UcRegisterResp> insertMember(UcRegisterRequest request) {
		PubResponse<UcRegisterResp> response = new PubResponse<UcRegisterResp>();
		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, RegResultCodeConstants.FAIL_CODE,
					listValidator + "", null);
			return response;
		}
		// 验证 用户名电话邮箱必输一项
		UcMembersRegisterRequest ucMembersRegisterRequest = new UcMembersRegisterRequest();
		BeanUtils.copyProperties(ucMembersRegisterRequest, request);
		ucMembersRegisterRequest.setDomainname(request.getDomain_name());
		if (!UcmembersValidators.validateUserAccount(ucMembersRegisterRequest)) {
			response = addResponse(response, true, RegResultCodeConstants.USERNAME_ERROR,
					"用户名、电话邮箱必输一项", null);
			return response;
		}
		// 用户名已经存在
		if (StringUtils.isNotBlank(request.getUsername())
				&& !UcmembersValidators.validateUsername(request.getUsername())) {
			response = addResponse(response, true, RegResultCodeConstants.USERNAME_EXISTS,
					"用户名已存在", null);
			return response;

		}
		// 邮箱存在
		if (StringUtils.isNotBlank(request.getEmail()) && !UcmembersValidators.validateEmail(request.getEmail())) {
			response = addResponse(response, true, RegResultCodeConstants.EMAIL_REGISTERED,
					"该邮箱已注册", null);
			return response;
		}

		// 手机存在
		if (StringUtils.isNotBlank(request.getMobilephone())
				&& !UcmembersValidators.validateMobilephone(request.getMobilephone())) {
			response = addResponse(response, true, RegResultCodeConstants.MOBILE_REGISTERED,
					"该手机号码已注册", null);
			return response;
		}

		// 包含不允许注册的词语

		// 手机+密码方式手机激活码(operationcode)有值
		if (UcMembersLoginModeEnum.PHONEPASS_MODE.equals(request.getLoginmode())
				&& StringUtils.isBlank(request.getOperationcode())) {
			response = addResponse(response, true, RegResultCodeConstants.FAIL_CODE,
					"失败,手机激活码不能为空", null);
			return response;
		}
		// 注册方式

		String resultUid = "";
		try {
			resultUid = iUcMembersAtomService.insertMember(ucMembersRegisterRequest);
			if (StringUtils.isBlank(resultUid)) {

				response = addResponse(response, true, RegResultCodeConstants.FAIL_CODE,
						"失败，没有返回Uid", null);
				return response;
			}
		} catch (Exception e) {
			response = addResponse(response, true, RegResultCodeConstants.FAIL_CODE,
					"失败，保存用户信息失败", null);
			return response;
		}
		// 生成验证码并发送
		try {
			UcMembersGetOperationcodeRequest getOperaRequest = new UcMembersGetOperationcodeRequest();
			getOperaRequest.setUserinfo(getUserinfoAndOper(ucMembersRegisterRequest).get("userinfo") + "");
			getOperaRequest.setOperationtype(getUserinfoAndOper(ucMembersRegisterRequest).get("operationtype") + "");
			getOperaRequest.setUid(Integer.valueOf(resultUid));
			String code = iUcMembersOperationAtomService.saveOperationcode(getOperaRequest);

			UcRegisterResp ucRegisterResp = new UcRegisterResp();
			ucRegisterResp.setUid(resultUid);
			ucRegisterResp.setOperationcode(code);
			ucRegisterResp.setUsername(iUcMembersAtomService.getUsername());
			response = addResponse(response, true, RegResultCodeConstants.SUCCESS_CODE,
					"用户注册成功", ucRegisterResp);
		} catch (Exception e) {
			response = addResponse(response, true, RegResultCodeConstants.FAIL_CODE,
					"失败,生成验证码失败", null);
			return response;
		}
		return response;
	}
	/**
	 * 修改日志
	 * 入参新添加字段
	 * loginip（未处理，需要入库？）
	 * systemsource（未处理，需要入库？）
	 * atom需要修改！！！！！！
	 * 出参参照原来的代码方案passHav不变，修改domain_name
	 */
	@Override
	public PubResponse<UcLoginMemberResp> loginMember(UcLoginMemberRequest request) {
		PubResponse<UcLoginMemberResp> response = new PubResponse<UcLoginMemberResp>();
		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, ResultCodeConstants.ERROR_CODE,
					listValidator + "", null);
			return response;
		}

		UcMembersLoginRequest ucMembersLoginRequest = new UcMembersLoginRequest();
		BeanUtils.copyProperties(ucMembersLoginRequest, request);
		UcMembers ucMembers = getUcMembers(ucMembersLoginRequest);
		if (ucMembers == null) {
			response = addResponse(response, true, ResultCodeConstants.ERROR_USER_NOT_EXIST, "用户不存在",
					null);
			return response;
		}

		if (!LoginValidators.validateEnablestatus(ucMembers)) {
			response =  addResponse(response, true, ResultCodeConstants.ERROR_ACCOUNT_NOT_ACTIVITY, "账户未激活",
					null);
			return response;
		}

		if (StringUtils.isBlank(ucMembers.getSalt())) {
			response = addResponse(response, true, ResultCodeConstants.ERROR_ACCOUNT_OR_PASSWORD_NOT_MATCH, "帐号或密码错误",
					null);
			return response;
		}

		String passMd5 = PasswordMD5Util.getPassSaltMd5(request.getPassword(), ucMembers.getSalt());

		List<UcMembers> list = iUcMembersAtomService.loginMember(request.getUsername(), passMd5,
				request.getLoginmode());
		if (list.size() > 0) {

			UcMembers ucMembersResponse = list.get(0);
			UcLoginMemberResp ucLoginMemberResp = new UcLoginMemberResp();
			BeanUtils.copyProperties(ucLoginMemberResp, ucMembersResponse);
			ucLoginMemberResp.setDomain_name(ucMembersResponse.getDomainName());
			ucLoginMemberResp.setPassHav((StringUtils.isNotBlank(ucMembersResponse.getPassword()) ? "true" : "false"));
			response = addResponse(response, true, ResultCodeConstants.SUCCESS_CODE, "认证成功",
					ucLoginMemberResp);
		} else {
			response = addResponse(response, true, ResultCodeConstants.ERROR_ACCOUNT_OR_PASSWORD_NOT_MATCH, "帐号或密码错误",
					null);

		}
		return response;
	}


	private static Map<String, String> getUserinfoAndOper(UcMembersRegisterRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		String loginway = request.getLoginway();
		if (Constants.LoginWayConstant.EMAIL_PASS.equals(loginway)) {
			map.put("userinfo", request.getEmail());
			map.put("operationtype", OperationtypeConstants.EMAIL_ACTIV);
		} else if (Constants.LoginWayConstant.MOBILE_DYNA.equals(loginway)
				|| Constants.LoginWayConstant.MOBILE_PASS.equals(loginway)) {
			map.put("userinfo", request.getMobilephone());
			map.put("operationtype", OperationtypeConstants.MOBILE_ACTIV);
		}

		return map;
	}

	/**
	 * 修改纪录
	 * 添加参数，修改出参domain_name
	 */
	@Override
	public PubResponse<UcGetMemberResp> getMember(UcGetMemberRequest request) {
		PubResponse<UcGetMemberResp> response = new PubResponse<UcGetMemberResp>();
		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true,
					Constants.GetUcMembersResultConstants.NOT_EMPTY, listValidator + "", null);
			return response;
		}

		UcMembersGetRequest ucMembersGetRequest = new UcMembersGetRequest();
		BeanUtils.copyProperties(ucMembersGetRequest, response);
		List<UcMembers> list = iUcMembersAtomService.getMember(ucMembersGetRequest);

		if (list.size() > 0) {
			UcMembers ucMembers = list.get(0);
			// 判断账号是否未激活
			if (("0").equals(ucMembers.getEnablestatus())) {
				UcMembersGetDate ucMembersGetDate = new UcMembersGetDate();
				UcGetMemberResp ucGetMemberResp = new UcGetMemberResp();
				BeanUtils.copyProperties(ucGetMemberResp, ucMembersGetDate);
				ucGetMemberResp.setDomain_name(ucMembersGetDate.getDomainName());
				response = addResponse(response, true,
						Constants.GetUcMembersResultConstants.NO_ACTIV, "账户未激活", ucGetMemberResp);
				return response;
			}
			
			UcGetMemberResp ucGetMemberResp = new UcGetMemberResp();
			BeanUtils.copyProperties(ucGetMemberResp, ucMembers);
			ucGetMemberResp.setDomain_name(ucMembers.getDomainName());

			response = addResponse(response, true,
					Constants.GetUcMembersResultConstants.SUCCESS_CODE, "成功", ucGetMemberResp);
			return response;
		} else {
			response = addResponse(response, true,
					Constants.GetUcMembersResultConstants.FAIL_CODE, "失败，未找到该用户信息", null);
			return response;
		}

	}

	/**
	 * 修改纪录
	 * 入参修改domain_name
	 */
	@Override
	public PubResponse<UcEditMobilephoneResp> updateMobilephone(UcEditMobileRequest request) {
		PubResponse<UcEditMobilephoneResp> response = new PubResponse<UcEditMobilephoneResp>();

		if (request.getUid() == null) {
			response =  addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE,
					"Uid不能为空" + "", null);
			return response;
		}

		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE,
					listValidator + "", null);
			return response;
		}

		Integer res = iUcMembersOperationAtomService.processActivate(request.getUid(), request.getOperationcode(), "",
				"vali", request.getMobilephone());

		if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_DIFFERENT
				|| res == UcMembersOperationServiceAtomImpl.RESULT_VALI_NOTIN) {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE, "验证码错误",
					null);
			return response;
		} else if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_EXPIRED) {
			response = addResponse(response, true, EditMobileResultCodeConstants.OVERDUE_ERROR,
					"验证码过期，修改/绑定失败", null);
			return response;
		}

		// 该电话已被注册
		if (!UcmembersValidators.validateMobilephone(request.getMobilephone())) {
			response = addResponse(response, true, EditMobileResultCodeConstants.EXISTS_ERROR,
					"该电话已经被注册，修改/绑定失败", null);
			return response;
		}
		UcMembersEditMobileRequest ucMembersEditMobileRequest = new UcMembersEditMobileRequest();
		BeanUtils.copyProperties(ucMembersEditMobileRequest, request);
		ucMembersEditMobileRequest.setDomainname(request.getDomain_name());
		
		int resultCount = iUcMembersAtomService.updateMobilephone(ucMembersEditMobileRequest);
		if (resultCount > 0) {
			response = addResponse(response, true, EditMobileResultCodeConstants.SUCCESS_CODE,
					"更新成功", null);
		} else {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE, "失败",
					null);
		}
		return response;
	}

	@Override
	public PubResponse<UcEditEmailResp> updateEmail(UcEditEmailRequest request) {
		PubResponse<UcEditEmailResp> response = new PubResponse<UcEditEmailResp>();
		if (request.getUid() == null) {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE,
					"Uid不能为空" + "", null);
			return response;
		}

		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE,
					listValidator + "", null);
			return response;
		}

		// 别人已经使用过的邮箱
//		UcMembersGetRequest umgr = new UcMembersGetRequest();
//		umgr.setUsername(request.getEmail());
//		umgr.setGetmode(UcMembersGetModeFlag.EMAIL_FLAG);
		UcGetMemberRequest umgr = new UcGetMemberRequest();
		umgr.setUsername(request.getEmail());
		umgr.setGetmode(UcMembersGetModeFlag.EMAIL_FLAG);
		
		PubResponse<UcGetMemberResp> umgresp = getMember(umgr);
		if (null != umgresp) {
			if (null != umgresp.getData()) {
				if (0 < umgresp.getData().getUid()) {
					if (umgresp.getData().getUid().toString().equals(request.getUid())) {
						response = addResponse(response, true,
								EditMobileResultCodeConstants.SUCCESS_CODE, "您已经绑定此邮箱" + "", null);
						return response;
					}
					response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE,
							"邮箱已被其他用户绑定" + "", null);
					return response;
				}
			}
		}

		// 验证码过期，修改/绑定失败

		Integer res = iUcMembersOperationAtomService.processActivate(request.getUid(), request.getOperationcode(),
				OperationtypeConstants.EMAIL_VALI, "vali", request.getEmail());

		if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_DIFFERENT
				|| res == UcMembersOperationServiceAtomImpl.RESULT_VALI_NOTIN) {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE, "验证码错误",
					null);
			return response;
		} else if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_EXPIRED) {
			response = addResponse(response, true, EditMobileResultCodeConstants.OVERDUE_ERROR,
					"验证码过期，修改/绑定失败", null);
			return response;
		}

		UcMembersEditEmailRequest ucMembersEditEmailRequest = new UcMembersEditEmailRequest();
		BeanUtils.copyProperties(ucMembersEditEmailRequest, request);
		int resultCount = iUcMembersAtomService.updateEmail(ucMembersEditEmailRequest);
		if (resultCount > 0) {
			response = addResponse(response, true, EditMobileResultCodeConstants.SUCCESS_CODE, "成功",
					null);
		} else {
			response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE, "失败",
					null);
		}
		return response;
	}

	/**
	 * 修改密码
	 */
	@Override
	public PubResponse<UcEditPasswordResp> updatePassword(UcEditPassRequest request) {
		PubResponse<UcEditPasswordResp> response = new PubResponse<UcEditPasswordResp>();
		// 1、校验入参必填
		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, EditPassResultCodeConstants.FAIL_CODE,
					listValidator + "", null);
			return response;
		}

		// 2、验证方式为旧密码
		String checke_mode = request.getChecke_mode();
		if (("1").equals(checke_mode)) {

			// 2.1 验证密码是否输入正确
			UcMembers ucMembers = iUcMembersAtomService.getUcMembersbyUid(request.getUid());

			String oldpass = request.getChecke_code();
			String oldpassMD5 = PasswordMD5Util.getPassSaltMd5(oldpass, ucMembers.getSalt());

			if (!oldpassMD5.equals(ucMembers.getPassword())) {

				response = addResponse(response, true, EditPassResultCodeConstants.OLDPASS_ERROR,
						"旧密码输入有误，修改失败", null);
				return response;
			} // 2.2 封装修改密码对象
			else {
				String newpass = request.getNewpw();
				// 生成新salt
				String salt = PasswordMD5Util.creatSalt();
				String newpassMD5 = PasswordMD5Util.getPassSaltMd5(newpass, salt);
				ucMembers.setPassword(newpassMD5);
				ucMembers.setSalt(salt);
				int resultCount = iUcMembersAtomService.updatePassword(ucMembers);
				if (resultCount > 0) {
//					Map<String, Object> responseDate = new HashMap<String, Object>();
//					responseDate.put("uid", ucMembers.getUid());
//					responseDate.put("username", ucMembers.getUsername());
					response = addResponse(response, true, EditPassResultCodeConstants.SUCCESS_CODE,
							"修改成功", null);
				} else {
					response = addResponse(response, true,
							EditPassResultCodeConstants.NONERECORD_ERROR, "没有生效记录，修改失败", null);
				}
				return response;
			}

		}

		// 3、验证方式为验证码 此处验证码都是操作码生
		else if (("2").equals(checke_mode)) {

			// 这里的有可能是activ，也有可能是vali?
			@SuppressWarnings(value = { "may activ ,may vali" })
			Integer res = iUcMembersOperationAtomService.processActivate(request.getUid(), request.getChecke_code(), "",
					"activ", null);

			if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_DIFFERENT
					|| res == UcMembersOperationServiceAtomImpl.RESULT_VALI_NOTIN) {
				response = addResponse(response, true, EditMobileResultCodeConstants.FAIL_CODE,
						"手机动态码错误", null);
				return response;
			} else if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_EXPIRED) {
				response = addResponse(response, true, EditMobileResultCodeConstants.OVERDUE_ERROR,
						"手机动态码过期，修改/绑定失败", null);
				return response;
			} else if (res == UcMembersOperationServiceAtomImpl.RESULT_VALI_SUCCESS) {
				UcMembers ucMembers = iUcMembersAtomService.getUcMembersbyUid(request.getUid());
				ucMembers.setUid(request.getUid());
				String salt = PasswordMD5Util.creatSalt();
				ucMembers.setSalt(salt);
				ucMembers.setEnablestatus("1");
				ucMembers.setPassword(PasswordMD5Util.getPassSaltMd5(request.getNewpw(), salt));
				int resultCountmobile = iUcMembersAtomService.updatePassword(ucMembers);
				if (resultCountmobile > 0) {
//					Map<String, Object> responseDate = new HashMap<String, Object>();
//					responseDate.put("uid", ucMembers.getUid());
//					responseDate.put("username", ucMembers.getUsername());
					response = addResponse(response, true, EditPassResultCodeConstants.SUCCESS_CODE,
							"修改成功", null);

				} else {

					response = addResponse(response, true,
							EditPassResultCodeConstants.NONERECORD_ERROR, "没有生效记录，修改失败", null);
				}
			}

		}
		return response;

	}

	@Override
	public PubResponse<UcCheckeEmailResp> ucCheckeEmail(UcCheckEmailRequest request) {
		PubResponse<UcCheckeEmailResp> response = new PubResponse<UcCheckeEmailResp>();
		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, CheckEmailResultCodeConstants.FORMAT_ERROR,
					listValidator + "", null);
			return response;
		}

		UcMembersCheckEmailRequest UcMembersCheckEmailRequest = new UcMembersCheckEmailRequest();
		BeanUtils.copyProperties(UcMembersCheckEmailRequest, request);
		int resultCount = iUcMembersAtomService.checkEmail(UcMembersCheckEmailRequest);
		if (resultCount > 0) {
			response = addResponse(response, true, CheckEmailResultCodeConstants.EXIST_ERROR,
					"该邮箱已被注册", null);
		} else {
			response = addResponse(response, true, CheckEmailResultCodeConstants.SUCCESS_CODE, "成功",
					null);
		}
		return response;
	}

	/**
	 * 修改纪录
	 * 入参添加属性domain_name
	 */
	@Override
	public PubResponse<UcCheckeMobilephoneResp> ucCheckeMobilephone(UcCheckeMobileRequest request) {
		PubResponse<UcCheckeMobilephoneResp> response = new PubResponse<UcCheckeMobilephoneResp>();
		List<String> listValidator = beanValidator(request);
		if (listValidator != null && !listValidator.isEmpty()) {
			response = addResponse(response, true, CheckMobilResultCodeConstants.FORMAT_ERROR,
					listValidator + "", null);
			return response;
		}
		UcMembersCheckeMobileRequest ucMembersCheckeMobileRequest = new UcMembersCheckeMobileRequest();
		BeanUtils.copyProperties(ucMembersCheckeMobileRequest, request);
		int resultCount = iUcMembersAtomService.checkMobilephone(ucMembersCheckeMobileRequest);
		if (resultCount > 0) {
			response = addResponse(response, true, CheckMobilResultCodeConstants.EXIST_ERROR,
					"该手机号码已被注册", null);
		} else {
			response = addResponse(response, true, CheckMobilResultCodeConstants.SUCCESS_CODE, "成功",
					null);
		}
		return response;
	}


}

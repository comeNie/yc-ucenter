package com.ai.platform.common.test.members;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcActiveMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobileRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcCheckeMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditEmailResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobileRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditMobilephoneResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPassRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcEditPasswordResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcGetOperationcodeResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcLoginMemberResp;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterRequest;
import com.ai.yc.ucenter.api.ucpubilc.param.UcRegisterResp;
import com.ai.yc.ucenter.util.PasswordMD5Util.Md5Utils;
import com.ai.yc.ucenter.util.UCDateUtils;
import com.ai.yc.ucenter.api.ucpublic.impl.UcPublicOperationSVImpl;
import com.ai.yc.ucenter.api.ucpublic.impl.UcPublicSVImpl;
import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class PublicMembersTest {

	@Autowired
	private UcPublicSVImpl ucMembersSVImpl;

	@Autowired
	private UcPublicOperationSVImpl ucMembersOperationSVImpl;

	@Test
	public void getLogin() {
		UcLoginMemberRequest request = new UcLoginMemberRequest();
		// String username = "mico2015121001@163.com";
		// String password = "111111";
		// String username = "xyw10000@163.com";
		// String password = Md5Utils.md5("111111q");
		// String username = "12341234999";
		// String password = Md5Utils.md5("hbhb123");
//		 String username = "12341234999";
//		 String password = Md5Utils.md5("hbhb123");
		String username = "13661150004";
		String password = Md5Utils.md5("hbhb123456");
		String loginmode = "5";
		request.setLoginmode(loginmode);
		request.setPassword(password);
		request.setUsername(username);
		PubResponse<UcLoginMemberResp> response = ucMembersSVImpl.ucLoginMember(request);
		System.out.println(response.getCode() + "::" + response.getMessage());
	}

	@Test
	public void modifyPassword() {

		UcGetOperationcodeRequest request = new UcGetOperationcodeRequest();
		request.setUid(4444563);
		request.setOperationtype("2");
		request.setUserinfo("13661150004");
		request.setDomain_name("CN");
		request.setLocale(Locale.CHINA);
		request.setTenantId("yeecloud");
		PubResponse<UcGetOperationcodeResp> response = ucMembersOperationSVImpl.ucGetOperationcode(request);
		System.out.println(response);

		UcEditPassRequest b = new UcEditPassRequest();
		b.setNewpw(Md5Utils.md5("hbhb123"));
		b.setChecke_mode("2");
		b.setChecke_code(response.getData().getOperationcode());
		b.setUid(Integer.valueOf(response.getData().getUid()));
		b.setLocale(Locale.CHINA);
		b.setTenantId("yeecloud");
		PubResponse<UcEditPasswordResp> d = ucMembersSVImpl.ucEditPassword(b);
		System.out.println(JSON.toJSON(d));
		System.out.println("end");
	}

	@Test
	public void registerByPhone() {
		UcGetOperationcodeRequest ucMembersGetOperationcodeRequest = new UcGetOperationcodeRequest();
		ucMembersGetOperationcodeRequest.setUserinfo("13661154321");
		ucMembersGetOperationcodeRequest.setDomain_name("CN");
		ucMembersGetOperationcodeRequest.setOperationtype("1");
		PubResponse<UcGetOperationcodeResp> umgor = ucMembersOperationSVImpl
				.ucGetOperationcode(ucMembersGetOperationcodeRequest);

		UcEditPassRequest umepr = new UcEditPassRequest();
		umepr.setUid(Integer.valueOf(umgor.getData().getUid()));
		umepr.setChecke_code(umgor.getData().getOperationcode());
		umepr.setChecke_mode("2");
		umepr.setNewpw(Md5Utils.md5("hbhb123"));
		PubResponse<UcEditPasswordResp> umr = ucMembersSVImpl.ucEditPassword(umepr);
		System.out.println(JSON.toJSON(umr));
	}

	@Test
	public void checkMobilephone() {
		UcCheckeMobileRequest a = new UcCheckeMobileRequest();
		a.setTenantId("yeecloud");
		a.setMobilephone("18456456666");
		// a.setMobilephone("13007420476");
		// a.setMobilephone("13848832588");
		PubResponse<UcCheckeMobilephoneResp> b = ucMembersSVImpl.ucCheckeMobilephone(a);
		System.out.println(JSON.toJSON(b));
	}

	@Test
	public void updateEmail() {
		UcGetOperationcodeRequest request = new UcGetOperationcodeRequest();
		request.setOperationtype("5");
		request.setUid(4444515);
		request.setUserinfo("1031248877@123.com");
		PubResponse<UcGetOperationcodeResp> response = ucMembersOperationSVImpl.ucGetOperationcode(request);
		System.out.println(JSON.toJSON(response));

		UcEditEmailRequest umeer = new UcEditEmailRequest();
		umeer.setUid(4444515);
		umeer.setEmail("1031248877@123.com");
		umeer.setOperationcode(response.getData().getOperationcode());
		PubResponse<UcEditEmailResp> aa = ucMembersSVImpl.ucEditEmail(umeer);
		System.out.println(JSON.toJSON(aa));
	}

	@Test
	public void updatePhone() {
		UcGetOperationcodeRequest request = new UcGetOperationcodeRequest();
		request.setOperationtype("2");
		request.setUid(4444725);
		request.setUserinfo("13661151822");
		request.setDomain_name("CN");
		PubResponse<UcGetOperationcodeResp> response = ucMembersOperationSVImpl.ucGetOperationcode(request);
		System.out.println(JSON.toJSON(response));

		UcEditMobileRequest umemr = new UcEditMobileRequest();
		umemr.setUid(4444725);
		umemr.setMobilephone("13661151822");
		umemr.setOperationcode(response.getData().getOperationcode());
		umemr.setDomain_name("CN");
		PubResponse<UcEditMobilephoneResp> aa = ucMembersSVImpl.ucEditMobilephone(umemr);
		System.out.println(JSON.toJSON(aa));
	}

	@Test
	public void getReg() {
		UcRegisterRequest request = new UcRegisterRequest();
		request.setEmail("mengbo001@asiainfo.com");
		request.setPassword("111111");
		request.setRegip("127.0.0.1");
		request.setUsersource("gtcom");
		request.setLoginmode("0");
		request.setLoginway("1");
		request.setCreatetime(String.valueOf(UCDateUtils.getSystime()));
		PubResponse<UcRegisterResp> response = ucMembersSVImpl.ucRegisterMember(request);
		System.out.println(JSON.toJSON(response));
	}
	
	@Test
	public void ucActiveMember() {
		UcActiveMemberRequest request = new UcActiveMemberRequest();
		request.setUid(4444574);
		request.setOperationtype("4");
		request.setUserinfo("178070754@qq.com");
		request.setOperationcode("865948");
		PubResponse<UcActiveMemberResp> response = ucMembersOperationSVImpl.ucActiveMember(request);
		System.out.println(response);
		
	}
	
	@Test
	public void getUsernameTest() throws Exception {
		UcGetMemberRequest request = new UcGetMemberRequest();
		request.setUserId("271535");
		request.setUsername("-1");
		request.setGetmode("-1");
		PubResponse<UcGetMemberResp> responsea = ucMembersSVImpl.ucGetMember(request);
		System.out.println(JSON.toJSON(responsea));
	}
}
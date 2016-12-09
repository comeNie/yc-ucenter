package com.ai.platform.common.test.members;


import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.yc.ucenter.api.members.impl.UcMembersOperationSVImpl;
import com.ai.yc.ucenter.api.members.impl.UcMembersSVImpl;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.editusername.UcMembersEditUserNameRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;
import com.ai.yc.ucenter.util.PasswordMD5Util.Md5Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml"})
public class MembersTest {


	@Autowired
	private UcMembersSVImpl ucMembersSVImpl;

	@Autowired
	private UcMembersOperationSVImpl ucMembersOperationSVImpl;

    @Test
    public void getLogin() {
    	UcMembersLoginRequest request = new UcMembersLoginRequest();
//    	String username = "mico2015121001@163.com";
//    	String password = "111111";
//    	String username = "xyw10000@163.com";
//    	String password = Md5Utils.md5("111111q");
    	String username = "12341234999";
    	String password = Md5Utils.md5("hbhb123");
    	
    	String loginmode = "5";
    	request.setLoginmode(loginmode);
    	request.setPassword(password);
    	request.setUsername(username);
    	UcMembersLoginResponse response =  ucMembersSVImpl.ucLoginMember(request);
        System.out.println(response.getCode()+"::"+response.getMessage());
    }
    
    @Test
    public void a(){
    	
    	UcMembersGetOperationcodeRequest request = new UcMembersGetOperationcodeRequest();
    	request.setOperationtype("6");
    	request.setUserinfo("12341234999");
    	request.setDomainname("");
    	request.setLocale(Locale.CHINA);
    	request.setTenantId("yeecloud");
    	UcMembersGetOperationcodeResponse response = ucMembersOperationSVImpl.ucGetOperationcode(request);
  		System.out.println(response);
    	
    	UcMembersEditPassRequest b = new UcMembersEditPassRequest();
    	b.setNewpw(Md5Utils.md5("hbhb123"));
    	b.setChecke_mode("2");
    	b.setChecke_code(response.getDate().get("operationcode").toString());
    	b.setUid(Integer.valueOf(response.getDate().get("uid").toString()));
    	b.setLocale(Locale.CHINA);
    	b.setTenantId("yeecloud");
    	UcMembersResponse d = ucMembersSVImpl.ucEditPassword(b);
    	System.out.println("end");
    }
    
    @Test
    public void updateEmail(){
    	UcMembersGetOperationcodeRequest request = new UcMembersGetOperationcodeRequest();
    	request.setOperationtype("6");
    	request.setUid(4444476);
    	request.setUserinfo("12341234999");
    	UcMembersGetOperationcodeResponse response = ucMembersOperationSVImpl.ucGetOperationcode(request);
  		System.out.println(response);
  		
  		
    	
    	UcMembersEditEmailRequest umeer = new UcMembersEditEmailRequest();
    	umeer.setUid(100058);
    	umeer.setEmail("3109128041@qq.com");
    	umeer.setOperationcode(response.getDate().get("operationcode").toString());
    	UcMembersResponse aa = ucMembersSVImpl.ucEditEmail(umeer);
    	System.out.println(aa);
    }
    
//   @Test
//    public void getReg() {
//    	UcMembersRegisterRequest request = new UcMembersRegisterRequest();
//    	request.setEmail("mengbo@asiainfo.com");
//    	request.setPassword("111111");
//    	request.setRegip("127.0.0.1");
//    	request.setUsersource("gtcom");
//    	request.setLoginmode("0");
//    	request.setLoginway("1");
//    	request.setCreatetime(String.valueOf(UCDateUtils.getSystime()));
//    	UcMembersRegisterResponse response = ucMembersSVImpl.ucRegisterMember(request);
//    	System.out.println(response.getMessage()+"::"+response.getMessage()+"::"+response.getCode());
//    }
//	@Test
//    public void getHB(){
//		UcMembersEditMobileRequest request = new UcMembersEditMobileRequest();
//		request.setMobilephone("18613871191");
//    	request.setUid(98924);
//    	request.setOperationcode("135812");
//    	
//    	UcMembersResponse response = ucMembersSVImpl.ucEditMobilephone(request);
//  		System.out.println(response.getCode());
//    }	
	
//	   
//	@Test
//	    public void getHB(){
//	    	UcMembersGetOperationcodeRequest request = new UcMembersGetOperationcodeRequest();
//	    	request.setOperationtype("2");
//	    	request.setUserinfo("18513871191");
//	    	request.setUid(98924);
//	    	UcMembersGetOperationcodeResponse response = ucMembersOperationSVImpl.ucGetOperationcode(request);
//	  		System.out.println(response.getCode());
//	    }

//   @Test
//    public void getUcmembers() {
//    	UcMembersGetRequest request = new UcMembersGetRequest();
//    	request.setUsername("zhongyyt123@sina.com");
//    	request.setGetmode("2");
//    	UcMembersGetResponse ucMembersGetResponse = ucMembersSVImpl.ucGetMember(request);
//    	
//    	
//    	  System.out.println(ucMembersGetResponse.getMessage()+"::"+ucMembersGetResponse.getMessage()+"::"+ucMembersGetResponse.getDate());
//    }
    
/*    @Test
    public void editMobile() {
    	boolean a =OperationValidateUtils.mobileActivAndDyan(30283,"837318");
		System.out.println("ss----------"+a);
    }*/
//  @Test
//  public void checkActiveMembeTest() throws Exception {
//  	UcMembersEditPassRequest request = new UcMembersEditPassRequest();
//  	request.setUid(4444313);
//  	request.setChecke_code("00794cabe1c48dffc97d5f66c87fa6b");
//  	request.setNewpw("5f8591a3d4fe06393cad53edff27ba05");
//  	request.setChecke_mode("1");
//  	UcMembersResponse response = ucMembersSVImpl.ucEditPassword(request);
//  	System.out.println(response.getCode());
//  }
  @Test
  public void updateUsernameTest() throws Exception {
	  UcMembersEditUserNameRequest request = new UcMembersEditUserNameRequest();
  	request.setUid(98910);
  	request.setUsername("lsptest22");
  	UcMembersResponse response = ucMembersSVImpl.ucEditUserName(request);
  	System.out.println(response.getCode());
  }   
}
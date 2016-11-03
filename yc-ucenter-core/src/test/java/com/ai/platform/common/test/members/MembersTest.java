package com.ai.platform.common.test.members;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.yc.ucenter.api.members.impl.UcMembersSVImpl;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterResponse;
import com.ai.yc.ucenter.util.UCDateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class MembersTest {


	@Autowired
	private UcMembersSVImpl ucMembersSVImpl;
	
    @Test
    public void testWrite() throws Exception {
       
    }

  

/*    @Test
    public void getLogin() {
    	UcMembersLoginRequest request = new UcMembersLoginRequest();
    	String username = "mico2015121001@163.com";
    	String password = "111111";
    	String loginmode = "1";
    	request.setLoginmode(loginmode);
    	request.setPassword(password);
    	request.setUsername(username);
    	UcMembersLoginResponse response =  ucMembersSVImpl.ucLoginMember(request);
        System.out.println(response.getResponseHeader().getResultCode()+"::"+response.getResponseHeader().getResultMessage());
    }*/
    
    @Test
    public void getReg() {
    	UcMembersRegisterRequest request = new UcMembersRegisterRequest();
    	request.setEmail("mengbo@asiainfo.com");
    	request.setPassword("111111");
    	request.setRegip("127.0.0.1");
    	request.setUsersource("gtcom");
    	request.setLoginmode("0");
    	request.setLoginway("1");
    	request.setCreatetime(String.valueOf(UCDateUtils.getSystime()));
    	UcMembersRegisterResponse response = ucMembersSVImpl.ucRegisterMember(request);
    	  System.out.println(response.getResponseHeader().getResultCode()+"::"+response.getResponseHeader().getResultMessage());
    }


    
    
    
    
}
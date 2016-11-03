package com.ai.platform.common.test.members;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.yc.ucenter.api.members.impl.UcMembersSVImpl;
import com.ai.yc.ucenter.api.members.param.UcMembersVo;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class MembersTest {


	@Autowired
	private UcMembersSVImpl ucMembersSVImpl;
	
    @Test
    public void testWrite() throws Exception {
       
    }

  

    @Test
    public void getUUID() {
    	UcMembersLoginRequest request = new UcMembersLoginRequest();
    	String username = "mico2015121001@163.com";
    	String password = "111111";
    	String loginmode = "1";
    	request.setLoginmode(loginmode);
    	request.setPassword(password);
    	request.setUsername(username);
    	UcMembersLoginResponse response =  ucMembersSVImpl.ucLoginMember(request);
        System.out.println(response.getResponseHeader().getResultCode()+"::"+response.getResponseHeader().getResultMessage());
    }


    
    
    
    
}
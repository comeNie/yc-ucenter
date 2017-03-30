package com.ai.platform.common.test.members;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ai.yc.ucenter.api.members.interfaces.IYCUcMembersSV;
import com.ai.yc.ucenter.api.members.param.UcMembersInfo;
import com.ai.yc.ucenter.api.members.param.UcMembersVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/context/core-context.xml" })
public class TestQueryUcMember {
	@Autowired
	private IYCUcMembersSV YCUcMembersSV;
	@Test
	public void queryUcMember(){
		Integer userId = 271535;
		UcMembersInfo ucMembersInfo = YCUcMembersSV.queryUcMember(userId);
		System.out.println("---"+ucMembersInfo);
	}
}

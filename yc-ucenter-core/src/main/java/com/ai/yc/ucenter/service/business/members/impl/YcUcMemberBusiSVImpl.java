package com.ai.yc.ucenter.service.business.members.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.sdk.util.BeanUtils;
import com.ai.yc.ucenter.api.members.param.UcMembersInfo;
import com.ai.yc.ucenter.api.members.param.UcMembersVo;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.service.business.members.IYcUcMemberBusiSV;
@Component
@Transactional
public class YcUcMemberBusiSVImpl implements IYcUcMemberBusiSV {
	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;
	@Override
	public UcMembersInfo QueryUcMember(Integer userId) {		
		UcMembers ucMembers	= iUcMembersAtomService.getUcMembersbyUid(userId);
		UcMembersInfo ucMembersInfo = new UcMembersInfo();
		BeanUtils.copyProperties(ucMembersInfo, ucMembers);
		return ucMembersInfo;
	}

}

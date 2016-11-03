package com.ai.yc.ucenter.service.atom.members.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperation;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;


@Component
public class UcMembersOperationServiceAtomImpl implements IUcMembersOperationAtomService {

	@Override
	public String saveOperationcode(UcMembersGetOperationcodeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getActiveMembe(UcMembersActiveRequest request) {
		
		UcMembersOperationCriteria example = new UcMembersOperationCriteria();
		Criteria criteria = example.createCriteria();
		
		criteria.andUidEqualTo(request.getUid());
		criteria.andOperationcodeEqualTo(request.getOperationcode());
		criteria.andOperationtypeEqualTo(request.getOperationtype());
		
		List<UcMembersOperation> list  = MapperFactory.getUcMembersOperationMapper().selectByExample(example);
		return list.size();
	}
	
	
}

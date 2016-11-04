package com.ai.yc.ucenter.service.atom.members.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperation;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.util.OperationCodeFactory;
import com.ai.yc.ucenter.util.UCDateUtils;


@Component
public class UcMembersOperationServiceAtomImpl implements IUcMembersOperationAtomService {

	@Override
	public String saveOperationcode(UcMembersGetOperationcodeRequest request) {
		int code = OperationCodeFactory.getInstance().getOperationCode();
		String operationCode = String.valueOf(code);
		Long newId =SeqUtil.getNewId("SYS$UCMEMBERSOPERATION$ID");
	
		UcMembersOperation record = new UcMembersOperation();
		BeanUtils.copyProperties(record, request);
		record.setOperationcode(operationCode);
		record.setOperationtime(String.valueOf(UCDateUtils.getSystime()));
		record.setOid(newId.intValue());
		MapperFactory.getUcMembersOperationMapper().insert(record);
		return operationCode;
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
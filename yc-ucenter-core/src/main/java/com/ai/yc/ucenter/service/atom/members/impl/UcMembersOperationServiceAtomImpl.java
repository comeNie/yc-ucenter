package com.ai.yc.ucenter.service.atom.members.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
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
	public List<UcMembersOperation> getActiveMembe(UcMembersActiveRequest request) {
		
		UcMembersOperationCriteria example = new UcMembersOperationCriteria();
		Criteria criteria = example.createCriteria();
		
		criteria.andUidEqualTo(request.getUid());
		criteria.andOperationcodeEqualTo(request.getOperationcode());
		criteria.andOperationtypeEqualTo(request.getOperationtype());
		
		List<UcMembersOperation> list  = MapperFactory.getUcMembersOperationMapper().selectByExample(example);
		return list;
	}




	/**
	 * 激活码验证通过后，激活账号
	 */
	@Override
	public int updateActiveMember(UcMembersActiveRequest request) {
		Integer uid = request.getUid();
		String operationcode = request.getOperationcode();
		UcMembers record = new UcMembers();
		record.setEnablestatus("1");
		if(operationcode.equals(OperationtypeConstants.EMAIL_ACTIV)){
			record.setEmailcheck(1);
		}
		UcMembersCriteria example = new UcMembersCriteria();
		com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		MapperFactory.getUcMembersMapper().updateByExampleSelective(record, example);
		
		
		return MapperFactory.getUcMembersMapper().updateByExampleSelective(record, example);
	}
	
	
}

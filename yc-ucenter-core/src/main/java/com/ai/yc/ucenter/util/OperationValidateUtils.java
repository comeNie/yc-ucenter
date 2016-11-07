package com.ai.yc.ucenter.util;


import java.util.Arrays;
import java.util.List;

import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperation;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersOperationCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.dao.mapper.interfaces.UcMembersOperationMapper;

public class OperationValidateUtils {

	/**
	 * 手机激活码或动态密码一致性验证
	 * 
	 * @param operationtime
	 * @return
	 */
	public static boolean mobileActivAndDyan(Integer uid ,String operatcode){
		UcMembersOperationMapper 	ucMembersOperationMapper = MapperFactory.getUcMembersOperationMapper();
		UcMembersOperationCriteria example = new UcMembersOperationCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andOperationcodeEqualTo(operatcode);
		criteria.andUidEqualTo(uid);
		criteria.andOperationtypeIn(Arrays.asList(OperationtypeConstants.DYN_PASS,OperationtypeConstants.MOBILE_ACTIV,OperationtypeConstants.MOBILE_VALI));
		List<UcMembersOperation> list = ucMembersOperationMapper.selectByExample(example);
		if(list.size()>0){
			for(UcMembersOperation o :list){
				o = (UcMembersOperation) list.iterator();
				long operationtime = Long.valueOf(o.getOperationtime());
				if(mobileExpirytime(operationtime)){
					return true;
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * 手机激活码或动态密码30分钟有效
	 * @param operationtime
	 * @return
	 */
	public static boolean mobileExpirytime(long operationtime){
		long systime = UCDateUtils.getSystime();
		long c = systime - operationtime;
		long time=30*60*1000;//30分钟

		if(c<=time){
			return true;
		}
		return false;
	}
	/**
	 * 邮箱验证码60分钟有效
	 * @param operationtime
	 * @return
	 */
	public static boolean emailExpirytime(long operationtime){
		long systime = UCDateUtils.getSystime();
		long c = systime - operationtime;
		long time=60*60*1000;//30分钟

		if(c<=time){
			return true;
		}
		return false;
	}
	/**
	 * 邮箱验证码60分钟有效
	 * 
	 * @param operationtime
	 * @return
	 */
	public static boolean emailVali(Integer uid ,String operatcode){
		
		UcMembersOperationMapper 	ucMembersOperationMapper = MapperFactory.getUcMembersOperationMapper();
		UcMembersOperationCriteria example = new UcMembersOperationCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andOperationcodeEqualTo(operatcode);
		criteria.andUidEqualTo(uid);
		criteria.andOperationtypeIn(Arrays.asList(OperationtypeConstants.EMAIL_ACTIV,OperationtypeConstants.EMAIL_VALI));
		List<UcMembersOperation> list = ucMembersOperationMapper.selectByExample(example);
		if(list.size()>0){
			for(UcMembersOperation o :list){
				o = (UcMembersOperation) list.iterator();
				long operationtime = Long.valueOf(o.getOperationtime());
				if(emailExpirytime(operationtime)){
					return true;
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * 手机激活码、动态密码、邮箱验证码、邮箱激活连接发出后，60秒后可再次获取。
	 * 
	 * @param operationtime
	 * @return
	 */
	public static boolean gainAgain(String operationtime){
		
		
		return false;
	}
	
	public static void main(String[] args) {
		boolean a =mobileActivAndDyan(30283,"837318");
		System.out.println("ss----------"+a);
	}
}

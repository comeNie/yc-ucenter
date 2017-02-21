package com.ai.yc.ucpublic.service.base;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.yc.ucenter.api.ucpubilc.param.PubResponse;
import com.ai.yc.ucenter.util.BeanValidators;



public abstract class UcPubBaseService {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected List<String> beanValidator(Object object, Class<?>... groups) {
		List<String> list = null;
		try{
			
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			if(list.size()>1){
				list.remove(1);
			}
				
			list.add(0, "数据验证失败：");
		
			return list;
		}
			return list;
	}

	protected <T> PubResponse<T> addResponse(PubResponse<T> response,boolean isSuccess,int code,String  message,T data){
		response.setCode(code);
		response.setMessage(message);
		response.setData(data);
		return response;
	}
}

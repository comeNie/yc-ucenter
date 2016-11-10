package com.ai.yc.ucenter.api.members.param.base;

import java.io.Serializable;

public  class ResponseMessage implements Serializable{
		

	private static final long serialVersionUID = 1L;
	
		public ResponseMessage(){}

		private boolean isSuccess;
		 
		private Integer messageCode; 
		
		private String messageValue; 
		
		public ResponseMessage(boolean isSuccess,Integer messageCode,String messageValue){
			this.isSuccess = isSuccess;
			this.messageCode = messageCode;
			this.messageValue = messageValue;
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public Integer getMessageCode() {
			return messageCode;
		}

		public void setMessageCode(Integer messageCode) {
			this.messageCode = messageCode;
		}

		public String getMessageValue() {
			return messageValue;
		}

		public void setMessageValue(String messageValue) {
			this.messageValue = messageValue;
		}

		
		
		
	}
package com.ai.yc.ucenter.api.members.param.base;

import java.io.Serializable;

public  class ResponseMessage implements Serializable{
		

	private static final long serialVersionUID = 1L;
	
		public ResponseMessage(){}

		private boolean isSuccess;
		 
		private int code; 
		
		private String message; 
		
		public ResponseMessage(boolean isSuccess,int code,String message){
			this.isSuccess = isSuccess;
			this.code = code;
			this.message = message;
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		
	}
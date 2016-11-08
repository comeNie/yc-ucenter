package com.ai.yc.ucenter.api.members.param.base;

import java.io.Serializable;

public  class ResponseMessage implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private boolean isSuccess;
		 
		private String code; 
		
		private String message; 
		
		public ResponseMessage(boolean isSuccess,String code,String message){
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

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		
	}
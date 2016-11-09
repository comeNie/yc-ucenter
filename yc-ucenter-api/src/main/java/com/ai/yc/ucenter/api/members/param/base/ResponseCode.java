package com.ai.yc.ucenter.api.members.param.base;

import java.io.Serializable;

public  class ResponseCode implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public ResponseCode(){}

		private int code;  
		   
		private String message;

		public ResponseCode(int code,String message){
			this.code = code;
			this.message = message;
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
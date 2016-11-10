package com.ai.yc.ucenter.api.members.param.base;

import java.io.Serializable;

public  class ResponseCode implements Serializable{
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		public ResponseCode(){}

		private Integer codeNumber;  
		   
		private String codeMessage;

		public ResponseCode(Integer codeNumber,String codeMessage){
			this.codeNumber = codeNumber;
			this.codeMessage = codeMessage;
		}

		public Integer getCodeNumber() {
			return codeNumber;
		}

		public void setCodeNumber(Integer codeNumber) {
			this.codeNumber = codeNumber;
		}

		public String getCodeMessage() {
			return codeMessage;
		}

		public void setCodeMessage(String codeMessage) {
			this.codeMessage = codeMessage;
		}
	
		
		
	}
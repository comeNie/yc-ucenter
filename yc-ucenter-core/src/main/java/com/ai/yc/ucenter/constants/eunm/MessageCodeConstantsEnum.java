package com.ai.yc.ucenter.constants.eunm;

public enum MessageCodeConstantsEnum implements IConstantsEnum{
	
		
		MESSAGE_SUCCESS("成功",1),MESSAGE_FAIL("失败",0);
		MessageCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (MessageCodeConstantsEnum c : MessageCodeConstantsEnum .values()) {
	        if (c.getIndex() == index) {
	          return c.value;
	        }
	      }
	      return null;
	    }
		
	    public int getIndex() { 
	        return index; 
	    }

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}
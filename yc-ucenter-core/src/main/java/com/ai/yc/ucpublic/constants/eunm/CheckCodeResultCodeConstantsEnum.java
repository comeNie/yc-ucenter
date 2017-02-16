package com.ai.yc.ucpublic.constants.eunm;

public enum CheckCodeResultCodeConstantsEnum implements IConstantsEnum{
	
		
		FORMAT_ERROR("格式有误",-10),ACTIVED_NUMBER("该账号是已激活账号",2),ACTIVE_CODE_FALSE("激活码错误",-1),VERIF_CODE_FALSE("验证码不对",-14),CODE_OVERTIME("验证超时，请重新发送激活码",-13);
		CheckCodeResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (CheckCodeResultCodeConstantsEnum c : CheckCodeResultCodeConstantsEnum .values()) {
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
package com.ai.yc.ucenter.constants.eunm;

public enum CheckMobilResultCodeConstantsEnum implements IConstantsEnum{
	
		
		FORMAT_ERROR("格式有误",-10),EXIST_ERROR("该手机号码已被注册",-11);
		CheckMobilResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (CheckMobilResultCodeConstantsEnum c : CheckMobilResultCodeConstantsEnum .values()) {
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
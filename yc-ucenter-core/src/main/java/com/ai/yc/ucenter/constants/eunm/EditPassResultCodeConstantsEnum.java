package com.ai.yc.ucenter.constants.eunm;

public enum EditPassResultCodeConstantsEnum implements IConstantsEnum{
	
		
		OLDPASS_ERROR("旧密码输入有误，修改失败",-1),
		NONERECORD_ERROR("没有生效记录，修改失败",-7),
		OVERDUE_ERROR("验证码过期，修改/绑定失败",0);
		EditPassResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (EditPassResultCodeConstantsEnum c : EditPassResultCodeConstantsEnum .values()) {
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
package com.ai.yc.ucenter.constants.eunm;

public enum EditMobileResultCodeConstantsEnum implements IConstantsEnum{
	
		/**
		 * 该电话已经被注册，修改/绑定失败
		 */
		EXISTS_ERROR("该电话已经被注册，修改/绑定失败",-1),
		NONERECORD_ERROR("没有生效记录，修改失败",-7),
		OVERDUE_ERROR("验证码过期，修改/绑定失败",0),
		FAIL_CODE("验证码过期不正确，修改/绑定失败",-2)
		;
		EditMobileResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (EditMobileResultCodeConstantsEnum c : EditMobileResultCodeConstantsEnum .values()) {
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
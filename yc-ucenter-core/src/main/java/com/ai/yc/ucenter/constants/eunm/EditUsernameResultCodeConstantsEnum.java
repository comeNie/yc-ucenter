package com.ai.yc.ucenter.constants.eunm;

public enum EditUsernameResultCodeConstantsEnum implements IConstantsEnum{
	
		/**
		 * 该电话已经被注册，修改/绑定失败
		 */
		EXISTS_ERROR("该用户名已经被注册，修改失败",-1),
		UPDATE_ERROR("修改失败",-1),
		FORMAT_ERROR("传入参数验证失败",-2)
		;
		EditUsernameResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (EditUsernameResultCodeConstantsEnum c : EditUsernameResultCodeConstantsEnum .values()) {
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
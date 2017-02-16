package com.ai.yc.ucpublic.constants.eunm;

public enum CheckEmailResultCodeConstantsEnum implements IConstantsEnum{
	
		/**
		 * 验证码过期，修改/绑定失败
		 */
	CODE_EXPIRED("验证码过期，修改/绑定失败",-1),
	/**
	 * 验证超时，请重新发送激活码
	 */
	CODE_OVERTIME("验证超时，请重新发送激活码",-13),
	/**
	 * 验证码不对，请重新输入
	 */
	CODE_ERROR("验证码错误，请重新输入",-14),
	/**
	 * 还没有发送验证码，请点击发送验证码
	 */
	NOT_SEND("还没有发送验证码，请点击发送验证码",-15);
	

		CheckEmailResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (CheckEmailResultCodeConstantsEnum c : CheckEmailResultCodeConstantsEnum .values()) {
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
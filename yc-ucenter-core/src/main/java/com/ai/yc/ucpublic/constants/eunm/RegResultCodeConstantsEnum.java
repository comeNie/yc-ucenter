package com.ai.yc.ucpublic.constants.eunm;

public enum RegResultCodeConstantsEnum implements IConstantsEnum{
	
		
		USERNAME_ERROR("用户名不合法",-1),
		NOTALLOW_ERROR("包含不允许注册的词语",-2),
		USERNAME_EXISTS("用户名已存在",-3),
		EMAIL_ERROR("Email 格式有误",-4),
		EMAIL_NOTALLOW("Email 不允许注册",-5),
		EMAIL_REGISTERED("该Email 已经被注册",-6),
		MOBILE_REGISTERED("该手机已经被注册",-7),
		VER_OVERDUE("验证码过期",-7);
		RegResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (RegResultCodeConstantsEnum c : RegResultCodeConstantsEnum .values()) {
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
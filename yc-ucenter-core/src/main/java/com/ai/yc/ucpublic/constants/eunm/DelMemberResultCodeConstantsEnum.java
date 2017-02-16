package com.ai.yc.ucpublic.constants.eunm;

public enum DelMemberResultCodeConstantsEnum implements IConstantsEnum{
	
	
		FORMAT_ERROR("Uid不能为空",-2),
		DEL_ERROR("删除失败",-1);
		DelMemberResultCodeConstantsEnum(String value,int idx) { 
			this.value = value;
	        this.index = idx; 
	    } 
		private int index; 
		private String value;
	
	

		// 普通方法
	    public static String getName(int index) {
	      for (DelMemberResultCodeConstantsEnum c : DelMemberResultCodeConstantsEnum .values()) {
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
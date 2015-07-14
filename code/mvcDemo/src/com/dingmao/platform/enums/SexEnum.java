package com.dingmao.platform.enums;

/**
 * 排序枚举
 * 
 * @author Administrator
 * 
 */
public enum SexEnum {												
	FEMALE("0","男"),
	MAN("1","女");
	
	private  String  code;
	private  String  name;
	
	private SexEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

    public static String getName(String code) {
        for (SexEnum c : SexEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
    
    public static String getCode(String name) {
        for (SexEnum c : SexEnum.values()) {
            if (c.getName() == name) {
                return c.code;
            }
        }
        return null;
    }
	
	public static  void  main(String[] args ){
		System.out.println(SexEnum.getName(SexEnum.FEMALE.code));
		System.out.println(SexEnum.getCode(SexEnum.FEMALE.name));
	}
}

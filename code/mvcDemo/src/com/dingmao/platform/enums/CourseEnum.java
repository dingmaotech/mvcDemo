package com.dingmao.platform.enums;

/**
 * 排序枚举
 * 
 * @author Administrator
 * 
 */
public enum CourseEnum {												
	YW("1","语 文"),
	SX("2","数 学"),
	YY("3","英 语"),
	WL("4","物 理"),
	HX("5","化 学"),
	SW("6","生 物"),
	ZZ("7","政 治"),
	LS("8","历 史"),
	DL("9","地 理"),
	MU("10","音 乐"),
	TY("11","体 育"),
	MS("12","美 术"),
	JS("13","技 术"),
	XL("14","心 理");
	
	private  String  code;
	private  String  name;
	
	private CourseEnum(String code, String name) {
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
        for (CourseEnum c : CourseEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
    
    public static String getCode(String name) {
        for (CourseEnum c : CourseEnum.values()) {
            if (c.getName() == name) {
                return c.code;
            }
        }
        return null;
    }
	
	public static  void  main(String[] args ){
		System.out.println(CourseEnum.getName(CourseEnum.YW.code));
		System.out.println(CourseEnum.getCode(CourseEnum.YW.name));
	}
}

package com.bz.xtcx.manager.enums;

/**
 * @author zhangkj
 *
 */
public enum UserTypeEnum {

	Person(1, "游客"),
	Enterprise(2, "企业"),
	Service(3, "服务机构"),
	Expert(4, "专家"),
	College(5, "高校院所"),
	Military(6, "军方");
	
	private final int value;

    private final String theName;
    
    public int value() {
        return this.value;
    }

    public String theName() {
        return this.theName;
    }
    
	UserTypeEnum(int value, String theName) {
        this.value = value;
        this.theName = theName;
    }
	
	public static void main(String[] args) {
		System.out.println(UserTypeEnum.Person.theName);
	}
}

package com.bz.xtcx.manager.enums;

/**
 * @author zhangkj
 *
 */
public enum UserTypeEnum {

	Expert(1, "个人/专家"),
	Enterprise(2, "企业"),
	Service(3, "服务机构"),
	College(4, "高校院所"),
	Military(5, "军方"),
	Fruit(6,"成果"),
	Need(7,"需求");
	
	private final int key;

    private final String theName;
    
    public int key() {
        return this.key;
    }

    public String theName() {
        return this.theName;
    }
    
	UserTypeEnum(int key, String theName) {
        this.key = key;
        this.theName = theName;
    }
	
	public static void main(String[] args) {
		System.out.println(UserTypeEnum.Expert.theName);
	}
}

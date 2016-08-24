/** 
 * @author: gsw
 * @version: 1.0
 * @CreateTime: 2016年1月8日 上午10:17:09
 * @Description: 无
 */
package org.gongice.util.db;

public enum DBTYPE {
	// GBASE, DB2
	GBASE("GBASE", "1"), DB2("DB2", "2"), ORACLE("DB2", "2");
	// 成员变量
	private String name;
	private String code;

	// 构造方法
	private DBTYPE(String name, String code) {
		this.name = name;
		this.code = code;
	}

	// 普通方法
	public static String getName(String code) {
		for (DBTYPE m : DBTYPE.values()) {
			if (m.getCode() == code) {
				return m.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static void main(String[] args) {
		System.out.println(DBTYPE.GBASE.name);
		System.out.println(DBTYPE.GBASE.code);
	}

}

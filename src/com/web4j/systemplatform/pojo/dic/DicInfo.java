package com.web4j.systemplatform.pojo.dic;

/**
 * 字典POJO
 * @author : hepx
 * @date : 2011-3-11上午11:32:46
 * @classname : DicInfo
 * @description : TODO
 *
 */
public class DicInfo {

	private String dicGroup;
	
	private String dicCode;
	
	private String dicValue;
	
	private String dicDesc;
	
	public DicInfo(){}
	
	public DicInfo(String dicCode,String dicValue,String dicGroup){
		this.dicCode=dicCode;
		this.dicValue=dicValue;
		this.dicGroup=dicGroup;
	}
	
	public DicInfo(String dicGroup,String dicCode,String dicValue,String dicDesc){
		this.dicGroup=dicGroup;
		this.dicCode=dicCode;
		this.dicValue=dicValue;
		this.dicDesc=dicDesc;
	}

	public String getDicGroup() {
		return dicGroup;
	}

	public void setDicGroup(String dicGroup) {
		this.dicGroup = dicGroup;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public String getDicValue() {
		return dicValue;
	}

	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public String getDicDesc() {
		return dicDesc;
	}

	public void setDicDesc(String dicDesc) {
		this.dicDesc = dicDesc;
	}
	
	
}

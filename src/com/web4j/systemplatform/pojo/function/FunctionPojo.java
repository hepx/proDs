package com.web4j.systemplatform.pojo.function;


public class FunctionPojo{

	public FunctionPojo() {
		// TODO Auto-generated constructor stub
	}
	
	public FunctionPojo(Long id,Integer type,String functionName){
		this.id=id;
		this.functionName=functionName;
		this.type=type;
	}
	public FunctionPojo(Long id,Integer type){
		this.id=id;
		this.type=type;
	}
	
	private Long id;
	
	private String functionName;// 方法名称，可以有多个中间用","隔开

	private Integer type;// 操作类型：录入、查询、打印、导出等。

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}

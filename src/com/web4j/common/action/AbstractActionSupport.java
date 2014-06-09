package com.web4j.common.action;

public abstract class AbstractActionSupport{
	
	public static final String JSON = "json";
	public static final String PAGE = "page";
	
	//错误信息
	public String errorMessage;

	//分页 参数
	public Integer start;

	public Integer limit;

	public Long total;
	
	public Boolean timeout;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = "系统异常:"+(errorMessage==null?"null":errorMessage);
	}
	
	public void setErrorMessage(Exception e){
		String exceptionName = e.getClass().getSimpleName();
		//System.out.println("**************"+exceptionName);
		if("DoErrorException".equals(exceptionName)){
			this.errorMessage=e.getMessage();
		}else if("DataAccessException".equals(exceptionName)) {
			this.errorMessage="数据库操作失败！";
		}else if("NullPointerException".equals(exceptionName)){
			this.errorMessage="调用了未经初始化的对象或者是不存在的对象！";
		}else if("IOException".equals(exceptionName)) {
			this.errorMessage="IO异常！";
		}else if("ClassNotFoundException".equals(exceptionName)) {
			this.errorMessage="指定的类不存在！";
		}else if("ArithmeticException".equals(exceptionName)) {
			this.errorMessage=("数学运算异常！");
		}else if("ArrayIndexOutOfBoundsException".equals(exceptionName)) {
			this.errorMessage="数组下标越界!";
		}else if("IllegalArgumentException".equals(exceptionName)) {
			this.errorMessage="方法的参数错误！";
		}else if("ClassCastException".equals(exceptionName)) {
			this.errorMessage="类型强制转换错误！";
		}else if("SecurityException".equals(exceptionName)) {
			this.errorMessage="违背安全原则异常！";
		}else if("SQLException".equals(exceptionName)) {
			this.errorMessage="操作数据库异常！";
		}else if("NoSuchMethodError".equals(exceptionName)) {
			this.errorMessage="方法末找到异常！";
		}else if("InternalError".equals(exceptionName)) {
			this.errorMessage="Java虚拟机发生了内部错误！";
		}else if("SchedulerException".equals(exceptionName)){
			this.errorMessage="JOB任务调度出现异常！";
		}else if("DataIntegrityViolationException".equals(exceptionName)){
			this.errorMessage="数据完整性冲突异常！";
		}else{
			this.errorMessage="程序内部错误，操作失败！";
		}
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Boolean getTimeout() {
		return timeout;
	}

	public void setTimeout(Boolean timeout) {
		this.timeout = timeout;
	}

}

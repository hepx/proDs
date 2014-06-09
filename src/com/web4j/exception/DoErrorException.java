package com.web4j.exception;


/**
 * 自定义处理错误信息异常
 * @author xixi
 * @date 2012-10-27
 *
 */
public class DoErrorException extends Exception {

	private static final long serialVersionUID = 1L;
	

	public DoErrorException() {
	}

	public DoErrorException(String massegs) {
		super(massegs);
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	
}

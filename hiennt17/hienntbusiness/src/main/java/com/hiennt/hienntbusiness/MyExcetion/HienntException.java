package com.hiennt.hienntbusiness.MyExcetion;

public class HienntException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;

	public HienntException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HienntException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public HienntException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public HienntException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public HienntException(String message, String errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public HienntException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}

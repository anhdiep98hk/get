package com.hiennt.hienntbusiness.common;

public enum Errors {
	CUS_ERROR("001", "CUSTOMER ERROR"),
	CUS_ERROR_ID("002","ID NOT EXISTS"),
	CUS_ERROR_NAME("003","NAME NOT EXISTS"),
	CUS_ERROR_ADDRESS("004","ADDRESS NOT EXISTS"),
	CUS_ERROR_CITY("005","CITY NOT EXISTS"),
	CUS_ERROR_STATE("006","STATE NOT EXISTS"),
	CUS_ERROR_ZIP("007","ZIP NOT EXISTS"),
	CUS_ERROR_PHONE("008","PHONE NOT EXISTS"),
	PRO_ERROR("011","PRODUCT ERROR"),
	PRO_ERROR_ID("012","ID NOT EXISTS"),
	PRO_ERROR_NAME("013","NAME NOT EXISTS"),
	PRO_ERROR_UNITPRCE("014","UNITPRICE NOT EXISTS"),
	INV_ERROR("021","INVOICE ERROR"),
	INV_ERROR_ID("022","ID NOT EXISTS"),
	INV_ERROR_NUMBERS("023","NUMBER NOT EXIST"),
	INV_ERROR_ISSUEDATE("024","ISSUEDATE NOT EXISTS"),
	INV_ERROR_TOTALPAYMENT("025","TOTALPAYMENT NOT EXISTS"),
	INV_ERROR_ID_CUS("026","CUSID NOT EXISTS IN INVOICE ID"),
	INV_ERROR_ID_PRO("027","PROiD NOT EXISTS IN INVOICE ID");
	
	
	private final String id;
	private final String message;
	
	Errors(String id, String message) {
		this.id = id;
		this.message = message;
	}
	
	public String getId() {return id;}
	
	public String getMessage() {return message;}
}

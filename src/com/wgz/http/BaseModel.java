package com.wgz.http;

import java.io.Serializable;
//@JSONType(includes = { "version" })
public class BaseModel implements Serializable, Cloneable {
	private static final long serialVersionUID = -5301635897930731399L;

	private String version;
	private String retCode;
	private String retMsg;
	private String errMsg;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		return "BaseModel [version=" + version + ", retCode=" + retCode + ", retMsg=" + retMsg + ", errMsg=" + errMsg
				+ "]";
	}
	
}

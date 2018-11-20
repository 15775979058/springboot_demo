package net.xdclass.demo.domain;

import java.io.Serializable;

public class JsonData implements Serializable {
	private static final long serialVersionUID = 1L;

	//状态码,0表示成功，-1表示失败
	private int status;
	
	//结果
	private Object data;

	//错误描述
	private String msg;
	
	public int getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public JsonData(int status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public JsonData(int status, String msg,Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	
	
}

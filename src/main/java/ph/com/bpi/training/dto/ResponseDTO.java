package ph.com.bpi.training.dto;

import ph.com.bpi.training.controller.ResponseStatus;

public class ResponseDTO<T> {

	private ResponseStatus status;
	private String message;
	private T data; // this means that this class
	// can hold ANY "data" of type "T"
	// if we use ResponseDTO<String> res = new ResponseDTO<String>();
	// res.getData() will be type String
	
	public void setStatus(ResponseStatus status) {
		// TODO Auto-generated method stub
		this.status = status;
	}
	
	public void setData(Object data) {
		// TODO Auto-generated method stub
		this.data = (T) data;
	}
	
	public ResponseStatus getStatus() {
		return this.status;
	}
	
	public T getData() {
		return this.data;
	}

	public void setMessage(String string) {
		// TODO Auto-generated method stub
		this.message = string;
	}
	
}

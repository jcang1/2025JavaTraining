/**
 * Added this to make customize feedback in json API by adding some messages
 */
package com.example.demo.DTO;

/**
 * 
 */

public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
    
    public String getMessage() {
    	return this.message;
    }
    
    public T getData() {
    	return this.data;
    }

}


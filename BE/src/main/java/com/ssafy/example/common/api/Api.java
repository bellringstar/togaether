package com.ssafy.example.common.api;

import com.ssafy.example.common.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {
	private Result result;
	private T body;

	public static <T> Api<T> ok(T data) {
		var api = new Api<T>();
		api.result = Result.ok();
		api.body = data;
		return api;
	}

	public static Api<Object> error(Result result) {
		var api = new Api<Object>();
		api.result = result;
		return api;
	}

	public static Api<Object> error(ErrorCode errorCode) {
		var api = new Api<Object>();
		api.result = Result.error(errorCode);
		return api;
	}

	public static Api<Object> error(ErrorCode errorCode, String description) {
		var api = new Api<Object>();
		api.result = Result.error(errorCode, description);
		return api;
	}

}

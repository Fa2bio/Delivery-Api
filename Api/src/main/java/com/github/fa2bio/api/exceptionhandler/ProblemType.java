package com.github.fa2bio.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALID_DATA("/invalid-data", "Invalid data"),
	SYSTEM_ERROR("/system-error", "System error"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	MESSAGE_INCOMPREENSIBLE("/message-incompreesible", "Message incompreesible"),
	RESOURCE_NOT_FUND("/resource-not-found", "Resource not found"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	BUSINESS_ERROR("/business-error", "Business error");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://github.com/Fa2bio" + path;
		this.title = title;
	}
	
}

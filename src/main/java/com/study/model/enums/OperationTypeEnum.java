package com.study.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

public enum OperationTypeEnum {
	CREDIT("c"),
	DEBIT("d");
	
	@Getter
	@JsonValue
	private String type;
	
	OperationTypeEnum(String type) {
		this.type = type;
	}
}
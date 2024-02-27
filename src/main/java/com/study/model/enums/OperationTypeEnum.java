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

	public static OperationTypeEnum getEnumFromType(String type) {
		for (var operationType : OperationTypeEnum.values()) {
			if (operationType.getType().equals(type)) {
				return operationType;
			}
		}

		throw new IllegalArgumentException(String.format("No operation type found for type \"%s\"", type));
	}
	
	@Override
	public String toString() {
		return this.type;
	}

}
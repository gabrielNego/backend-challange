package com.study.repository.entity;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionHistory {
	private Long customerId;
	private String type;
	private Long value;
	private String description;
	private LocalDateTime date;

}

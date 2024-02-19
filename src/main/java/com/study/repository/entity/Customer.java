package com.study.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {
	@Id
	@Column(name = "id")
	private Long Id;
	@Column(name = "limits")
	private Long limit;
	@Column(name = "balance")
	private Long balance;
}

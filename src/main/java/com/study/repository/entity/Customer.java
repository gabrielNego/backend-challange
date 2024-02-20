package com.study.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	@Id
	@Column(name = "id")
	private Long Id;
	@Column(name = "limit_")
	private Long limit;
	@Column(name = "balance")
	private Long balance;
}

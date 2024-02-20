package com.study.repository.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction_history")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACTION_HISTORY_SEQ")
	private Long id;
	@Column(name = "customer_id")
	private Long customerId;
	@Column(name = "type_")
	private String type;
	@Column(name = "value_")
	private Long value;
	@Column(name = "description")
	private String description;
	@Column(name = "date_")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;

}

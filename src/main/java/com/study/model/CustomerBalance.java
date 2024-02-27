package com.study.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerBalance {
	
	private long limite;
	private long total;
	private LocalDateTime data_extrato;

	public CustomerBalance(long limite, long total) {
		super();
		this.limite = limite;
		this.total = total;
	}

}

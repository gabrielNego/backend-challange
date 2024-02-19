package com.study.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Statement {
	private CustomerBalance saldo;
	private List<Transaction> ultimas_transacoes;
}

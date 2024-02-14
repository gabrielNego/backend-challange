package com.study.model;

import java.time.LocalDateTime;

import com.study.model.enums.OperationTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Transaction {
	private long valor;
	private OperationTypeEnum tipo;
	private String descricao;
	private LocalDateTime realizada_em;
}

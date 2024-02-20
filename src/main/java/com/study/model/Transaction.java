package com.study.model;

import java.time.LocalDateTime;

import com.study.model.enums.OperationTypeEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction {
	@PositiveOrZero
	private long valor;
	private OperationTypeEnum tipo;
	@NotNull
	@NotBlank
	@Size(min = 1, max = 10, message = "The value is out of allowed size")
	private String descricao;
	private LocalDateTime realizada_em;
}

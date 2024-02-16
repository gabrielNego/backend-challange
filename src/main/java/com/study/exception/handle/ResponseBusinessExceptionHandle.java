package com.study.exception.handle;

import com.study.exception.BusinessException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResponseBusinessExceptionHandle implements ExceptionMapper<BusinessException> {

	@Override
	public Response toResponse(BusinessException exception) {
		return Response
				.status(exception.getHttpStatus())
				.entity(exception.getMessage())
				.build();
	}
}

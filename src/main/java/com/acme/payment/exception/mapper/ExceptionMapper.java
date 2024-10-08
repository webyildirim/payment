package com.acme.payment.exception.mapper;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.acme.payment.api.model.ErrorResponse;
import com.acme.payment.exception.EntityAlreadyExistException;
import com.acme.payment.exception.RecordNotFoundExceptioın;

@ControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler(EntityAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody ErrorResponse handleEntityFoundException(EntityAlreadyExistException exception) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrors(Arrays.asList(exception.getErrorCode()));
		return errorResponse;
	}

	@ExceptionHandler(RecordNotFoundExceptioın.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleRecordNotFoundException(RecordNotFoundExceptioın exception) {

	}

}

package com.example.build.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			CustomFieldError fieldError = new CustomFieldError();
			fieldError.setField(((FieldError) error).getField());
			fieldError.setMessage(error.getDefaultMessage());
			fieldErrors.add(fieldError);
		});

		fieldErrorResponse.setFieldErrors(fieldErrors);
		return new ResponseEntity<>(fieldErrorResponse, status);
	}

	@ExceptionHandler(DuplicateUserException.class)
	protected ResponseEntity<Object> handleDuplicateUser(DuplicateUserException ex) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldError = Collections.singletonList(new CustomFieldError("email", ex.getMessage()));
		fieldErrorResponse.setFieldErrors(fieldError);
		return new ResponseEntity<>(fieldErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DuplicateGroupException.class)
	protected ResponseEntity<Object> handleDuplicateGroup(DuplicateGroupException ex) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldError = Collections.singletonList(new CustomFieldError("group", ex.getMessage()));
		fieldErrorResponse.setFieldErrors(fieldError);
		return new ResponseEntity<>(fieldErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalStateException.class)
	protected ResponseEntity<Object> handleIllegalState(IllegalStateException ex) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldError = Collections.singletonList(new CustomFieldError("email", ex.getMessage()));
		fieldErrorResponse.setFieldErrors(fieldError);
		return new ResponseEntity<>(fieldErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IncorrectDateRangeException.class)
	protected ResponseEntity<Object> handleIncorrectDateRange(IncorrectDateRangeException ex) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldError = Collections.singletonList(new CustomFieldError("date_range", ex.getMessage()));
		fieldErrorResponse.setFieldErrors(fieldError);
		return new ResponseEntity<>(fieldErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ AuthenticationException.class })
	@ResponseBody
	public ResponseEntity<Object> handleAuthenticationException(Exception ex) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldError = Collections
				.singletonList(new CustomFieldError("unauthorized", "Email or password is incorrect"));
		fieldErrorResponse.setFieldErrors(fieldError);
		return new ResponseEntity<>(fieldErrorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ GroupAuthorizationException.class })
	@ResponseBody
	public ResponseEntity<Object> handleGroupAuthorizationException(GroupAuthorizationException ex) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		List<CustomFieldError> fieldError = Collections
				.singletonList(new CustomFieldError("unauthorized", ex.getMessage()));
		fieldErrorResponse.setFieldErrors(fieldError);
		return new ResponseEntity<>(fieldErrorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

	// private Map<String, List<String>> getErrorsMap(List<String> errors) {
	// Map<String, List<String>> errorResponse = new HashMap<>();
	// errorResponse.put("errors", errors);
	// return errorResponse;
	// }
}
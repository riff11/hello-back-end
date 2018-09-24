package com.derkach.boot.hello_back_end;

import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.derkach.boot.hello_back_end.exceptions.ErrorDetails;

//
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorDetails errorDetails, HttpStatus httpStatus) {
		return new ResponseEntity<>(errorDetails, httpStatus);
	}

	// @ExceptionHandler(EntityNotFoundException.class)
	// protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException
	// ex,
	// WebRequest request) {
	// // ApiError apiError = new ApiError(NOT_FOUND);
	// // apiError.setMessage(ex.getMessage());
	// ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	// request.getHeader("status"), request.getDescription(false));
	// return buildResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	// }

	// @ExceptionHandler(Throwable.class)
	// public final ResponseEntity<Object> handleAllExceptions(Exception ex,
	// WebRequest request) {
	// ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	// request.getDescription(false));
	// return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	// }

	// @ExceptionHandler(Exception.class)
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// public final ResponseEntity<ErrorDetails> handleAllExceptions2(Exception ex,
	// WebRequest request) {
	// ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	// request.getDescription(false));
	// return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	// }

	// @ExceptionHandler(StudentNotFoundException.class)
	// public final ResponseEntity<ErrorDetails>
	// handleUserNotFoundException(StudentNotFoundException ex,
	// WebRequest request) {
	// ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	// request.getDescription(false));
	// return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	// }
}

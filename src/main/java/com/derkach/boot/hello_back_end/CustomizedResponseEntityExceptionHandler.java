package com.derkach.boot.hello_back_end;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.derkach.boot.hello_back_end.exceptions.ErrorDetails;
/**
 * exceptions hendlers.
 * @author alex
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.NOT_FOUND,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorDetails errorDetails, HttpStatus httpStatus) {
		return new ResponseEntity<>(errorDetails, httpStatus);
	}


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails( ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,
				request.getDescription(false));
		return buildResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

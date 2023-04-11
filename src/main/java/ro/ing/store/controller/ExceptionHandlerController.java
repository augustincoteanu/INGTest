package ro.ing.store.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.log4j.Log4j2;
import ro.ing.store.db.services.MessageDetails;

@ControllerAdvice
@RestController
@Log4j2
public class ExceptionHandlerController {

	@ExceptionHandler({ Exception.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		MessageDetails errorDetails = new MessageDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		MessageDetails errorDetails = new MessageDetails(new Date(), errors.toString(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		String name = ex.getParameterName();
		MessageDetails errorDetails = new MessageDetails(new Date(), name, ex.getLocalizedMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}

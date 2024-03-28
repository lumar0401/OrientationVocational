package com.co.orientationVocational.app.business;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class exceptions {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<FileMessages> maxSizeException(MaxUploadSizeExceededException exc){
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileMessages("Uno o mas archivos exceden el tamaño máximo"));
	}
}

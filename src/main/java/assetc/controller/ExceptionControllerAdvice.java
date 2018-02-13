/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import assetc.model.Error;


/**
 *
 * @author Laud.Ochei
 */
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
	public ResponseEntity<Error> exceptionHandler(Exception ex) {
		Error error = new Error();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Please contact your administrator");
		return new ResponseEntity<Error>(error, HttpStatus.OK);
	}
    
}

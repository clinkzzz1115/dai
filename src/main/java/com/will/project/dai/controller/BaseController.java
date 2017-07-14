package com.will.project.dai.controller;

import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by jiayi.chen on 2016/12/2.
 */
public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> processValidationError(MethodArgumentNotValidException e) {
		logger.info("数据校验异常[{}]", e.getMessage());
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		logger.info("FieldErrors[{}]", JSON.toJSONString(fieldErrors));
		String errorMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).distinct()
				.collect(Collectors.joining("\n"));
		logger.info("异常提示:[{}]", errorMessage);
		return new ResponseEntity(errorMessage,HttpStatus.BAD_REQUEST);
	}
}

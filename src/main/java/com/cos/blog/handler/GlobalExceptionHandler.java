//package com.cos.blog.handler;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//
//@ControllerAdvice //모든 exception이 생기면 일로들어와
//@RestController
//public class GlobalExceptionHandler {
//	
//	@ExceptionHandler(value = IllegalArgumentException.class)
//	public String handleArgumentExeption(IllegalArgumentException e) {
//		return "<h1>"+e.getMessage() +"</h1>";
//	}
//}

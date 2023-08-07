package com.project.fastpickup.admin.adviceexception;

/*
 * Date   : 2023.08.01
 * Author : 권성준
 * E-mail : thistrik@naver.com
 */

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.project.fastpickup.admin.adviceexception.errorenum.AdviceErrorCode;
import com.project.fastpickup.admin.adviceexception.exception.DataNotFoundException;
import com.project.fastpickup.admin.adviceexception.exception.ResourceNotFoundException;
import com.project.fastpickup.admin.member.restcontroller.MemberRestController;
import com.project.fastpickup.admin.order.restcontroller.OrderRestController;
import com.project.fastpickup.admin.product.restcontroller.ProductRestController;
import com.project.fastpickup.admin.qna.restcontroller.QnaRestController;
import com.project.fastpickup.admin.review.restcontroller.ReviewRestController;

@RestControllerAdvice(assignableTypes = { ReviewRestController.class,
		QnaRestController.class, ProductRestController.class, OrderRestController.class, MemberRestController.class })

public class AdviceController {
	
	/*
	 * Time : Error 발생 시점
	 * AdviceErrorStatus : Status
	 * AdviceErrorCode : Message
	 */
	private Map<String, String> generateErrorMap(AdviceErrorCode errorCode, Exception ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("time", "" + System.currentTimeMillis());
		errorMap.put("status", String.valueOf(errorCode.getStatus()));
		errorMap.put("message", errorCode.getMessage(ex));
		return errorMap;
	}

	// generateErrorEntity: 공통적으로 사용되는 에러 메시지를 담은 ResponseEntity를 생성하는 메서드
	private ResponseEntity<Map<String, String>> generateErrorEntity(AdviceErrorCode errorCode,
			Map<String, String> errorMap) {
		return new ResponseEntity<>(errorMap, errorCode.getStatus());
	}

	// 처리할 예외: NullPointerException
	// 발생 원인: 요청한 데이터가 없는 경우
	// HTTP 상태 코드 : 500(Null Pointer Exception)
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Map<String, String>> handleNullPointerErr(NullPointerException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.NULL_POINTER;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.NULL_POINTER, ex));
	}

	// 처리할 예외: MethodArgumentTypeMismatchException
	// 발생 원인: 요청에 대한 메서드의 인자가 올바르지 않은 경우
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.METHOD_ARUGMNET_TYPE_MISMATCH;
		return generateErrorEntity(adviceErrorCode,
				generateErrorMap(AdviceErrorCode.METHOD_ARUGMNET_TYPE_MISMATCH, ex));
	}

	// 처리할 예외: DataIntegrityViolationException
	// 발생 원인: 데이터 무결성 위반, 즉, SQL 제약 조건을 위반하는 경우
	// HTTP 상태 코드: 417 (Expectation Failed)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleFKException(DataIntegrityViolationException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.DATA_INTEGRITY_VIOLATION;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.DATA_INTEGRITY_VIOLATION, ex));
	}

	// 처리할 예외: NoSuchElementException, EmptyResultDataAccessException
	// 발생 원인: 요청한 엔티티가 없는 경우
	// HTTP 상태 코드: 417 (Expectation Failed)
	@ExceptionHandler({ NoSuchElementException.class, EmptyResultDataAccessException.class })
	public ResponseEntity<Map<String, String>> handleNoSuchElement(Exception ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.NO_SUCH_ELEMENT;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.NO_SUCH_ELEMENT, ex));
	}

	// 처리할 예외: IllegalArgumentException
	// 발생 원인: 메서드에 잘못된 인자를 전달한 경우
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentErr(IllegalArgumentException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.ILLEGAL_ARGUMENT;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.ILLEGAL_ARGUMENT, ex));
	}

	// 처리할 예외: DataNotFoundException
	// 발생 원인: 필요한 데이터를 찾을 수 없는 경우
	// HTTP 상태 코드: 404 (Not Found)
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleDataNotFoundException(DataNotFoundException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.DATA_NOT_FOUND;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.DATA_NOT_FOUND, ex));
	}

	// 처리할 예외: BindException
	// 발생 원인: 바인딩에 실패한 경우, 즉, 폼 검증에서 오류가 발생한 경우
	// HTTP 상태 코드: 417 (Expectation Failed)
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.BIND_EXCEPTION;
		Map<String, String> errorMap = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError.getCode()));
		return generateErrorEntity(adviceErrorCode, errorMap);
	}

	// 처리할 예외: ArrayIndexOutOfBoundsException
	// 발생 원인: 배열에 없는 인덱스를 참조하려고 할 때 발생
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public ResponseEntity<Map<String, String>> handleArrayIndexOutOfBoundsErr(ArrayIndexOutOfBoundsException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.ARRAY_INDEX_OUT_OF_BOUNDS;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.ARRAY_INDEX_OUT_OF_BOUNDS, ex));
	}

	// 처리할 예외: NumberFormatException
	// 발생 원인: 숫자로 파싱할 수 없는 문자열을 숫자로 변환하려고 할 때
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Map<String, String>> handleNumberFormatErr(NumberFormatException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.NUMBER_FORMAT;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.NUMBER_FORMAT, ex));
	}

	// 처리할 예외: HttpMessageNotReadableException
	// 발생 원인: HTTP 요청의 본문을 객체로 변환할 수 없을 때 (Spring에서 사용)
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableErr(
			HttpMessageNotReadableException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.HTTP_MESSAGE_NOT_READABLE;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.HTTP_MESSAGE_NOT_READABLE, ex));
	}

	// 처리할 예외: HttpClientErrorException
	// 발생 원인: HTTP 상태 코드 4xx를 반환하는 HTTP 응답을 받았을 때 발생
	// HTTP 상태 코드: 400 (Bad Request)
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Map<String, String>> handleHttpClientError(HttpClientErrorException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.HTTP_CLIENT_ERROR;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.HTTP_CLIENT_ERROR, ex));
	}

	// 처리할 예외: HttpServerErrorException
	// 발생 원인: HTTP 상태 코드 5xx를 반환하는 HTTP 응답을 받았을 때 발생
	// HTTP 상태 코드: 500 (Internal Server Error)
	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<Map<String, String>> handleHttpServerError(HttpServerErrorException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.HTTP_SERVER_ERROR;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.HTTP_SERVER_ERROR, ex));
	}

	// 처리할 예외: ResourceNotFoundException
	// 발생 원인: 요청한 리소스를 찾을 수 없을 때
	// HTTP 상태 코드: 404 (Not Found)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, String>> handleResourceNotFoundErr(ResourceNotFoundException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.RESOURCE_NOT_FOUND;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.RESOURCE_NOT_FOUND, ex));
	}
	
	// 처리할 예외: AccessDeniedException
	// 발생 원인: 요청한 자원에 대한 권한이 없을 때
	// HTTP 상태 코드: 403 (Forbidden)
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, String>> handleAccessDeniedErr(AccessDeniedException ex) {
		AdviceErrorCode adviceErrorCode = AdviceErrorCode.ACCESS_DENIED;
		return generateErrorEntity(adviceErrorCode, generateErrorMap(AdviceErrorCode.ACCESS_DENIED, ex));
	}
}
package com.example.demo;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

  private Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public String handleInternalError(Exception e) {
    logger.error("Hello internal server error", e);
//    Span span = Span.current();
//    span.setStatus(StatusCode.ERROR, "Something bad happened!");
//    span.recordException(e);
//    span.end();
    return String.format("Internal Server Error (traceId: %s)", MDC.get("traceId"));  }

}

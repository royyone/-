package com.example.springboot2.Exception;

import com.example.springboot2.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component //Spring容易自动管理
@RestControllerAdvice //在controller层添加通知。如果使用@ControllerAdvice，则方法上需要添加@ResponseBody
public class UnifiedExceptionHandler {

    /**
     * 未定义异常
     */
    @ExceptionHandler(value = {Exception.class}) //当controller中抛出Exception，则捕获
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error();
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleBusinessException(ServiceException e){
        log.error(e.getMessage(), e);
        return Result.error(String.valueOf(e.getCode()), e.getMessage());
    }

}

package com.suffixit.kieb.ExceptionHandler;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        //System.out.println("Execution message: " + ex.getMessage());
        //System.out.println("Method name: " + method.getName());
        for (Object obj : params) {
            System.out.println("Parameter value: " + obj);
        }
    }
}

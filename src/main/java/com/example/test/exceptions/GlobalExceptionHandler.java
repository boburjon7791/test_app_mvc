package com.example.test.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e){
        log.error("");
        log.error(e.getClass().getName());
        log.error(e.getMessage());
        for(StackTraceElement element : e.getStackTrace()){
            log.error(element.toString());
        }
        log.error("");
        ModelAndView modelAndView = new ModelAndView("errors/server_error");
        modelAndView.addObject("description", e.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView handler(BadRequestException e){
        ModelAndView modelAndView = new ModelAndView("errors/bad");
        modelAndView.addObject("description",e.getDescription());
        return modelAndView;
    }
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handler(NotFoundException e){
        ModelAndView modelAndView = new ModelAndView("errors/not_found");
        modelAndView.addObject("description",e.getDescription());
        return modelAndView;
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handler(NoResourceFoundException e){
        ModelAndView modelAndView=new ModelAndView("errors/not_found");
        modelAndView.addObject("description", "Ushbu sahifa mavjud emas");
        return modelAndView;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handler(MethodArgumentNotValidException e){
        ModelAndView modelAndView=new ModelAndView("errors/bad");
        modelAndView.addObject("description", e.getMessage());
        return modelAndView;
    }
}

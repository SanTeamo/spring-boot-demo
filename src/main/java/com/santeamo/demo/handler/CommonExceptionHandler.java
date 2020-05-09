package com.santeamo.demo.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @title
 * @className com.santeamo.demo.handler.CommonExceptionHandler.java
 * @description
 *
 * @author santeamo
 * @date  2019-10-08 上午 11:15
 * @version V1.0
 */
@ControllerAdvice
public class CommonExceptionHandler {

    private static final String ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
                               HttpServletResponse response, Exception e) throws Exception {

        e.printStackTrace();

        if (isAjax(reqest)) {
            Map<String,Object> ajaxResult = new HashMap<>();
            ajaxResult.put("code","9999");
            ajaxResult.put("msg",e.getMessage());
            return ajaxResult;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(ERROR_VIEW);
            return mav;
        }
    }

    private static boolean isAjax(HttpServletRequest httpRequest){
        return  (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals( httpRequest.getHeader("X-Requested-With").toString()) );
    }
}

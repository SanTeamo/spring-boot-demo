package com.santeamo.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title
 * ClassName com.santeamo.demo.controller.CommonErrorController.java
 * Description 错误处理器，返回指定页面。
 *
 * @author santeamo
 * @date  2019-10-08 下午 1:26
 * @version V1.0
 */
//@RestController
public class CommonErrorController implements ErrorController {

    private final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    public String handleError(){
        System.out.println(getErrorPath());
        return "error";
    }
}

package com.yx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handler ：处理程序
 */
@Controller
public class HelloWorld {
    /**
     * 1、使用 @RequestMapping 注解来映射请求URL
     * 2、返回值通过视图解析器为实际的物理视图，对于InternalResourceViewResolver 视图解析器，会做如下解析：
     *   通过 prefix + returnVal + suffix 得到实际的物理视图，然后做转发操作
     */
    //用 @RequestMapping 注解来配置映射路径
    @RequestMapping(value = "/testHelloWorld", method = RequestMethod.GET, params = {"username"})
    public String testHelloWorld(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发
//        request.getRequestDispatcher("/views/index.jsp").forward(request,response);
        System.out.println("testHelloWorld");
        return "success";
    }

    @RequestMapping(value = "test1")
    public String test1(){
        return "success";
    }

    @RequestMapping(value = "test2")
    public String test2(){
        return "success";
    }

    @RequestMapping(value = "test3")
    public String test3(){
        return "success";
    }
}

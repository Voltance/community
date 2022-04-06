package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller //有才能被扫描  有@Component
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求数据
        System.out.println(request.getMethod());//请求的方式
        System.out.println(request.getServletPath());//请求的路径 网址
//        Enumeration是迭代器
        Enumeration<String> enumeration = request.getHeaderNames(); // 请求的消息头
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();//消息头中的请求对象
            String value = request.getHeader(name);//请求对象对应的值
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code")); // 获取参数名为code的数据值
        response.setContentType("text/html;charset=utf-8"); // 返回的响应内容的格式定义
        try (
                PrintWriter writer = response.getWriter();//获取写的对象
                ){
            writer.write("<h1>牛客网</h1>");//写入网页内容（html（上面定义的））
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get请求

    // /student?current=1&limit=20
    @RequestMapping(path = "/students", method= RequestMethod.GET) //只能用get去请求这个网页
    @ResponseBody
    public String getStudents( //RequestParam name的对象  可以没有输入数值 默认值为defaultValue
            @RequestParam(name="current", required = false, defaultValue = "1") int current,
            @RequestParam(name="limit", required = false, defaultValue = "20")  int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }

    // /student/123

    @RequestMapping(path = "/student/{id}", method=RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) { //PathVariable 获取路径中对应位置的数值
        System.out.println(id);
        return "a student";
    }

    // POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view"); // 调用的模板文件位置
        return mav;
    }

    // 和上面差不多 但要简单
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {      // 会自动调用Model   return对应的html的路径
        model.addAttribute("name","北京大学");
        model.addAttribute("age", "80");
        return "/demo/view";
    }

    // 响应JSON数据  一般在异步请求  （页面没有刷新，  但数据更新了）
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {  //Map对象被自动地转成JSON 前端显示的就是JSON
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    //多个JSON对象 一个集合[]框起来
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {  //Map对象被自动地转成JSON 前端显示的就是JSON
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp.put("name", "李四");
        emp.put("age", 15);
        emp.put("salary", 7000.00);
        list.add(emp);

        emp.put("name", "王梅");
        emp.put("age", 24);
        emp.put("salary", 5000.00);
        list.add(emp);
        return list;
    }

}

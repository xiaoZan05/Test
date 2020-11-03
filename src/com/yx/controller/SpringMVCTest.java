package com.yx.controller;

import com.yx.pojo.Address;
import com.yx.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(value = "/springmvc") //value可以省略，可以之间写 "/springmvc"
public class SpringMVCTest {

    /**
     * 测试 @RequestMapping的 Param 和 Header
     * value：设置请求映射地址
     * method:设置请求方式
     * params：设置请求参数
     * 1- param1：表示请求必须包含的名为param1的请求参数
     * 2- !param1：表示请求不能包含名为param1的请求参数
     * 3- param1 != value1：表示请求包含名为param1的请求参数，但其值不能为value1
     * 4-("param1 = value1","param2")：请求必须包含名为param1和param2的两个请求参数，且param1参数的值必须为value1
     *     param != value  可以不包含 但是包含的话一定不能等于value值
     * headers：限制请求头的相关参数信息
     */
    @RequestMapping(value = "/testRequestMapping", method = RequestMethod.GET, params = {"username"}
    )//headers = {"Accept-Language=zh-CN,zh;q=0.9"}
    public String testRequestMapping(){
        System.out.println("testRequestMapping");
        return "success";
    }

    /**
     * Ant风格资源地址支持三种匹配符：
     * 1- ？：匹配文件名中的一个字符
     * 2- *：匹配文件名中的任意字符
     * 3- **：匹配多层路径
     */
    @RequestMapping(value = "/testAntPath*/test1") //路径：/testAntPath/*****/test1
    public String testAntPath1(){
        System.out.println("testAntPath1");
        return "success";
    }

    @RequestMapping(value = "/testAntPath**/test2") //路径：/testAntPath/(******/******/)/test2
    public String testAntPath2(){
        System.out.println("testAntPath2");
        return "success";
    }

    @RequestMapping(value = "/testAntPath/test??") //路径：/testAntPath/test3** (两个字符)
    public String testAntPath3(){
        System.out.println("testAntPath3");
        return "success";
    }

    /**
     * @GetMapping注解可以省略 method 参数
     * @PathVariable注解获取请求URL中的占位符
     * @PathVariable("id") Integer id：表示将获取的id赋值给 Integer id
     * url中的 id 和 integer中的 id名相同，所以能自动匹配
     * url中的 name 和 String 的 name1 不相同，所以不能匹配，所以要写 (value = "name")
     */
    @GetMapping("/testPathVariable/{id}/{name}")
    public String testPathVariable(@PathVariable("id") Integer id,@PathVariable(value = "name") String name1){
        System.out.println("testPathVariable,id:"+id+",name:"+name1);
        return "success";
    }

    /**
     * Rest风格的URL
     * 以CRUD为例：
     * 新增：/order POST
     * 修改：/order/1 PUT
     * 获取：/order/1 GET
     * 删除：order/1 DELETE
     *
     * 如何发送PUT请求和DELET请求？
     * 1、需要配置HiddenHTTPMethodFilter
     * 2、需要发送POST请求
     * 3、需要发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或PUT
     *
     * springmvc的目标方法中如何得到id
     * 使用 @PathVariable注解
     */
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.PUT)
    @ResponseBody // 相当于转发 response.getWrite().write("xxx")
    public String testRest(@PathVariable Integer id){
        System.out.println("处理更新逻辑：id:"+id);
        return "success";  //转发
//        return "forword:success.jsp"; //重定向
    }
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.DELETE)     //   /deleteUser    /updateUser     /getUser
    @ResponseBody
    public String testRest2(@PathVariable Integer id){
        System.out.println("处理删除逻辑：id:"+id);
        //调用service -- dao -- sql
        return "success";  //转发
    }

    /**
     * 转发
     */
    @GetMapping("testForword")
    public String testForword(){
        System.out.println("转发");
        return "forward:/views/success.jsp";
    }

    /**
     * 重定向
     */
    @GetMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("重定向");
        return "redirect:/index.jsp"; //写了jsp关键字，就不在springmvc.xml中进行拼接了
    }

    /**
     * @RequestParam 来映射请求参数
     * value 值即请求参数的参数名
     * defaultValue 请求参数的默认值
     */
    @RequestMapping(value = "/testRequestParam",method = RequestMethod.POST)
    //                                                   是否是必须，默认为true   默认值为 "1"
    public String testRequestParam(@RequestParam(value = "id1",required = false,defaultValue = "1") Integer id,@RequestParam(value = "username1")String username,String password){
        System.out.println("id:"+id+",username:"+username+",password:"+password);
        return "success";
    }

    /**
     * 了解
     * 映射请求头信息
     * 用法同 @RequestParam
     */
    @RequestMapping(path = "testRequestHeader")
    public String testRequestHeader(@RequestHeader(name = "Accept-Language") String al){
        System.out.println("Accept-Language:"+al);
        return "success";
    }

    /**
     * 了解：
     * @CookieValue:映射一个Cookie值，属性同@RequestParam
     */
    @RequestMapping(path = "testCookieValue")
    public String testCookieValue(@CookieValue(name = "JSESSIONID") String JSESSIONID){
        System.out.println("JSESSIONID:"+JSESSIONID);
        return "success";
    }

    @RequestMapping("/testPojo")
    public String testPojo(User user){
        System.out.println(user);
        return "success";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("Java","Mysql","Spring"));
        return "success";
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("username","张三");
        return "success";
    }

    @RequestMapping(value = "/testModelAttribute", method = RequestMethod.POST)
    //@ModelAttribute("xyz")    ${requestScope.xyz}
    public String testModelAttribute(@ModelAttribute("user") User user) {
        System.out.println("修改：" + user);
        return "success";
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            //模拟数据从数据库中获取
            User user = new User(1, "Tom", "123", 1, null);
            System.out.println("从数据库中获取的对象：" + user);

            map.put("user", user);
        }
    }
    /**
     * i18n
     */
    @RequestMapping(value = "/i18n")
    public String i18n(){
        return "success";
    }
}

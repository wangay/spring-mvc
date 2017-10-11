package com.way;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.way.util.HttpClientUtil;
import com.way.vo.User;

@Controller
public class HelloController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}
	
	@RequestMapping("/login.do")  
	public String login(HttpServletRequest request){  
	    String name = request.getParameter("name") ; 
	    String pass = request.getParameter("pass")  ;
	    return "";
	} 
	
//其他例子
	
	@RequestMapping(value="/testX1")//输入http://localhost:8080/spring-mvc/testX1  那么testX1会找到下面这个方法 去处理。
	public ModelAndView xxx(){
		ModelAndView modelAndView = new ModelAndView("x1");//去找x1.jsp  根据 spring-web-servlet.xml中的配置的路径下
		modelAndView.addObject("attr1", "attr1的值");//jsp页面可以取得attr1的值
		
		User user = new User();
		user.setName("alex wang"); 
		
		
		modelAndView.addObject("user",user);//jsp页面可以取得user的值
		return modelAndView;
	}
	
	@RequestMapping(value="/pay")
	public ModelAndView pay() throws UnsupportedEncodingException, Exception{

		ModelAndView modelAndView = new ModelAndView("pay");//去找pay.jsp  根据 spring-web-servlet.xml中的配置的路径下
		
		String blockChainAddress =	HttpClientUtil.getBlockChainAddress();	
		
		modelAndView.addObject("blockChainAddress",blockChainAddress);//jsp页面可以取得user的值
		return modelAndView;

	}
	@RequestMapping(value="/balance")
	public ModelAndView balance() throws UnsupportedEncodingException, Exception{

		ModelAndView modelAndView = new ModelAndView("balance");//去找xx.jsp  根据 spring-web-servlet.xml中的配置的路径下

		String balance =	HttpClientUtil.getBlockChainBalance();

		modelAndView.addObject("balance",balance);//jsp页面可以取得user的值
		return modelAndView;
	}
	
	@RequestMapping(value="/paid")
	public ModelAndView paidCallback(
			@RequestParam("invoice_id") String invoice_id,
			@RequestParam("transaction_hash") String transaction_hash,
			@RequestParam("value") String value
			) throws UnsupportedEncodingException, Exception{
		//被支付的回调方法，然后被blockchain通知到
		ModelAndView modelAndView = new ModelAndView("paid");//去找paid.jsp  根据 spring-web-servlet.xml中的配置的路径下
		String real_secret = "ZzsMLGKe162CfA5EcG6j";
		System.out.println("invoice_id="+invoice_id);
		modelAndView.addObject("message","支付成功");//jsp页面可以取得user的值
		return modelAndView;
	}



	@RequestMapping(value="/balanceCallback")
	@ResponseBody
	public String balanceCallback(
			@RequestParam("transaction_hash") String transaction_hash,
			@RequestParam("address") String address,
			@RequestParam("confirmations") String confirmations,
			@RequestParam("value") String value
	) throws UnsupportedEncodingException, Exception{
		//调用balance update接口是提供出去的回调函数。
		System.out.println("value="+value+" transaction_hash= "+transaction_hash);

		final  String result = "ok";
		return result;
	}

	
}

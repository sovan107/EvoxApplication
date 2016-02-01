package com.sovan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String sayHello(Model model){
		
		model.addAttribute("greetings", "Hello World!!");
		return "hello";
	}
}

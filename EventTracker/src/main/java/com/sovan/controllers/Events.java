package com.sovan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Events {

	@RequestMapping(value="/event", method = RequestMethod.GET)
	public String displayEventForm(Model model){
		
		return "event";
	}
}

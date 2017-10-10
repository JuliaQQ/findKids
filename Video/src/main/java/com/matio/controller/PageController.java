package com.matio.controller;

import com.matio.mapping.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PageController {

	@Autowired
	SessionMapper sessionMapper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index.jsp");
		return mv;
	}

	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView regist() {
		ModelAndView mv = new ModelAndView("regist.jsp");
		return mv;
	}

//
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public ModelAndView login() {
//		ModelAndView mv = new ModelAndView("sub_pages/login_sub.jsp");
//		return mv;
//	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
		for (int i=0;i<request.getCookies().length;i++){

			if (request.getCookies()[i].getName().equals("session_id")){
				System.out.println(request.getCookies()[i].getValue());
				sessionMapper.deleteBySession(request.getCookies()[i].getValue());
				break;
			}
		}
		ModelAndView mv = new ModelAndView("index.jsp");
		mv.addObject("isLogin",0);
		return mv;
	}

	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	public ModelAndView archive() {
		ModelAndView mv = new ModelAndView("archive.jsp");
		return mv;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("contact.jsp");
		return mv;
	}

	@RequestMapping(value = "/single", method = RequestMethod.GET)
	public ModelAndView single() {
		ModelAndView mv = new ModelAndView("single.jsp");
		return mv;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("user_pages/products.jsp");
	}


}

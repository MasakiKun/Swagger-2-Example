package kr.ayukawa.swagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(value="/Home")
	public @ResponseBody String home() {
		return "<h1>hello, world!</h1>";
	}
}

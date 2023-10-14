package com.itb.tcc.inf2a.petcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/petcare/novo-usuario")
public class IndexController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		return "fale-conosco";
	}
	
	@GetMapping("senha")
	public String senhaPage() {
		return "esqueceu-senha";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "redirect:/fazer-login";
	}
}

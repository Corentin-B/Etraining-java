package fr.excilys.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		return new ModelAndView();
	}
	
	@GetMapping("/403")
	public ModelAndView getForbidenPage() {
		return new ModelAndView();
	}
	
	@GetMapping("/register")
	public ModelAndView getRegisterPage() {
		return new ModelAndView();
	}
	
	@PostMapping("/register")
	public ModelAndView postRegisterPage(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
	
        if (bindingResult.hasErrors()) {
        	return new ModelAndView("redirect:/register");
        }
        
		return new ModelAndView("redirect:/dashboard");
	}
}

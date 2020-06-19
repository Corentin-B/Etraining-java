package fr.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.model.UserDatabase;
import fr.excilys.services.CdbUserDetailsService;

@Controller
public class MainController {

	@Autowired
	private CdbUserDetailsService cdbUserDetailsService;
	
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
	public ModelAndView postRegisterPage(@ModelAttribute("from") UserDatabase userForm, BindingResult bindingResult) {
		
        if (bindingResult.hasErrors()) {
        	return new ModelAndView("redirect:/register");
        }
                
        cdbUserDetailsService.save(userForm);
        
		return new ModelAndView("redirect:/dashboard");
	}
}

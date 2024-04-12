package com.nik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nik.Service.UserService;
import com.nik.User.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@RequestMapping("/home")
	public String show() {

		return "home";
	}

	
	
	@RequestMapping("/Register")
	public String Register() {

		return "Register";
	}

	@PostMapping("/do_register")
	public String RegUser(@RequestParam("Username") String Username, @RequestParam("Password") String Password,
			@RequestParam("CPassword") String CPassword  ,Model model) {

		User user = new User(Username, Password, CPassword);
 
	   User existingUser = userService.checkedUser(Username, Password);
		
	   
	   
	   if (existingUser != null) {
	        
	       model.addAttribute("error","User is Already Exists");
	        return "Register"; 
	    }

	   
			this.userService.SaveUser(user);

			System.out.println("Register SuccessFully");

			return "redirect:/login";
			
		
			
		
		
		

	}

	@RequestMapping("/login")
	public String log() {

		return "login";
	}

	@RequestMapping("/dashboard")
	public String dashboard() {

		return "dashboard";
	}




	
	@PostMapping("/dologin")
	public String login(@RequestParam("Username") String Username, @RequestParam("Password") String Password ,Model model) {

		boolean check = userService.checkUser(Username, Password);

		if (check) {

			
			
			return "dashboard";
		}

		else {

			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}
	
	

	@GetMapping("/logout")
	public String logout(HttpServletRequest request,Model model) {
       
        HttpSession session = request.getSession(false);
        
    
        if (session != null) {
        	
        	
            session.invalidate(); 
        }

        model.addAttribute("error", "Logout SuccessFully");
        return "login"; 
    }

}

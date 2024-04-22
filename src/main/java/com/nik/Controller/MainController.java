package com.nik.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
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
	
	
	@RequestMapping("/update")
	public String update() {

		return "update";
	}

	@RequestMapping("/searchbook")
	public String sbook() {

		return "searchbook";
	}

	@RequestMapping("/Csearchbook")
	public String Csbook() {

		return "Csearchbook";
	}

	@RequestMapping("/Register")
	public String Register() {

		return "Register";
	}

	@PostMapping("/do_register")
	public String RegUser(@RequestParam("Username") String Username, @RequestParam("Password") String Password,
			@RequestParam("CPassword") String CPassword, Model model) {

		User user = new User(Username, Password, CPassword);

		User existingUser = userService.checkedUser(Username, Password);

		if (existingUser != null) {

			model.addAttribute("error", "User is Already Exists");
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

	@RequestMapping("/Cdashboard")
	public String Cdashboard() {

		return "Cdashboard";
	}

	@PostMapping("/dologin")
	public String login(@RequestParam("Username") String Username, @RequestParam("Password") String Password,
			Model model) {

		boolean check = userService.checkUser(Username, Password);

		User user = userService.checkedUser(Username, Password);

		if (user != null && user.getUsername().equals("admin@gmail.com") && user.getPassword().equals("admin")) {

			
		String name = user.getUsername();
		
		
		model.addAttribute("name",name);

			
			return "dashboard";
		}

		else if (user == null) {

			model.addAttribute("error", "Invalid username or password");
			return "login";

		}

		else {

			return "Cdashboard";

		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession(false);

		if (session != null) {

			session.invalidate();
		}

		model.addAttribute("error", "Logout SuccessFully");
		return "login";
	}

}

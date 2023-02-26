package com.raeshi.springboot.myfirstwebapp.login;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")

public class WelcomeController{
//	private AuthenticationService authenticationService;
//	
//	public loginController(AuthenticationService authenticationService) {
//			super();
//			this.authenticationService = authenticationService;
//		}


//	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping(value= "/",method = RequestMethod.GET)
    public String gotoWelcomePage (ModelMap model) {
//		logger.debug("Requset is {}", name);
//		logger.info("Info Requset is {}", name);
//		logger.warn("Warn Requset is {}", name);
//		System.out.println("Request" + name);
		model.put("name", getLoggedInUsername());
        return "welcome";
    }
	
	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	

//	@RequestMapping(value= "login",method = RequestMethod.POST)
//    public String welcome (@RequestParam String name, @RequestParam String password, ModelMap model ) {
//		
//		if (authenticationService.authenticate(name, password)) {
//			model.put("name",name);
////		logger.debug("Requset is {}", name);
////		logger.info("Info Requset is {}", name);
////		logger.warn("Warn Requset is {}", name);
////		System.out.println("Request" + name);
//		
//		//Authentication
//			return "welcome";
//		 }
//		model.put("errorMessage", "Invalid Credentials");
//		 return "logIn";
//    }
}

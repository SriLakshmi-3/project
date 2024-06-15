package com.sri.springboot.mydiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sri.springboot.mydiary.entity.Entry;
import com.sri.springboot.mydiary.entity.User;
import com.sri.springboot.mydiary.service.EntryService;
import com.sri.springboot.mydiary.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class ClientController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	HttpSession session;
	
	
	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	@Autowired
	private EntryService entryService;
	
	public EntryService getEntryService() {
		return entryService;
	}


	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}
	

	@GetMapping("home")
	public String homepage(Model model)
	{
		return "loginpage";
	}
	
	
	@GetMapping("register")
	public String registrationpage(Model model)
	{
		return "registrationpage";
	}
	
	//here model attribute is used for multiple data types 
	@PostMapping(value="saveuser")
	public String saveuser(@ModelAttribute("user") User user)
	{
		//code to save the user details in the database
	
        userService.saveUser(user);	
		return "registersuccess";
		
	}
	
	//here user implies the data which we provide while logining to username through login page
	@PostMapping(value="/authenticate")
	public String authenticateuser(@ModelAttribute("user") User user, Model model)
	{
		String viewname = "loginpage";
		//here user1 resents the data present in the database
		User user1 = userService.findbyUsername(user.getUsername());
		
		if(user1!=null && user.getPassword().equals(user1.getPassword())) {
			viewname ="userhomepage";
			model.addAttribute("user",user1);
			//through session we can able to know which user is logged in 
			//sessions are used to store the information here after adding users to the userpage session need to be added to store them
			session.setAttribute("user", user1);
			List<Entry> entries=null;
			
			try {
				entries=entryService.findByUserId(user1.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("entrieslist",entries);
		}
		
		return viewname;
	}

	@GetMapping("addentry")
	public String addentry(Model model)
	{
		return "addentryform";
	}
	
	@PostMapping("saveentry")
	public Model saveentry(@ModelAttribute("entry") Entry entry ,Model model)
	{
		 String viewname="userhomepage";
		
		
		entryService.saveEntry(entry);
		
		User user1=(User)session.getAttribute("user");
		
		List<Entry> entries=null;
		
		try {
			entries=entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		return model;
	}
	
	
	
	@GetMapping("viewentry")
	//request param assigns the value in which jsp file represts 
	//request param will be used to choose particular id not like as model attribute
	public String viewentry(@RequestParam("id") int id,Model model)
	{
		
		
		Entry entry = entryService.findbyIdEntry(id);
		
		model.addAttribute("entry", entry);
		
		return "displayentry";
	}
	
	
	@GetMapping("userhome")
	public String userhomepage(Model model)
	{
		
		User user1=(User)session.getAttribute("user");
		
		List<Entry> entries=null;
		
		try {
			entries=entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		return "userhomepage";
	}
	
	@GetMapping("updateentry")
	public Model updateentry(@RequestParam("id") int id,Model model)
	{
		String viewname ="displayupdateentry";
		
		Entry entry = entryService.findbyIdEntry(id);
		
		model.addAttribute("entry", entry);
		
		User user1=(User)session.getAttribute("user");
		
		if(user1==null)
			viewname="loginpage";
		
		return model;
	}
	
	@PostMapping("processentryupdate")
	public String processentryupdate(@ModelAttribute("entry") Entry entry,Model model)
	{
		
		
		
		entryService.updateEntry(entry);
		
		User user1=(User)session.getAttribute("user");
		
		List<Entry> entries=null;
		
		try {
			entries=entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		return "userhomepage";
	}
	
	
	@PostMapping("deleteentry")
	public Model deleteentry(@RequestParam("id") int id,Model model)
	{
		String viewname="userhomepage";
		
         User user1=(User)session.getAttribute("user");
		
		
		Entry entry = entryService.findbyIdEntry(id);
		
		if(user1==null)
			viewname="loginpage";
		else
			entryService.deleteEntry(entry);
		
		
        List<Entry> entries=null;
		
		try {
			entries=entryService.findByUserId(user1.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("entrieslist", entries);
		
		
		
		
		return model;
	}
	
	@PostMapping("signout")
	public String signout()
	{
		
		
		
		session.invalidate();
		
		
		return "loginpage";
	}

	
} 

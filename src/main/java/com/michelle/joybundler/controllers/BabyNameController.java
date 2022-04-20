package com.michelle.joybundler.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.michelle.joybundler.models.BabyName;
import com.michelle.joybundler.services.BabyNameService;
import com.michelle.joybundler.services.UserService;

@Controller
public class BabyNameController {
		@Autowired
		private BabyNameService babyService;
		@Autowired
		private UserService userService;
		
		@GetMapping("/home")
		public String home(Model model, HttpSession session) {
			if(session.getAttribute("user_id")== null) return "redirect:/";
			model.addAttribute("user", userService.getUser((Long) session.getAttribute("user_id")));
			model.addAttribute("babyNames",babyService.allNames());
			return "home.jsp";
		}
		
		//get and post new
		@GetMapping("/names/new")
		public String newName(@ModelAttribute("babyName") BabyName babyName, Model model, HttpSession session) {
			if(session.getAttribute("user_id")== null) return "redirect:/";
			model.addAttribute("user", userService.getUser((Long) session.getAttribute("user_id")));
			return "newName.jsp";
		}
		
		@PostMapping("/names/new")
		public String addNewName(@Valid @ModelAttribute("babyName") BabyName babyName, BindingResult result, Model model, HttpSession session) {
			if(session.getAttribute("user_id")== null) return "redirect:/";
			if(result.hasErrors()) {
				model.addAttribute("user", userService.getUser((Long) session.getAttribute("user_id")));
				return "newName.jsp";
			}else {
				babyService.createOne(babyName,result);
				return "redirect:/home";
			}
		}
		
		//edit
		@GetMapping("/names/{id}/edit")
		public String editName(@PathVariable("id") Long id, Model model, HttpSession session) {
			if(session.getAttribute("user_id") == null) return "redirect:/";
			BabyName babyName = babyService.getOne(id);
			if((Long) session.getAttribute("user_id") != babyName.getUser().getId()) {
				return "redirect:/";
			}
			model.addAttribute("babyName", babyService.getOne(id));
			return "editName.jsp";
		}
		
		@PutMapping("/names/{id}/edit")
		public String updateName(@Valid @ModelAttribute("babyName") BabyName babyName, BindingResult result,@PathVariable("id") Long id) {
			if(result.hasErrors()) return "editName.jsp";
			else {
				babyService.update(babyName);
				return "redirect:/home";
			}
		}
		
		//get one
		@GetMapping("/names/{id}")
		public String getName(@PathVariable("id") Long id, Model model,HttpSession session) {
			if(session.getAttribute("user_id")== null) return "redirect:/";
			model.addAttribute("user", userService.getUser((Long) session.getAttribute("user_id")));
			model.addAttribute("babyName",babyService.getOne(id));
			return "oneName.jsp";
		}
		
		//delete
		@DeleteMapping("/names/{id}/delete")
		public String deleteName(@PathVariable("id") Long id) {
			babyService.deleteName(id);
			return "redirect:/home";
		}
		
		@PutMapping("/names/{id}/like")
		public String likeGift(@PathVariable("id")Long nameId, HttpSession session) {
			Long userId = (Long) session.getAttribute("user_id");
			babyService.likeName(nameId, userId);
			
			return "redirect:/home";
		}
		
		@PutMapping("/names/{id}/unlike")
		public String unlikeGift(@PathVariable("id")Long nameId, HttpSession session) {
			Long userId = (Long) session.getAttribute("user_id");
			babyService.unlikeName(nameId, userId);
			
			return "redirect:/home";
		}
}

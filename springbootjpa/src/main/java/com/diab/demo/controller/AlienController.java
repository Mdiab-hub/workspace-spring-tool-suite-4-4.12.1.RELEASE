package com.diab.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.diab.demo.dao.AlienRepo;
import com.diab.demo.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable int aid) {
		Alien a = repo.getOne(aid);
		
		repo.delete(a);
		
		return "deleted";
	}
	
	@PostMapping(path="/alien", consumes= {"application/json"})//why did we write consumes="" here?
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
//	@RequestMapping("/getAlien")
//	public ModelAndView getAlien(@RequestParam Integer aid) {
//		ModelAndView mv = new ModelAndView("showAlien.jsp");//what does this do? What is model view do?
//		Alien alien = repo.findById(aid).orElse(new Alien());
//		//TODO: figure out how to delete/update
//		System.out.println(repo.findByTech("Java"));
//		System.out.println(repo.findByAidGreaterThan(102));
//		System.out.println(repo.findByTechSorted("Java"));
//		mv.addObject(alien);
//		return mv;
//	}
	
	@GetMapping(path="/aliens")
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	
	@PutMapping(path="/alien", consumes= {"application/json"})//why did we write consumes="" here?
	public Alien saveOrUpdateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	@RequestMapping("/alien/{aid}")
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
}

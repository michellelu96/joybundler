package com.michelle.joybundler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.michelle.joybundler.models.BabyName;
import com.michelle.joybundler.models.User;
import com.michelle.joybundler.repositories.BabyNameRepository;

@Service
public class BabyNameService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private BabyNameRepository babyRepo;
	
	public List<BabyName> allNames(){
		return babyRepo.findAll();
	}
	
	public BabyName getOne(Long id) {
		Optional<BabyName> optionalName = babyRepo.findById(id);
		if(optionalName.isPresent()) return optionalName.get();
		else return null;
	}
	
	public BabyName createOne(BabyName babyName,BindingResult result) {
		Optional<BabyName> potentialName = babyRepo.findByName(babyName.getName());
		if(potentialName.isPresent()) {
			result.rejectValue("name","unique","Name already exists");
		}
        if(result.hasErrors()) {
      	  return null;
      }
        
		return babyRepo.save(babyName);
	}
	
	public BabyName update(BabyName babyName) {
		return babyRepo.save(babyName);
	}
	
	public void deleteName(Long id) {
		babyRepo.deleteById(id);
	}
	
	public void likeName(Long nameId, Long userId) {
		// 1. get user 
		User user = userService.getUser(userId);
		// 2. get gift
		BabyName babyName= this.getOne(nameId);
		// 3. add user to the gift
		babyName.getLikedUsers().add(user);
		// 4. save 		
		babyRepo.save(babyName);
	}
	
	public void unlikeName(Long nameId, Long userId) {
		// 1. get user 
		User user = userService.getUser(userId);
		// 2. get gift
		BabyName babyName= this.getOne(nameId);
		// 3. add user to the gift
		babyName.getLikedUsers().remove(user);
		// 4. save 		
		babyRepo.save(babyName);
	}
}

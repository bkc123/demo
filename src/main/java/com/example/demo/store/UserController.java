package com.example.demo.store;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserEntityCrudRepository repo;

	@GetMapping(path = "/contact")
	String contact() {
		return "phone: 414.123.1234";
	}


	@PostMapping
	public UserEntity saveUser(@RequestBody UserEntity user) {
		return repo.save(user);
	}
	/*
    @GetMapping
    public List<UserEntity> listUser(){
    	return (List<UserEntity>) repo.findAll();
    }

    @GetMapping
    public UserEntity getOne(@PathVariable Long id) {
    	return repo.findById(id).get();
    }
	 */

	@GetMapping(path = "/createUser", produces = "text/html")
	String showUserForm() {
		String output = "<form action='' method='POST'>";
		output += "<h4>Please enter the following fields to create user </h4>";
		output += "Name: <input name='name' type='text' /><br />";
		output += "Email: <input name='email' type='text' /><br />";
		output += "<input type='submit' />";
		output += "</form>";
		return output ;
	}

	@PostMapping(path = "/createUser")
	String createUser(@ModelAttribute UserEntity user) {
		if (user == null || user.getName() == null && user.getEmail()== null) {
			throw new RuntimeException("user name is required");
		}

		repo.save(user);
		String output = "User " + user.getName() + " was successfully created";
		output += "<form action='home'>";
		output += "<input type='submit' value = 'home' />";
		output += "</form>";
		return output ;

	}

	@GetMapping(path = "/searchUser", produces = "text/html")
	String showSearchForm() {
		String output = "<form action='' method='POST'>";
		output += "Search by id: <input name='id' type='Long' /> "
				+ "<input type='submit' value = 'submit' /> <br />" ;
		output += "</form>";
		return output ;
	}


	@PostMapping(path = "/searchUser")
	String  searchUser(Long id) {
		String s = null;
		Iterable<UserEntity> users = repo.findAll();
		for(UserEntity u:users) {
			if (id.equals(u.getId())) {
				s= "User with id " + id +  " was successfully found + <br/> <br/>";
				s+= "<h4>Here is the detail profile of User </h4>";
				s += "Id: "+ u.getId() + "<br />Name: "+ u.getName() + "<br />Email: " + u.getEmail();
				String output = s;
				output += "</form>";
				output += "<form action='home'>";
				output += "<input type='submit' value = 'Home' />";
				output += "</form>" ;
				output += "<form action='updateUser'>";
				output += "<input type='submit' value = 'update' />";
				output += "</form>" ;
				return output;
			}

		}

		String output = "Sorry, user with id " + id + " doen't exist.";
		output += "<form action='home'>";
		output += "<input type='submit' value = 'Home' />";
		output += "</form>" ;
		return output;

	}


	@GetMapping(path = "/updateUser", produces = "text/html")
	String showUpdateForm() {
		String output = "<form action='' method='POST'>";
		output += "update by id: <input name='id' type='Long' /> "
				+ "<input type='submit' value = 'submit' /> <br />" ;
		output += "</form>";
		return output ;
	}


	@PostMapping(path = "/updateUser")
	String updateUser(Long id) {
		String s;
		Iterable<UserEntity> users = repo.findAll();
		for(UserEntity u:users) {
			if (id.equals(u.getId())) {
				s= "User with id " + id +  " was successfully found + <br/> <br/>";
				s+= "<h4>Here is the detail profile of User </h4>";
				s += "Id: "+ u.getId() + "<br />Name: "+ u.getName() + "<br />Email: " + u.getEmail();
				String output = s;
				output += "<h4>Please enter the following fields to update user </h4>";
				output += "<form action='updateResult', method='GET'>";
				output += "Name: <input name='name' type='text' /><br />";
				output += "Email: <input name='email' type='text' /><br />";
				output += "<input type='submit' />";
				output += "</form>";
				return output;
			}
		}
		String output = "Sorry, user with id " + id + " doen't exist.";
		output += "<form action='home'>";
		output += "<input type='submit' value = 'Home' />";
		output += "</form>" ;
		return output;
	}


	@GetMapping(path ="/updateResult")
	String updateResult(@ModelAttribute UserEntity user,String name, String email ) {
		user.setName(name);
		user.setEmail(email);
		repo.save(user);
		String output = "User " + user.getName() + " was successfully updated" ;
		output += "<form action='home'>";
		output += "<input type='submit' value = 'home' />";
		output += "</form>";
		return output ;
	}
	


	@GetMapping(path = "/home")
	String home() {
		Iterable<UserEntity> users = repo.findAll();

		String  myUser =   "  <a href=\"searchUser\"> search</a>   " 
				+ "  <a href=\"createUser\"> createUser</a> "
				+ "  <a href=\"updateUser\"> update</a> ";


		myUser += "<h2>All Users</h2>";
		for (UserEntity p: users) {
			myUser = myUser + "<p>" + p.getId()+ ".    " +  p.getName() + "    "  + p.getEmail() + "</p>";      
		}
		return myUser;
	}



}

package com.org.controller;

import java.math.BigInteger;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.org.exceptions.RecordAlreadyPresentException;
import com.org.exceptions.RecordNotFoundException;
import com.org.model.Users;
import com.org.service.UserService;

@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/createUser") //--> signup
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public void addUser(@RequestBody Users newUser) {

		userService.createUser(newUser);
	}

	@GetMapping("/readAllUsers")
	public Iterable<Users> readAllUsers() {

		return userService.displayAllUser();
	}

	@PutMapping("/updateUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public void updateUser(@RequestBody Users updateUser) {

		userService.updateUser(updateUser);
	}

//	@GetMapping("/searchUser/{id}")
//	@ExceptionHandler(RecordNotFoundException.class)
//	public ResponseEntity<?> searchUserByID(@PathVariable("id") BigInteger userId) {
//
//		return userService.findUserById(userId);
//	}

	@DeleteMapping("/deleteUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public void deleteBookingByID(@PathVariable("id") BigInteger userId) {

		userService.deleteUser(userId);
	}

	//login details
	@GetMapping("/userLogin/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> userLogin(@PathVariable("id") BigInteger userId){

		return userService.userLogin(userId);
	}
}
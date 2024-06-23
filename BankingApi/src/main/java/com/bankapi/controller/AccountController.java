package com.bankapi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bankapi.Dto.AccountDto;
import com.bankapi.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping()
	 public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountdto)
	 {
		 return new ResponseEntity<>(accountService.createAccount(accountdto),HttpStatus.CREATED);
	 }
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto>getAccountById(@PathVariable Long id)
	{
		AccountDto accountbyId = accountService.getAccountbyId(id);
		return ResponseEntity.ok(accountbyId);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto>depositMoney(@PathVariable Long id,@RequestBody java.util.Map<String,Double>request)
	{
		AccountDto accountdto=accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountdto);
		
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto>withdrawMoney(@PathVariable Long id,@RequestBody java.util.Map<String,Double>request)
	{
		AccountDto accountdto=accountService.withdraw(id, request.get("amount"));
		return ResponseEntity.ok(accountdto);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String>deleteAccount(@PathVariable Long id)
	{
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is deleted successfully");
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<AccountDto>>getAllAccounts()
	{
		List<AccountDto> allAccounts = accountService.getAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}
	
	

}

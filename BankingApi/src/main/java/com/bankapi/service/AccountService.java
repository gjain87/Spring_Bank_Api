package com.bankapi.service;

import java.util.List;

import com.bankapi.Dto.AccountDto;


public interface AccountService {
	
	AccountDto createAccount(AccountDto accountdto);
	
	AccountDto getAccountbyId(Long id);
	
	AccountDto deposit(Long id, double amount);
	
	AccountDto withdraw(Long id, double amount);
	
	void deleteAccount(Long id);
	
	List<AccountDto>getAllAccounts();

}

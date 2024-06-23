package com.bankapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankapi.Dto.AccountDto;
import com.bankapi.entity.Account;
import com.bankapi.mapper.AccountMapper;
import com.bankapi.repository.AccountRepository;
import com.bankapi.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accrepo;

	@Override
	public AccountDto createAccount(AccountDto accountdto) {
		Account account=AccountMapper.mapToAccount(accountdto);
		Account savedAccount = accrepo.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountbyId(Long id) {
		Account account = accrepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account doesn't exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accrepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account doesn't exist"));
		double totalBalance=account.getBalance() + amount;
		account.setBalance(totalBalance);
		Account savedAccount = accrepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accrepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account doesn't exist"));
		if(amount>account.getBalance())
		{
			throw new RuntimeException("Insufficient balance");
		}
		double totalBalance=account.getBalance()-amount;
		account.setBalance(totalBalance);
		Account savedAccount = accrepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
		
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accrepo
				.findById(id)
				.orElseThrow(()->new RuntimeException("Account doesn't exist"));
		accrepo.deleteById(id);
		
		
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accrepo.findAll();
		List<AccountDto>accountdtos=new ArrayList<>();
		for(int i=0;i<accounts.size();i++)
		{
			accountdtos.add(AccountMapper.mapToAccountDto(accounts.get(i)));
		}
		return accountdtos;
	}

}

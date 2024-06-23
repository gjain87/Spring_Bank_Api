package com.bankapi.mapper;

import com.bankapi.Dto.AccountDto;
import com.bankapi.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountdto) {
		Account account=new Account(
				accountdto.getId(),
				accountdto.getAccountHolderName(),
				accountdto.getBalance()
				);
		return account;
		
	}
	
	public static AccountDto mapToAccountDto(Account account)
	{
		AccountDto accountdto=new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountdto;
				
	}
}

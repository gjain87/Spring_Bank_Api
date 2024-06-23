package com.bankapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankapi.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

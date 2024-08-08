package com.quocdat.java5.service;

import com.quocdat.java5.entity.Account;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccount();

    Optional<Account> getAccountByEmailAndPass(String email, String password) throws SQLException;

    Account save(Account account) throws SQLException;

    Object delete(String id) throws SQLException;

    List<Account> filter(String id, String fullname) throws SQLException;
}

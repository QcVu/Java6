package com.quocdat.java5.service.impl;

import com.quocdat.java5.entity.Account;
import com.quocdat.java5.repository.AccountRepo;
import com.quocdat.java5.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServicesImpl implements AccountService {
    final AccountRepo repo;

    @Override
    public List<Account> getAllAccount(){
        return repo.findAll();
    };

    @Override
    public Optional<Account> getAccountByEmailAndPass(String email, String password) throws SQLException{
        var result = repo.getAccountByEmailAndPass(email, password);
        return Optional.ofNullable(result);
    }

    @Override
    public Account save(Account account) throws SQLException {
        return repo.save(account);
    }

    @Override
    public Object delete(String id) throws SQLException {
        repo.deleteById(id);
        return null;
    }

    @Override
    public List<Account> filter(String id, String fullname) throws SQLException {
        return repo.filter(id, fullname);
    }

}

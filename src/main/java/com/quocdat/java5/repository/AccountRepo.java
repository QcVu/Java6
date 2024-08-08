package com.quocdat.java5.repository;

import com.quocdat.java5.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {

    @Query(value = "SELECT * FROM JAVA6_FINAL f WHERE f.EMAIL = ?1 AND f.PASSWORD = ?2", nativeQuery = true)
    Account getAccountByEmailAndPass(String email, String password);


    @Query(value = "SELECT * FROM JAVA6_FINAL f WHERE f.ID LIKE %?1% AND f.FULLNAME LIKE %?2%", nativeQuery = true)
    List<Account> filter(String id, String fullname);
}

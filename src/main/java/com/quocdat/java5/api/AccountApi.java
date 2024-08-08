package com.quocdat.java5.api;

import com.quocdat.java5.entity.Account;
import com.quocdat.java5.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/java05/account-api")
public class AccountApi {
    @Autowired
    private AccountService accServ;
    @GetMapping("/getAllAccount")
    public ResponseEntity<?> doGetAllAccount(){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", accServ.getAllAccount());
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/save")
    public ResponseEntity<?> doSave(@RequestBody Account account){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", accServ.save(account));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> doDelete(@RequestParam String id){
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("status", true);
            result.put("message", "Get All Account Success");
            result.put("data", accServ.delete(id));
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Get All Account Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/getAllAccount ", e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/login")
    public ResponseEntity<?> doGetLogin(@RequestParam("email") String email,
                                        @RequestParam("password") String password){
        Map<String, Object> result = new HashMap<>();
        try {
            var data = accServ.getAccountByEmailAndPass(email, password);
            if(!data.isEmpty()){
                result.put("status", true);
                result.put("message", "Login Success");
                result.put("data", data);
            } else {
                result.put("status", false);
                result.put("message", "Login Fail");
            }
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Login Fail");
            result.put("data", null);
            log.error("Fail When Call API /java05/account-api/login ", e);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> doFilter(@RequestParam("id") String id,
                                        @RequestParam("fullname") String fullname){
        Map<String, Object> result = new HashMap<>();
        try {
            var data = accServ.filter(id, fullname);
            if(!data.isEmpty()){
                result.put("status", true);
                result.put("message", "Filter Success");
                result.put("data", data);
            } else {
                result.put("status", false);
                result.put("message", "Filter Fail");
            }
        } catch (Exception e){
            result.put("status", false);
            result.put("message", "Filter Fail");
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

}

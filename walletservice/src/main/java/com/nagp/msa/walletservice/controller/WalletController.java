package com.nagp.msa.walletservice.controller;

import com.nagp.msa.walletservice.model.UserDetails;
import com.nagp.msa.walletservice.model.WalletDetails;
import com.nagp.msa.walletservice.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }
    @PutMapping("/add")
    public String addMoney( @RequestBody WalletDetails walletDetails){

        return walletService.addUserWalletMoney(walletDetails);
    }
    @PutMapping("/deduct")
    public String deductMoney( @RequestBody WalletDetails walletDetails){

        return walletService.deductUserWalletMoney(walletDetails);
    }
    @GetMapping("/users/money")
    public List<WalletDetails> getAllUserWalletMoney(){
        return walletService.walletMoney();
    }
    @GetMapping("/users/money/{username}")
    public WalletDetails getAllUserWalletMoney(@PathVariable("username") String userName){
        return walletService.getUserWalletMoney(userName);
    }
    @GetMapping("users/details/{username}")
    public UserDetails getUserDetails(@PathVariable("username") String userName){
        return walletService.getUserDetails(userName);
    }
}

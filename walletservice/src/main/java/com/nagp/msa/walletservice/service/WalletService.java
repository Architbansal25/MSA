package com.nagp.msa.walletservice.service;

import com.nagp.msa.walletservice.model.UserDetails;
import com.nagp.msa.walletservice.model.WalletDetails;
import com.nagp.msa.walletservice.model.WalletMoney;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;
    private String username = "arc123";
    List<WalletDetails> walletDetailsList = new ArrayList<>();

    public boolean getUserLoginStatus(String username){
//        walletDetailsList.add(new WalletDetails("arc123",1000));
//        walletDetailsList.add(new WalletDetails("test123",1000));
        String baseUrl = eurekaClient.getNextServerFromEureka("profilemanagement", false)
                .getHomePageUrl() + "profile" + "/login/status/"+ username;
        ResponseEntity<Boolean> response = restTemplate.exchange(baseUrl,HttpMethod.GET,
                null,Boolean.class);
        //System.out.println(response.getBody());
        return  response.getBody();
    }
    public List<WalletDetails> walletMoney(){
        return walletDetailsList;
    }
    public WalletDetails getUserWalletMoney(String userName){
        Optional<WalletDetails> result = walletMoney().stream()
                .filter(c -> userName.equals(c.getUsername())).findFirst();
        if(result.isPresent()){
            return result.get();
        }
        WalletDetails walletDetails = new WalletDetails();
        walletDetails.setUsername(userName);
        walletDetails.setWalletMoney(0);
        walletDetailsList.add(walletDetails);
        return walletDetails;
    }
//    public String getUserbyService(){
////        String baseUrl = eurekaClient.getNextServerFromEureka("profilemanagement", false)
////                .getHomePageUrl() + "profile" + "/login/status/"+ username;
////        System.out.println(baseUrl);
////        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
////        //ResponseEntity<UserDetails> response = restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET,null,
////          //      UserDetails.class);
////        //ResponseEntity<UserDetails> response = restTemplate.getForObject(builder.buildAndExpand().toUri(),UserDetails.)
////        //ResponseEntity<UserDetails> response = restTemplate.getForEntity(baseUrl, UserDetails.class);
////        ResponseEntity<Boolean> response = restTemplate.exchange(baseUrl,HttpMethod.GET,
////                null,Boolean.class);
////        System.out.println(response.getBody());
////        //UserDetails result = response.getBody();
////        //System.out.println(result.getName());
////        //this.walletDetails.add(new WalletDetails(result.getcUsername(),1150));
////        //System.out.println(this.walletDetails.get(0));
//
//        boolean userStatus = getUserLoginStatus("arc123");
//        if (userStatus){
//            WalletDetails tempWalletDetails = getUserWalletMoney("arc123");
//            if(tempWalletDetails.getWalletMoney()!=0){
//                tempWalletDetails.setWalletMoney(tempWalletDetails.getWalletMoney()+1000);
//                int userIndex = walletDetailsList.indexOf(tempWalletDetails);
//                walletDetailsList.set(userIndex,tempWalletDetails);
//            }
//            else {
//                tempWalletDetails.setWalletMoney(1000);
//                int userIndex = walletDetailsList.indexOf(tempWalletDetails);
//                walletDetailsList.set(userIndex,tempWalletDetails);
//            }
//        }
//        return "data return";
//    }
    public UserDetails getUserDetails(String userName){
        String baseUrl = eurekaClient.getNextServerFromEureka("profilemanagement", false)
                .getHomePageUrl() + "profile" + "/user/"+ userName;
        ResponseEntity<UserDetails> response = restTemplate.exchange(baseUrl,HttpMethod.GET,
                null,UserDetails.class);
        WalletDetails walletDetails = getUserWalletMoney(userName);
        UserDetails userDetails = response.getBody();
        WalletMoney walletMoney = new WalletMoney();
        walletMoney.setMoney(walletDetails.getWalletMoney());

        userDetails.setWalletMoney(walletMoney);
        return userDetails;
    }
    public String addUserWalletMoney(WalletDetails walletDetails){
        boolean userStatus = getUserLoginStatus(walletDetails.getUsername());
        if (userStatus){
            WalletDetails tempWalletDetails = getUserWalletMoney(walletDetails.getUsername());
            if(tempWalletDetails!=null){
                if(tempWalletDetails.getWalletMoney()!=0){
                    tempWalletDetails.setWalletMoney(tempWalletDetails.getWalletMoney()+walletDetails.getWalletMoney());
                    int userIndex = walletDetailsList.indexOf(tempWalletDetails);
                    walletDetailsList.set(userIndex,tempWalletDetails);
                }
                else {
                    tempWalletDetails.setWalletMoney(walletDetails.getWalletMoney());
                    int userIndex = walletDetailsList.indexOf(tempWalletDetails);
                    walletDetailsList.set(userIndex,tempWalletDetails);
                }
            }
            else {
                walletDetailsList.add(walletDetails);
            }
            return "money added!";
        }
        return "user needs to be loggedIN";
    }
    public String deductUserWalletMoney(WalletDetails walletDetails){
        boolean userStatus = getUserLoginStatus(walletDetails.getUsername());
        if (userStatus){
            WalletDetails tempWalletDetails = getUserWalletMoney(walletDetails.getUsername());
            if(tempWalletDetails!=null){
                if(tempWalletDetails.getWalletMoney()!=0){
                    tempWalletDetails.setWalletMoney(tempWalletDetails.getWalletMoney()-walletDetails.getWalletMoney());
                    int userIndex = walletDetailsList.indexOf(tempWalletDetails);
                    walletDetailsList.set(userIndex,tempWalletDetails);
                }
                else {
                    return "user wallet empty";
                }
            }
            else {
                return "user wallet empty";
            }
            return "money deducted!";
        }
        return "user needs to be loggedIN";
    }

}

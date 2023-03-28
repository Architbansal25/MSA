package com.nagp.msa.profilemanagement.service;

import com.nagp.msa.profilemanagement.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    List<UserDetails> userDetails = new ArrayList<>();
    public UserService() {

        userDetails.add(new UserDetails("archit123","pass","archit", 123434445,
                "meerut",false,"customer"));
        userDetails.add(new UserDetails("test123","pass","test", 123434445,
                "meerut",false,"HotelProvider"));
        userDetails.add(new UserDetails("arc123","pass","arctest", 123434445,
                "meerut",false,"FlightProvider"));

    }

    public List<UserDetails> userList(){
        return userDetails;
    }
    public UserDetails getUserByUsername(String username){
        Optional<UserDetails> result = userList().stream()
                .filter(c -> username.equals(c.getcUsername())).findFirst();
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }
    public String updateUserLoggedIN( String userName){
        UserDetails tempCustomerDetail = getUserByUsername(userName);
        int customerIndex = userDetails.indexOf(tempCustomerDetail);
        tempCustomerDetail.setLoggedIn(true);
        userDetails.set(customerIndex,tempCustomerDetail);
        return "user logged in";
    }
    public String updateUserLoggedOut( String userName){
        UserDetails tempCustomerDetail = getUserByUsername(userName);
        int customerIndex = userDetails.indexOf(tempCustomerDetail);
        tempCustomerDetail.setLoggedIn(false);
        userDetails.set(customerIndex,tempCustomerDetail);
        return "user logged out";
    }
    public boolean userLoggedStatus( String userName){
        UserDetails tempCustomerDetail = getUserByUsername(userName);
        int customerIndex = userDetails.indexOf(tempCustomerDetail);
        boolean userStatus = tempCustomerDetail.isLoggedIn();
        return userStatus;
    }
    public String getUserType(String userName){
        UserDetails tempCustomerDetail = getUserByUsername(userName);
        String userType = tempCustomerDetail.getScope();
        return userType;
    }
    public String addUser(UserDetails userDetails){
        this.userDetails.add(userDetails);
        return "user added";
    }
}

package com.example.excercise.testdatafactory.domain.model.user;

import com.example.excercise.domain.model.user.User;
import com.example.excercise.domain.model.user.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserObjectMother {

    public static User getUserOwner() {
        return new User(1, "Owner User", UserType.OWNER.getName());
    }

    public static User getUserClient() {
        return new User(2, "Client User", UserType.CLIENT.getName());
    }

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(getUserOwner());
        users.add(getUserClient());
        return users;
    }
}

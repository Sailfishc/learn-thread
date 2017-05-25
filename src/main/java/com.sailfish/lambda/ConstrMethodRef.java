package com.sailfish.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sailfish
 * @create 2017-05-18-下午7:34
 */
public class ConstrMethodRef {

    @FunctionalInterface
    interface UserFactory<U extends User> {
        U createUser(int id, String name);
    }

    static UserFactory uf = User::new;

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        for (int i = 0; i<10; i++) {
            users.add(uf.createUser(i, "sailfish" + Integer.toString(i)));
        }

        users.stream().map(User::getName).forEach(System.out::println);
    }
}

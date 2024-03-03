package com.asanchez.crud;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asanchez.crud.Services.UserService;
import com.asanchez.crud.entity.User;

public class Main {

    public static final Logger _Logger = LoggerFactory.getLogger(Main.class);
    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            if (chooseOption() < 0)
                break;
        }
    }

    public static void showMenu() {
        System.out.println("CRUD MYSQL - AUTHOR: JONATHAN ALEXIS SANCHEZ SANTOS");
        System.out.println("1.- CREATE User");
        System.out.println("2.- MODIFY User");
        System.out.println("3.- SELECT ALL Users");
        System.out.println("4.- DELETE User");
        System.out.println("Other number to exit...");
    }

    public static int chooseOption() {
        int opc;
        try {
            opc = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            return -1;
        }

        switch (opc) {
            case 1:
                insert();
                break;
            case 2:
                update();
                break;
            case 3:
                findAll();
                break;
            case 4:
                delete();
                break;
            default:
                opc = -1;
                break;
        }

        return opc;
    }

    public static void insert() {
        String name, lastname;

        System.out.println("Write Name: ");
        name = sc.nextLine();
        System.out.println("Write Lastname: ");
        lastname = sc.nextLine();

        User user = new User(name, lastname);
        if ((user = userService.save(user)) != null) {
            System.out.println(user.toString());
        } else {
            System.err.println("Fail to save in DB");
        }
    }

    public static void update() {
        Long id;
        String name, lastname;

        System.out.println("Write the User ID: ");
        id = Long.valueOf(sc.nextLine());

        User userBd = userService.findById(id);
        if (userBd == null) {
            System.err.println("User Not Found");
            return;
        }

        System.out.println("Write Name: ");
        name = sc.nextLine();
        System.out.println("Write Lastame: ");
        lastname = sc.nextLine();

        userBd.setName(name);
        userBd.setLastname(lastname);
        if ((userBd = userService.update(userBd)) != null) {
            System.out.println(userBd.toString());
        } else {
            System.err.println("Fail to save in DB");
        }
    }

    public static void findAll() {
        userService.findAll().forEach(user -> System.out.println(user));
    }

    public static void delete() {
        Long id;
        System.out.println("Write the User ID: ");
        id = Long.valueOf(sc.nextLine());

        User userBd = userService.findById(id);
        if (userBd == null) {
            System.err.println("User Not Found");
            return;
        }

        userService.deleteById(id);
        System.out.println("User Deleted");
    }
}
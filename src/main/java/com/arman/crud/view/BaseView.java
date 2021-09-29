package com.arman.crud.view;

import java.util.Scanner;

public abstract class BaseView {
    protected Scanner sc;
    protected String message;

    abstract void save();

    abstract void edit();

    abstract void delete();

    abstract void print();

    abstract void findById();

    void show(){
        boolean isExit = false;
        while (true) {
            print();
            System.out.println("---------------------------");
            System.out.println(message);
            System.out.println("---------------------------");
            String response = sc.next();
            switch (response) {
                case "1" -> save();
                case "2" -> edit();
                case "3" -> delete();
                case "4" -> print();
                case "5" -> findById();
                case "6" -> isExit = true;
                default -> System.out.println("Error");
            }
            if (isExit)
                break;
        }
    }
}

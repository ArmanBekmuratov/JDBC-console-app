package com.arman.crud.view;

import java.util.Scanner;

import static com.arman.crud.view.Message.LINE;

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
            System.out.println(LINE);
            System.out.println(message);
            System.out.println(LINE);
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

package com.arman.crud.view;

public enum Message {
    LINE("------------------------------"),
    SUCCESS_EDIT("The editing was successful"),
    SUCCESS_DELETE("The deletion was successful"),
    SUCCESS_SAVE("The saving was successful"),
    FIRST_NAME("Enter first name"),
    LAST_NAME("Enter last name"),
    SKILLS("Enter skill ID separated by \"SPACE\"");




    private final String text;

    Message(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

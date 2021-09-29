package com.arman.crud.view;

import java.util.Scanner;

import static com.arman.crud.view.Message.LINE;

public class ConsoleRunner {
    private final BaseView  skillView;
    private final BaseView developerView;
    private final BaseView teamView;
    private final static Scanner sc = new Scanner(System.in);

    public ConsoleRunner() {
         skillView = new SkillView(sc);
         developerView = new DeveloperView(sc);
         teamView = new TeamView(sc);
    }

    public void run()  {
        boolean isExit = false;
        while (true) {
            System.out.println(LINE);
            String menuMessage = """
                    Select the entity you want to work with:\s
                    1. Developer
                    2. Skill
                    3. Team
                    4. Exit""";
            System.out.println(menuMessage);
            System.out.println(LINE);
            String response = sc.next();
            switch (response) {
                case "1" -> developerView.show();
                case "2" -> skillView.show();
                case "3" -> teamView.show();
                case "4" -> isExit = true;
                default -> System.out.println("Error");
            }
            if (isExit)
                break;
        }
        sc.close();
    }
}

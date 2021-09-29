package com.arman.crud.view;

import com.arman.crud.controller.DeveloperController;
import com.arman.crud.model.Developer;
import java.util.Scanner;

import static com.arman.crud.view.Message.*;

public class DeveloperView extends BaseView {
    private static final DeveloperController developerController = DeveloperController.getInstance();

    public DeveloperView(Scanner sc) {
        this.sc = sc;
        this.message = """
                Choose an action on the developer :
                 1. Create
                 2. Edit
                 3. Delete
                 4. List
                 5. Find developer by ID
                 6. Exit""";
    }

    @Override
    void save() {
        System.out.println(LINE);
        var saveMenuMessage = "Developer creation.\n";
        System.out.println(saveMenuMessage);
        System.out.println(FIRST_NAME);
        var firstname = sc.nextLine();
        System.out.println(LAST_NAME);
        var lastname = sc.nextLine();
        System.out.println(SKILLS);
        var skills = sc.nextLine();
        var idSkills = skills.split(" ");
        var developer = developerController.save(firstname, lastname, idSkills);
        System.out.println(developer.toString());
        System.out.println(SUCCESS_SAVE);
        System.out.println(LINE);
    }

    @Override
    void edit() {
        System.out.println(LINE);
        var editMenuMessage = "Developer editing.\n" +
                "Enter ID";
        System.out.println(editMenuMessage);
        var id = sc.nextLine();
        System.out.println(FIRST_NAME);
        var firstname = sc.nextLine();
        System.out.println(LAST_NAME);
        var lastname = sc.nextLine();
        System.out.println(SKILLS);
        var skills = sc.nextLine();
        var idSkills = skills.split(" ");
        var update = developerController.update(id, firstname, lastname, idSkills);
        if (update) {
            System.out.println(SUCCESS_EDIT);
        }
        System.out.println(LINE);
    }

    @Override
    void delete() {
        System.out.println(LINE);
        String deleteMenuMessage = "Developer deleting\n" +
                "Enter ID";
        System.out.println(deleteMenuMessage);
        var id = sc.nextLine();
        if (developerController.deleteById(id)) {
            System.out.println(SUCCESS_DELETE);
        }
        System.out.println(LINE);
    }

    @Override
    void print() {
        System.out.println(LINE);
        var printMenuMessage = "List of developers:\n";
        System.out.println(printMenuMessage);
        var developers = developerController.findAll();
        developers.forEach(developer -> System.out.println(developer.toString()));
        System.out.println(LINE);
    }

    @Override
    void findById() {
        System.out.println(LINE);
        System.out.println("Enter Developer's ID");
        var id = sc.nextLine();
        var developer = developerController.findById(id);
        System.out.println(developer.toString());
        System.out.println(LINE);
    }

}

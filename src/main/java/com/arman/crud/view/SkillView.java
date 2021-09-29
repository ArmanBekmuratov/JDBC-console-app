package com.arman.crud.view;

import com.arman.crud.controller.SkillController;

import java.util.Scanner;

import static com.arman.crud.view.Message.*;

public class SkillView extends BaseView {
    private static final SkillController skillController = SkillController.getInstance();

    public SkillView(Scanner sc) {
        this.sc = sc;
        this.message = """
                Choose an action on developer skills:
                 1. Create
                 2. Edit
                 3. Delete
                 4. List
                 5. Find skill by ID
                 6. Exit""";
    }

    @Override
    void save() {
        System.out.println(LINE);
        var createMenuMessage = "Skill creation.\n" +
                "Enter a name of the skill";
        System.out.println(createMenuMessage);
        var name = sc.next();
        skillController.save(name);
        System.out.println(SUCCESS_SAVE);
        System.out.println(LINE);
    }

    @Override
    void edit() {
        System.out.println(LINE);
        var editMenuMessage = "Skill editing.\n" +
                "Enter ID";
        System.out.println(editMenuMessage);
        var id = sc.nextLine();
        System.out.println(LINE);
        var name = sc.nextLine();
        skillController.update(id, name);
        System.out.println(SUCCESS_EDIT);
        System.out.println(LINE);
    }

    @Override
    void delete() {
        System.out.println(LINE);
        var deleteMenuMessage = "Skill deleting\n" +
                "Enter ID";
        System.out.println(deleteMenuMessage);
        var id = sc.nextLine();
        skillController.deleteById(id);
        System.out.println(SUCCESS_DELETE);
        System.out.println(LINE);
    }

    @Override
    void print() {
        System.out.println(LINE);
        var skillList = skillController.findAll();
        skillList.forEach(skill -> System.out.println(skill.toString()));
        System.out.println(LINE);
    }

    @Override
    void findById() {
        System.out.println(LINE);
        System.out.println("Enter skill's ID");
        var id = sc.nextLine();
        var skill = skillController.findById(id);
        System.out.println(skill.toString());
        System.out.println(LINE);
    }
}

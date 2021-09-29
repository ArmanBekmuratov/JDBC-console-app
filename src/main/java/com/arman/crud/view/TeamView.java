package com.arman.crud.view;

import com.arman.crud.controller.TeamController;

import java.util.Scanner;

import static com.arman.crud.view.Message.*;

public class TeamView extends BaseView{
    private static final TeamController teamController = TeamController.getInstance();

    public TeamView(Scanner sc) {
        this.sc = sc;
        this.message = """
                Choose an action on team:
                 1. Create
                 2. Edit
                 3. Delete
                 4. List
                 5. Find team by ID
                 6. Exit""";
    }

    @Override
    void save() {
        System.out.println(LINE);
        System.out.println("Enter team's name");
        var name = sc.nextLine();
        System.out.println("Enter developer's ID separated by \"SPACE\"");
        var developer = sc.nextLine();
        var idDevelopers = developer.split(" ");
        var team = teamController.save( name, idDevelopers);
        System.out.println(team.toString());
        System.out.println(SUCCESS_SAVE);
        System.out.println(LINE);
    }

    @Override
    void edit() {
        System.out.println(LINE);
        System.out.println("Enter Team's ID");
        var id = sc.nextLine();
        System.out.println("Enter developer's ID separated by \"SPACE\"");
        var developers = sc.nextLine();
        var idDevelopers = developers.split(" ");
        var team = teamController.update(id, idDevelopers);
        if (team) {
            System.out.println(SUCCESS_EDIT);
        }
        System.out.println(LINE);
    }

    @Override
    void delete() {
        System.out.println(LINE);
        System.out.println("Enter Team's ID");
        var id = sc.nextLine();
        teamController.deleteById(id);
        System.out.println("The status is set to 'deleted'");
        System.out.println(SUCCESS_DELETE);
        System.out.println(LINE);
    }

    @Override
    void print() {
        System.out.println(LINE);
        var printMenuMessage = "List of teams:\n";
        System.out.println(printMenuMessage);
        var teams = teamController.findAll();
        teams.forEach(team -> System.out.println(team.toString()));
        System.out.println(LINE);
    }

    @Override
    void findById() {
        System.out.println(LINE);
        System.out.println("Enter team's ID");
        var id = sc.nextLine();
        var team = teamController.findById(id);
        System.out.println(team.toString());
        System.out.println(LINE);
    }
}

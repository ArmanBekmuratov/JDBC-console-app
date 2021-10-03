
package com.arman.crud.view;

import com.arman.crud.controller.TeamController;
import com.arman.crud.model.Team;

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
        var name = sc.next();
        System.out.println("Enter developer's ID separated by \"SPACE\"");
        var developer = sc.next();
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
        var id = sc.nextInt();
        System.out.println("Enter developer's ID separated by \"SPACE\"");
        var developers = sc.next();
        var idDevelopers = developers.split(" ");
        var team = teamController.update(id, idDevelopers);
        System.out.println(team.toString());
        System.out.println(LINE);
    }

    @Override
    void delete() {
        System.out.println(LINE);
        System.out.println("Enter Team's ID");
        var id = sc.nextInt();
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
        var id = sc.nextInt();
        var team = teamController.findById(id);
        System.out.println(team.toString());
        System.out.println(LINE);
    }
}


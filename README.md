# JDBC-console-app
CrudConsole Description You need to implement a CRUD console application that has the following entities:

Team Developer Skill TeamStatus (enum ACTIVE, DELETED)

Team -> List developers + Developer developer

Developer -> List skills + Skill skill

You must use Postgres as DBMS and there tables: team, developer, skill, developer_team, skill_developer

The user in the console must be able to create, retrieve, edit and delete data.

Layers:

main.java.com.arman.crud.model - POJO classes

main.java.com.arman.crud.repo - classes that implement access to database using JDBC

main.java.com.arman.crud.controller - handling requests from the user

main.java.com.arman.crud.view - all data required to work with the console

Start-up instructions

Download the app

Go to the repository at the link https://github.com/ArmanBekmuratov/JDBC-console-app

Click the green "Code" button at the top right of the page.

Unpack with archiver

Open project in intellij idea

Run class main.java.com.arman.crud.Main

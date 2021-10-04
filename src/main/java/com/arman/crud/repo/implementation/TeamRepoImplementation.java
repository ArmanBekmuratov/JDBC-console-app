package com.arman.crud.repo.implementation;

import com.arman.crud.model.Developer;
import com.arman.crud.model.Skill;
import com.arman.crud.model.Team;
import com.arman.crud.model.TeamStatus;
import com.arman.crud.repo.GenericRepo;
import com.arman.crud.repo.TeamRepo;
import com.arman.crud.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamRepoImplementation implements TeamRepo {
    private static final TeamRepoImplementation INSTANCE = new TeamRepoImplementation();
    private final Connection connection = ConnectionManager.get();
    private static final String DELETE_BY_ID_SQL = """
            UPDATE team
            SET team_status = 'deleted'
            WHERE team_id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT team_id, team_name, team_status
            FROM team
            """;
    private static final String FIND_DEVELOPERS_OF_TEAM =
            "SELECT DISTINCT d.developer_id, d.first_name, d.last_name\n" +
            "FROM developer d\n" +
            "    LEFT JOIN developer_team dt on d.developer_id = dt.developer_id\n" +
            "WHERE dt.developer_id = ";
    private static final String FIND_SKILLS_OF_DEVELOPERS_SQL =
            "SELECT DISTINCT s.skill_id, s.skill_name " +
                    "FROM skill AS s " +
                    "LEFT JOIN skill_developer sd on s.skill_id = sd.skill_id" +
                    " WHERE sd.developer_id = ";
    private static final String SAVE_TEAM_SQL = """
            INSERT INTO team(team_name, team_status) 
            VALUES (?, ?::team_status) 
            """;
    private static final String SAVE_DEVELOPERS_OF_TEAM = """
            INSERT INTO developer_team(developer_id, team_id) 
            VALUES (?,?)
            """;
    private static final String UPDATE_TEAM_SQL = """
            UPDATE team
            SET team_name = ?,
                team_status = ?::team_status
            WHERE team_id = ?
            """;
    private static final String DELETE_DEVELOPERS_SQL = """
            DELETE FROM developer_team 
            WHERE team_id = ?
            """;

    private TeamRepoImplementation() {
    }
    @Override
    public Optional<Team> findById(Integer id) {
        if (id < 1) {
            throw new RuntimeException("ID cannot be less than 1");
        }
        List<Team> teams = this.findAll();
        return teams.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    @Override
    @SneakyThrows
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        try (var findTeamsStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSetTeam = findTeamsStatement.executeQuery();
            while (resultSetTeam.next()) {
                Integer teamId = resultSetTeam.getInt("team_id");
                String teamName = resultSetTeam.getString("team_name");
                TeamStatus teamStatus = TeamStatus.valueOf(resultSetTeam.getString("team_status"));
                List<Developer> developers = new ArrayList<>();
                String sqlFindDevelopers = FIND_DEVELOPERS_OF_TEAM + teamId ;
                findDevelopersOfTeam(developers, sqlFindDevelopers);
                addNewTeamToList(teams, teamId, teamName, teamStatus, developers);
            }
        }
        return teams;
    }

    @Override
    @SneakyThrows
    public Team save(Team team) {
        try (var saveTeamStatement = connection.prepareStatement(SAVE_TEAM_SQL, Statement.RETURN_GENERATED_KEYS);
             var saveDevelopersStatement = connection.prepareStatement(SAVE_DEVELOPERS_OF_TEAM)) {
            saveTeamStatement.setString(1, team.getName());
            saveTeamStatement.setString(2, team.getTeamStatus().name());
            saveTeamStatement.executeUpdate();

            var generatedKeys = saveTeamStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                team.setId(generatedKeys.getInt("team_id"));
            }

            for (Developer developer : team.getDevelopers()) {
                saveDevelopersStatement.setInt(1, developer.getId());
                saveDevelopersStatement.setInt(2, generatedKeys.getInt("team_id"));
                saveDevelopersStatement.executeUpdate();
            }
        }
        return team;
    }

    @Override
    @SneakyThrows
    public Team update(Team team) {
        try (var updateTeamStatement = connection.prepareStatement(UPDATE_TEAM_SQL);
             var deleteDevelopersStatement = connection.prepareStatement(DELETE_DEVELOPERS_SQL);
             var saveDeveloperStatement = connection.prepareStatement(SAVE_DEVELOPERS_OF_TEAM)) {
            updateTeamStatement.setString(1, team.getName());
            updateTeamStatement.setString(2, team.getTeamStatus().name());
            updateTeamStatement.setInt(3, team.getId());
            updateTeamStatement.executeUpdate();

            deleteDevelopersStatement.setInt(1, team.getId());
            updateTeamStatement.executeUpdate();

            for (Developer developer : team.getDevelopers()) {
                saveDeveloperStatement.setInt(1, developer.getId());
                saveDeveloperStatement.setInt(2, team.getId());
                saveDeveloperStatement.executeUpdate();
            }
        }
        return team;
    }

    @Override
    @SneakyThrows
    public boolean deleteById(Integer id) {
        if (id < 1) {
            throw new RuntimeException("ID cannot be less than 1");
        }
        try (var preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public static TeamRepoImplementation getInstance() {
        return INSTANCE;
    }

    private void findDevelopersOfTeam(List<Developer> developers, String sqlFindDevelopers) throws SQLException {
        try (var findDevelopersStatement = connection.prepareStatement(sqlFindDevelopers)) {
            var resultSetDeveloper = findDevelopersStatement.executeQuery();
            while (resultSetDeveloper.next()) {
                var developer = new Developer();
                developer.setId(resultSetDeveloper.getInt("developer_id"));
                developer.setFirstName(resultSetDeveloper.getString("first_name"));
                developer.setLastName(resultSetDeveloper.getString("last_name"));
                String sqlFindSkills = FIND_SKILLS_OF_DEVELOPERS_SQL + resultSetDeveloper.getInt("developer_id");
                findSkillsOfDeveloper(developer, sqlFindSkills);
                developers.add(developer);
            }
        }
    }

    private void addNewTeamToList(List<Team> teams, Integer teamId, String teamName, TeamStatus teamStatus, List<Developer> developers) {
        teams.add(new Team(
                teamId,
                teamName,
                developers,
                teamStatus
        ));
    }

    private void findSkillsOfDeveloper(Developer developer, String sqlFindSkills) throws SQLException {
        try (var findSkillsStatement = connection.prepareStatement(sqlFindSkills)) {
            List<Skill> skills = new ArrayList<>();
            var resultSetSkills = findSkillsStatement.executeQuery();
            while (resultSetSkills.next()) {
                skills.add(new Skill(
                        resultSetSkills.getInt("skill_id"),
                        resultSetSkills.getString("skill_name")
                ));
                developer.setSkillList(skills);
            }
        }
    }
}

package com.arman.crud.repo.implementation;
import com.arman.crud.model.Developer;
import com.arman.crud.model.Skill;
import com.arman.crud.repo.GenericRepo;
import com.arman.crud.util.ConnectionManager;
import lombok.SneakyThrows;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperRepoImplementation implements GenericRepo<Developer, Integer> {

    private static final DeveloperRepoImplementation INSTANCE = new DeveloperRepoImplementation();
    private final Connection connection = ConnectionManager.get();
    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM developer
            WHERE developer_id = ?
            """;
    private static final String SAVE_DEVELOPERS_SQL = """
            INSERT INTO developer(first_name, last_name)
            VALUES (?,?)
            """;
    private static final String SAVE_SKILLS_OF_DEVELOPER_SQL = """
            INSERT INTO skill_developer(skill_id, developer_id) 
            VALUES (?,?)
            """;
    private static final String UPDATE_DEVELOPER_SQL = """
            UPDATE developer
            SET first_name = ?,
                last_name = ?
            WHERE developer_id = ?   
            """;
    private static final String DELETE_SKILLS_SQL = """
            DELETE 
            FROM skill_developer
            WHERE developer_id = ?
            """;
    private static final String FIND_ALL_DEVELOPERS_SQL = """
            SELECT developer_id, first_name, last_name
            FROM developer 
            """;
    private static final String FIND_SKILLS_OF_DEVELOPERS_SQL =
                    "SELECT DISTINCT s.skill_id, s.skill_name " +
                    "FROM skill AS s " +
                    "LEFT JOIN skill_developer sd on s.skill_id = sd.skill_id" +
                            " WHERE sd.developer_id = ";



    private DeveloperRepoImplementation() {}

    @Override
    @SneakyThrows
    public boolean update(Developer developer) {
        try (var developerUpdateStatement = connection.prepareStatement(UPDATE_DEVELOPER_SQL);
             var skillsDeleteStatement = connection.prepareStatement(DELETE_SKILLS_SQL);
             var skillsSaveStatement = connection.prepareStatement(SAVE_SKILLS_OF_DEVELOPER_SQL)) {
            developerUpdateStatement.setString(1, developer.getFirstName());
            developerUpdateStatement.setString(2, developer.getLastName());
            developerUpdateStatement.setInt(3, developer.getId());
            developerUpdateStatement.executeUpdate();

            skillsDeleteStatement.setInt(1, developer.getId());
            skillsDeleteStatement.executeUpdate();

            for(Skill skill : developer.getSkillList()) {
                skillsSaveStatement.setInt(1, skill.getId());
                skillsSaveStatement.setInt(2, developer.getId());
                skillsSaveStatement.executeUpdate();
            }
        }
        return true;
    }

    @Override
    @SneakyThrows
    public List<Developer> findAll() {
        List<Developer> developers = new ArrayList<>();
        try (var findAllStatement = connection.prepareStatement(FIND_ALL_DEVELOPERS_SQL)) {
             var resultSetOfDeveloper = findAllStatement.executeQuery();
             while (resultSetOfDeveloper.next()) {
                 List<Skill> skills = new ArrayList<>();
                 int idDeveloper = resultSetOfDeveloper.getInt("developer_id");
                 String firstName = resultSetOfDeveloper.getString("first_name");
                 String lastName = resultSetOfDeveloper.getString("last_name");
                 String sql = FIND_SKILLS_OF_DEVELOPERS_SQL + idDeveloper;
                 findSkillsOfDeveloper(skills, sql);
                 developers.add(new Developer(
                         idDeveloper,
                         firstName,
                         lastName,
                         skills
                 ));
             }
            return developers;
            }
        }

    @Override
    @SneakyThrows
    public Optional<Developer> findById(Integer id) {
        List<Developer> developers = this.findAll();
        return developers.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    @Override
    @SneakyThrows
    public Developer save(Developer developer) {
        try (var saveDeveloperStatement = connection.prepareStatement(SAVE_DEVELOPERS_SQL, Statement.RETURN_GENERATED_KEYS);
             var saveSkillStatement = connection.prepareStatement(SAVE_SKILLS_OF_DEVELOPER_SQL)) {
           saveDeveloperStatement.setString(1, developer.getFirstName());
           saveDeveloperStatement.setString(2, developer.getLastName());
           saveDeveloperStatement.executeUpdate();
            var generatedKeys = saveDeveloperStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                developer.setId(generatedKeys.getInt("developer_id"));
            }

            for (Skill s : developer.getSkillList()) {
                    saveSkillStatement.setInt(1, s.getId());
                    saveSkillStatement.setInt(2,generatedKeys.getInt("developer_id"));
                    saveSkillStatement.executeUpdate();
            }
           return developer;
        }
    }

    @Override
    @SneakyThrows
    public boolean deleteById(Integer id) {
        try (var preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            return  preparedStatement.executeUpdate() > 0;
        }
    }

    public static DeveloperRepoImplementation getInstance() {
        return INSTANCE;
    }


    private void findSkillsOfDeveloper(List<Skill> skills, String sql) throws SQLException {
        try (var findSkillsOfDeveloperStatement = connection.prepareStatement(sql)) {
            var resultSetOfSkills = findSkillsOfDeveloperStatement.executeQuery();
            while (resultSetOfSkills.next()) {
                Skill skill = new Skill();
                skill.setId(resultSetOfSkills.getInt("skill_id"));
                skill.setName(resultSetOfSkills.getString("skill_name"));
                skills.add(skill);
            }
        }
    }
}


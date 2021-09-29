package com.arman.crud.repo.implementation;
import com.arman.crud.model.Skill;
import com.arman.crud.repo.GenericRepo;
import com.arman.crud.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkillRepoImplementation implements GenericRepo<Skill, Integer> {

    private static final SkillRepoImplementation INSTANCE = new SkillRepoImplementation();
    private  final Connection connection = ConnectionManager.get();

    private static final String FIND_ALL_SQL = """
            SELECT skill_id,
                   skill_name
            FROM skill
            """;
    private static final String SAVE_SQL = """
            INSERT INTO skill(skill_name)
            VALUES (?)
            """;
    private static final String DELETE_SQL = """
            DELETE FROM skill
            WHERE skill_id = ?
            """;
    private static final String UPDATE_SQL = """
            UPDATE skill 
            SET skill_name = ?
            WHERE skill_id = ?
            """;
    private SkillRepoImplementation() {
    }

    @Override
    @SneakyThrows
    public Optional<Skill> findById(Integer id) {
        List<Skill> skills = this.findAll();
        return skills.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    @Override
    @SneakyThrows
    public List<Skill> findAll() {
        try (var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Skill> skills = new ArrayList<>();
            while (resultSet.next()) {
                skills.add(buildSkill(resultSet));
            }
            return skills;
        }
    }

    @Override
    @SneakyThrows
    public Skill save(Skill skill) {
        try (var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, skill.getName());

        preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt("skill_id"));
            }
        }
        return skill;
    }

    @Override
    @SneakyThrows
    public boolean update(Skill skill) {
        try (var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());

            return preparedStatement.executeUpdate() > 0;
        }

    }

    @Override
    @SneakyThrows
    public boolean deleteById(Integer id) {
        try (var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id);
           return preparedStatement.executeUpdate() > 0;
        }
    }

    public static SkillRepoImplementation getInstance() {
        return INSTANCE;
    }

    private Skill buildSkill(ResultSet resultSet) throws SQLException {
        return new Skill(
                resultSet.getInt("skill_id"),
                resultSet.getString("skill_name")
        );
    }
}

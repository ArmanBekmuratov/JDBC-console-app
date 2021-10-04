CREATE TABLE IF NOT EXISTS skill_developer(
       skill_id INT REFERENCES skill(skill_id),
       developer_id INT REFERENCES developer(developer_id),
       PRIMARY KEY (skill_id,developer_id)
);
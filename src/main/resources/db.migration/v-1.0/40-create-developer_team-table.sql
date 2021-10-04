CREATE TABLE IF NOT EXISTS developer_team(
    developer_id INT REFERENCES developer(developer_id),
    team_id INT REFERENCES team(team_id),
    PRIMARY KEY (developer_id,team_id)
);
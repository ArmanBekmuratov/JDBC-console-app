CREATE TABLE IF NOT EXISTS team(
   team_id SERIAL PRIMARY KEY ,
   team_name VARCHAR(128) NOT NULL UNIQUE ,
   team_status team_status

);
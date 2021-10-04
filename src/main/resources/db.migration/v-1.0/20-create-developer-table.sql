CREATE TABLE IF NOT EXISTS developer(
  developer_id SERIAL PRIMARY KEY ,
  first_name VARCHAR(128) NOT NULL ,
  last_name VARCHAR(128) NOT NULL,
  UNIQUE (first_name, last_name)
);
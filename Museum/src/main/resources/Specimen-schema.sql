DROP TABLE IF EXISTS `specimen` CASCADE;
CREATE TABLE specimen (
    id BIGINT AUTO_INCREMENT NOT NULL,
    date_arrived DATE,
    description VARCHAR(255),
    latin_name VARCHAR(255) NOT NULL,
    origin VARCHAR(255) NOT NULL,
    storage_location VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
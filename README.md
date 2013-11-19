
    CREATE DATABASE querydsl CHARACTER SET utf8 COLLATE utf8_swedish_ci;
    CREATE USER 'querydsl'@'localhost' IDENTIFIED BY 'querydsl';
    GRANT ALL PRIVILEGES ON querydsl.* TO 'querydsl'@'localhost';


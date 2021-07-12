DROP DATABASE if exists demo_oauth_jwt;
CREATE DATABASE demo_oauth_jwt;
USE demo_oauth_jwt;

create table tb_user (
    id       int unsigned not null auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

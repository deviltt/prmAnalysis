create database if not exists oa1;
use oa1;
create table if not exists user (
  id       int          not null auto_increment primary key,
  username varchar(255) not null,
  password varchar(255) not null
)
  engine = innodb
  charset = utf8;

insert into user (username, password) VALUES ('tt', '123456'),
  ('ac', '123456');

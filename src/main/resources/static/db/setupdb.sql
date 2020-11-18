drop user if exist 'petuser'@'localhost'
CREATE user 'petuser'@'localhost' identified by 'petuser123';
grant all privileges on petstoredb.* to 'petuser'@'localhost';
flush privileges;

drop database if exists petstoredb;
create database petstoredb;
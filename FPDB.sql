-- drop database if exists FPDB;
create database FPDB;
use FPDB;

create table UserInfo (
	userId varchar(25) primary key not null,
    -- internalId int(10) auto_increment not null,
    givenName varchar(25) not null,
    familyName varchar(25) not null,
    pictureUrl varchar(200) not null,
    email varchar(50) not null,
    useTwitter boolean not null,
    useGmail boolean not null,
    useWeather boolean not null,
    useCalendar boolean not null,
    useYouTube boolean not null,
    useStock boolean not null,
    accessToken varchar(300)
);


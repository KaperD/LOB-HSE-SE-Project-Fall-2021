--liquibase formatted sql

--changeset KaperD:create-table-player
create table player (
   id serial primary key,
   name text not null,
   country text not null,
   position text not null,
   height int not null,
   leadingFoot text not null,
   goals int not null,
   saves int not null,
   teamId int references team
);
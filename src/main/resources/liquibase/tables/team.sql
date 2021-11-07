--liquibase formatted sql

--changeset KaperD:create-table-team
create table team (
    id serial primary key,
    name text not null,
    country text not null,
    league text not null,
    homeStadium text not null,
    generalSponsor text not null,
    coachName text not null
);
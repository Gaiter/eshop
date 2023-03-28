DROP TABLE IF EXISTS event;

create table event
(
    id         bigint primary key,
    event_type int          not null,
    body       varchar(250) not null,
    constraint event_pk
        unique (id)
);
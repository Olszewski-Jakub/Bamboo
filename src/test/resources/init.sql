drop table if exists users;

create table users
(
    is_administrator boolean,
    id               bigint not null,
    email            varchar(255),
    name             varchar(255),
    surname          varchar(255),
    uid              varchar(255),
    primary key (id)
);


drop table if exists panda_device;
create table panda_device
(
    status   boolean,
    id       bigint not null,
    owner    bigint,
    api_key  varchar(255),
    location varchar(255),
    name     varchar(255),
    uuid     varchar(255),
    primary key (id)
);

drop table if exists api_keys;
create table api_keys
(
    active  boolean,
    created timestamp(6),
    id      bigint not null,
    owner   bigint,
    panda   bigint,
    key     varchar(255),
    primary key (id)
);

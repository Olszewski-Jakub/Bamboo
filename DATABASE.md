# Databse Schema

## Table: Users

```postgresql
create table users
(
    is_administrator boolean,
    id               bigint not null
        primary key,
    email            varchar(255),
    name             varchar(255),
    surname          varchar(255),
    uid              varchar(255)
);
```

## Table: Panda Device

```postgresql
create table panda_device
(
    status   boolean,
    id       bigint not null
        primary key,
    owner    bigint,
    api_key  varchar(255),
    location varchar(255),
    name     varchar(255),
    uuid     varchar(255)
);
```

## Table: Panda Status

```postgresql
create table panda_status
(
    status          smallint
        constraint panda_status_status_check
            check ((status >= 0) AND (status <= 2)),
    id              bigint not null
        primary key,
    last_connection timestamp(6),
    uuid            varchar(255)
);
```

## Table: Api Keys

```postgresql

create table api_keys
(
    active  boolean,
    created timestamp(6),
    id      bigint not null
        primary key,
    owner   bigint,
    panda   bigint,
    key     varchar(255)
);

```

## Table: Data Packets

```postgresql
create table data_packets
(
    date         date,
    people_count integer not null,
    time         time(6),
    id           bigint  not null
        primary key,
    panda_id     bigint,
    day_of_week  varchar(255)
);
```
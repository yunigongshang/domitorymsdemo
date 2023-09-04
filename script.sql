create table absent
(
    id                 int auto_increment
        primary key,
    building_id        int         null,
    dormitory_id       int         null,
    student_id         int         null,
    dormitory_admin_id int         null,
    create_date        varchar(20) null,
    reason             varchar(20) null
);

create table building
(
    id           int auto_increment
        primary key,
    name         varchar(50)   null,
    introduction varchar(1000) null,
    admin_id     int           null
);

create table dormitory
(
    id          int auto_increment
        primary key,
    building_id int         null,
    name        varchar(20) null,
    type        int         null,
    available   int         null,
    telephone   varchar(20) null
);

create table dormitory_admin
(
    id        int auto_increment
        primary key,
    username  varchar(20) null,
    password  varchar(20) null,
    name      varchar(20) null,
    gender    varchar(10) null,
    telephone varchar(20) null
);

create table moveout
(
    id           int auto_increment
        primary key,
    student_id   varchar(11) null,
    dormitory_id varchar(50) null,
    reason       varchar(11) null,
    create_date  varchar(20) null
);

create table student
(
    id           int auto_increment
        primary key,
    number       varchar(11) null,
    name         varchar(20) null,
    gender       varchar(20) null,
    dormitory_id int         null,
    state        varchar(20) null,
    create_date  varchar(20) null
);

create table system_admin
(
    id        int auto_increment
        primary key,
    username  varchar(20) null,
    password  varchar(20) null,
    name      varchar(20) null,
    telephone varchar(20) null
);



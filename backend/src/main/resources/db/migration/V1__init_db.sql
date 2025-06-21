create table User
(
    id         bigint        not null auto_increment primary key,
    first_name nvarchar(50)  not null,
    last_name  nvarchar(50)  not null,
    sex        bit           not null,
    birth_date date          not null,
    phone      nvarchar(25)  not null,
    address    nvarchar(300),
    username   nvarchar(50)  not null unique,
    email      nvarchar(100) not null unique,
    password   nvarchar(100) not null,
    created_at datetime      not null default current_timestamp,
    updated_at datetime      not null default current_timestamp on update current_timestamp
)
create table patients(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,
    phone_number varchar(20) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zip_code varchar(9) not null,
    complement varchar(100),
    number varchar(20),
    state char(2) not null,
    city varchar(100) not null,

    primary key(id)

);

DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS resource CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS newsfeed CASCADE;

CREATE TABLE "user"(
    idUser SERIAL PRIMARY KEY,
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    shortDescription varchar(255),
    phone varchar(255) ,
    photo bytea,
    registerDate timestamp without time zone NOT NULL 
    
);

create table category(
    idCategory SERIAL PRIMARY KEY ,
    name varchar(255) NOT NULL
    );

create table resource(
    idResource SERIAL PRIMARY KEY,
    idCategory int NOT NULL,
    title varchar(255) NOT NULL,
    idUser int  NOT NULL,
    type Varchar(255) ,
    photo bytea,
    shortDescription varchar(255) ,
    foreign key (idUser) references "user"(idUser),
    foreign key (idCategory) references category (idCategory),
    tags jsonb,
    registerDate timestamp without time zone NOT NULL 
    );   

ALTER TABLE "user" ADD CONSTRAINT user_u_number_key UNIQUE (username);
ALTER TABLE "user" ADD CONSTRAINT user_u_email UNIQUE (email);
ALTER TABLE category ADD CONSTRAINT category_name_key UNIQUE (name);
 
CREATE TABLE USERS (
	id bigserial not null primary key,
	name varchar(200) not null,
	email varchar(500) not null,
	password varchar(100) not null,
	birthday timestamp not null,
    date_create timestamp not null,
    date_change timestamp
);
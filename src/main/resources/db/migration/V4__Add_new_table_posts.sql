CREATE TABLE POSTS (
    id bigserial not null primary key,
    post_text varchar(4000) not null,
    date_create timestamp not null,
    date_change timestamp,
    user_id bigint not null references USERS(id)
);
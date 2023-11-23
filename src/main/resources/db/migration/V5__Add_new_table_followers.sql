CREATE TABLE FOLLOWERS (
    id bigserial not null primary key,
    date_create timestamp not null,
    date_change timestamp,
    user_id bigint not null references USERS(id),
    follower_id bigint not null references USERS(id)
);
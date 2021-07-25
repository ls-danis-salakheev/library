create table books
(
    id    bigserial primary key,
    title varchar(256) not null unique,
    year  int,
    pages int check ( pages > 0)
);

create table authors
(
    id         bigserial primary key,
    name       varchar(128) not null,
    birth_date date,
    book_id    bigint references books(id)
);
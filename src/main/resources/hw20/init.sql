CREATE TABLE author
(
    id integer not null,
    first_name varchar(63) not null,
    last_name varchar(63) not null,
    primary key (id)
);
ALTER TABLE author OWNER TO postgres;

CREATE TABLE book
(
    id integer not null,
    author_id integer,
    title varchar(255) not null,
    primary key (id),
    constraint fk_cbe5a331f675f31b foreign key (author_id) references author
);
ALTER TABLE book OWNER TO postgres;
CREATE INDEX idx_cbe5a331f675f31b ON book (author_id);

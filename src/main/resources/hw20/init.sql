-- auto-generated definition
CREATE SEQUENCE author_id_seq;
ALTER SEQUENCE author_id_seq OWNER TO postgres;
-- create table
CREATE TABLE author
(
    id integer NOT NULL,
    first_name varchar(63) NOT NULL,
    last_name varchar(63) NOT NULL,
    PRIMARY KEY (id)
);
ALTER TABLE author OWNER TO postgres;

-- auto-generated definition
CREATE SEQUENCE book_id_seq;
ALTER SEQUENCE book_id_seq OWNER TO postgres;
-- create table
CREATE TABLE book
(
    id integer NOT NULL,
    author_id integer,
    title varchar(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_cbe5a331f675f31b FOREIGN KEY (author_id) REFERENCES author
);
ALTER TABLE book OWNER TO postgres;
CREATE INDEX idx_cbe5a331f675f31b ON book (author_id);

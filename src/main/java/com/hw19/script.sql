CREATE TABLE "user"
(
    id integer not null,
    name varchar(63) not null,
    sur_name varchar(63) not null,
    date_of_registration timestamp(0) not null,
    primary key (id)
);
COMMENT ON COLUMN "user".date_of_registration IS '(DC2Type:datetime_immutable)';
ALTER TABLE "user" OWNER TO postgres;

CREATE TABLE wallet
(
    id integer not null,
    user_id integer not null,
    currency varchar(7) not null,
    amount integer not null,
    created_at timestamp(0) not null,
    primary key (id),
    constraint fk_7c68921fa76ed395 foreign key (user_id) references "user"
);
COMMENT ON COLUMN wallet.created_at IS '(DC2Type:datetime_immutable)';
ALTER TABLE wallet OWNER TO postgres;
CREATE INDEX idx_7c68921fa76ed395 ON wallet (user_id);


INSERT INTO "user" VALUES (1, 'John', 'Smith', '2022-06-12 14:00:00');
INSERT INTO "user" VALUES (2, 'Capitan', 'America', '2022-06-12 14:30:00');
INSERT INTO wallet VALUES (1, 1, 'UAH', 100, '2022-06-12 14:35:00');
INSERT INTO wallet VALUES (2, 1, 'UAH', 112, '2022-06-12 14:40:00');
INSERT INTO wallet VALUES (3, 2, 'UAH', 99, '2022-06-12 14:40:30');
INSERT INTO wallet VALUES (4, 3, 'UAH', 1000, '2022-06-12 14:45:59');


SELECT u.name AS first_name, w.currency AS currency, SUM(w.amount) AS amount
FROM "user" AS u
    INNER JOIN wallet w ON u.id = w.user_id
GROUP BY first_name, w.currency
ORDER BY amount DESC;
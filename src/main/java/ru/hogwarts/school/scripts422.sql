CREATE TABLE drivers
(
    name         TEXT    NOT NULL
        PRIMARY KEY,
    age          INTEGER NOT NULL,
    have_license BOOLEAN NOT NULL,
    car_id       UUID
        REFERENCES cars (id)
);

CREATE TABLE cars
(
    id    UUID
        PRIMARY KEY,
    brand TEXT   NOT NULL,
    model TEXT   NOT NULL,
    price BIGINT NOT NULL
)
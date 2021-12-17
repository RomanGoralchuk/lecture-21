CREATE TABLE prophylactic_leave (
    id CHAR(50) NOT NULL,
    id_card CHAR(50) NULL DEFAULT NULL,
    description CHAR(50) NULL DEFAULT NULL,
    PRIMARY KEY (id) USING BTREE
);
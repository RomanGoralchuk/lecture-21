CREATE TABLE patient (
	id BIGINT DEFAULT (next value for patient_sequence),
	name CHAR(50) NULL DEFAULT NULL,
	surname CHAR(50) NULL DEFAULT NULL,
	gender CHAR(50) NULL DEFAULT NULL,
	birthday DATE NULL DEFAULT NULL,
	life_status CHAR(50) NOT NULL DEFAULT 'alive',
	PRIMARY KEY (id) USING BTREE
);
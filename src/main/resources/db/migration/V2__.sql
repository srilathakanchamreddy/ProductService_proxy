ALTER TABLE category
    ADD image_url VARCHAR(255) NULL;

ALTER TABLE student
    DROP FOREIGN KEY FK3w7xmt19i6a0cr7kp8c80ek40;

ALTER TABLE mentor
    DROP FOREIGN KEY FKd16r84yivniejc3seveq4kety;

ALTER TABLE instructor
    DROP FOREIGN KEY FKe1no74q1af4dd1fbrc0mat3rq;

DROP TABLE instructor;

DROP TABLE mentor;

DROP TABLE student;

DROP TABLE user;

DROP TABLE users;

ALTER TABLE category
    DROP COLUMN number;
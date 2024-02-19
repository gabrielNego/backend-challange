--DDL Create Table 
CREATE TABLE IF NOT EXISTS CUSTOMER (
    id INTEGER,
    limits BIGINT,
    balance BIGINT,
    PRIMARY KEY (id)
);

--DML Tables
INSERT INTO CUSTOMER (id, limits, balance) values (1, 100000, 0);
INSERT INTO CUSTOMER (id, limits, balance) values (2, 80000, 0);
INSERT INTO CUSTOMER (id, limits, balance) values (3, 1000000, 0);
INSERT INTO CUSTOMER (id, limits, balance) values (4, 10000000, 0);
INSERT INTO CUSTOMER (id, limits, balance) values (5, 500000, 0);
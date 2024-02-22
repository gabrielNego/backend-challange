--DDL Create Table 
CREATE TABLE IF NOT EXISTS CUSTOMER (
    id INTEGER,
    limit_ BIGINT,
    balance BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE public.transaction_history
(
    id bigint,
    customer_id bigint,
    type_ character varying(1),
    value_ bigint,
    description character varying(10),
    date_ timestamp with time zone,
    PRIMARY KEY (id)
);

--DML Tables
INSERT INTO CUSTOMER (id, limit_, balance) values (1, 100000, 0);
INSERT INTO CUSTOMER (id, limit_, balance) values (2, 80000, 0);
INSERT INTO CUSTOMER (id, limit_, balance) values (3, 1000000, 0);
INSERT INTO CUSTOMER (id, limit_, balance) values (4, 10000000, 0);
INSERT INTO CUSTOMER (id, limit_, balance) values (5, 500000, 0);
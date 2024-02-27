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
INSERT INTO CUSTOMER (id, limit_, balance) values (6, 500000, 80000);
INSERT INTO CUSTOMER (id, limit_, balance) values (7, 500000, -30000);


INSERT INTO public.transaction_history(id, customer_id, type_, value_, description, date_) VALUES
    (1, 6, 'c', 10000, 'descricao', '2024-01-05T02:34:38.543030Z'),
    (2, 7, 'd', 20000, 'descricao', '2024-01-06T01:34:38.543030Z'),
    (3, 6, 'd', 20000, 'descricao', '2024-01-06T02:34:38.543030Z'),
    (4, 7, 'c', 30000, 'descricao', '2024-01-07T01:34:38.543030Z'),
    (5, 6, 'c', 30000, 'descricao', '2024-01-07T02:34:38.543030Z'),
    (6, 7, 'd', 40000, 'descricao', '2024-01-08T01:34:38.543030Z'),
    (7, 6, 'd', 40000, 'descricao', '2024-01-08T02:34:38.543030Z'),
    (8, 6, 'c', 50000, 'descricao', '2024-01-09T02:34:38.543030Z'),
    (9, 6, 'd', 60000, 'descricao', '2024-01-10T02:34:38.543030Z'),
    (10, 6, 'c', 70000, 'descricao', '2024-01-11T02:34:38.543030Z'),
    (11, 6, 'd', 80000, 'descricao', '2024-01-12T02:34:38.543030Z'),
    (12, 6, 'c', 90000, 'descricao', '2024-01-13T02:34:38.543030Z'),
    (13, 6, 'd', 100000, 'descricao', '2024-01-14T02:34:38.543030Z'),
    (14, 6, 'c', 110000, 'descricao', '2024-01-15T02:34:38.543030Z'),
    (15, 6, 'd', 120000, 'descricao', '2024-01-16T02:34:38.543030Z'),
    (16, 6, 'c', 130000, 'descricao', '2024-01-17T02:34:38.543030Z');


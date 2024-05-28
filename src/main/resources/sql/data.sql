-- Seed data for accounts
INSERT INTO accounts (document_no) VALUES ('12345678');
INSERT INTO accounts (document_no) VALUES ('12345789');
INSERT INTO accounts (document_no) VALUES ('98765467');
INSERT INTO accounts (document_no) VALUES ('24567223');

-- Seed data for operation types
INSERT INTO operationtypes (description) VALUES ('Normal Purchase');
INSERT INTO operationtypes (description) VALUES ('Purchase with installments');
INSERT INTO operationtypes (description) VALUES ('Withdrawal');
INSERT INTO operationtypes (description) VALUES ('Credit Voucher');

-- Seed data for transactions
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (1, 1, 100.00, '2023-05-01T10:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (2, 2, 200.00, '2023-05-02T11:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (3, 3, -300.00, '2023-05-03T12:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (4, 4, 400.00, '2023-05-04T13:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (1, 2, 150.00, '2023-05-05T14:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (2, 3, -250.00, '2023-05-06T15:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (3, 4, 350.00, '2023-05-07T16:00:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (4, 1, 450.00, '2023-05-08T17:00:00');

INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (3, 4, -350.00, '2023-05-07T17:30:00');
INSERT INTO transactions (account_id, operationtype_id, amount, eventdate) VALUES (4, 1, 450.00, '2023-05-08T18:00:00');

DROP TABLE IF EXISTS Account cascade;
DROP TABLE IF EXISTS Customer cascade;
DROP TABLE IF EXISTS Card cascade;

CREATE TABLE Customer(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) not null,
    second_name VARCHAR(255) not null,
    patronymic VARCHAR(255) not null
);

CREATE TABLE Account(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_customer BIGINT not null,
    account_number BIGINT not null,
    balance NUMERIC(20,2) not null DEFAULT 0.00,
    FOREIGN KEY (id_customer) REFERENCES Customer(id)
);

CREATE TABLE Card(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_account BIGINT not null,
    card_number VARCHAR(20) not null,
    pin VARCHAR(200) not null,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    FOREIGN KEY (id_account) REFERENCES Account(id)
);

INSERT INTO Customer(first_name,second_name,patronymic) VALUES
('Mark','Ivanuk','Lvovich'),
('Igor', 'Samoylov', 'Alekseevich'),
('Anton', 'Kiselev', 'Yurievich'),
('Diana', 'Motuzova', 'Vladimirovna');

INSERT INTO Account(id_customer, account_number, balance) VALUES
('1', '100001', '3000.00'),
('2', '100002', '123000.00'),
('3', '100003', '1230.00'),
('4', '100005', '66.00');

INSERT INTO Card(id_account, card_number, pin) VALUES
('1', '5222232132139321', '$2a$12$oHDx7c88c6OX/TOds.CsNeBZy33v.jAB710y6b5m4Os0jPyhly2za'),
('2', '5223232108348731', '$2a$12$U8QUOq72EeDFiKNY8mQjMuond8FDL5bawDgMhYSiJFsmomqxx4m8y'),
('3', '5223095139345432', '$2a$12$hTbFq.f6cJFk2S7Uhxl3QuFR0D3xtilwHaNRlV1Bib6DSZvOKDEai'),
('4', '1488666777666123', '$2a$12$U8QUOq72EeDFiKNY8mQjMuond8FDL5bawDgMhYSiJFsmomqxx4m8y');

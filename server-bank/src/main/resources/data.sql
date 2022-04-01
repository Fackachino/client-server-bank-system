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
    card_number BIGINT not null,
    pin INT not null,
    FOREIGN KEY (id_account) REFERENCES Account(id)
);

INSERT INTO Customer(first_name,second_name,patronymic) VALUES
('Mark','Ivanuk','Lvovich'),
('Samoylov', 'Igor', 'Alekseevich'),
('Kiselev', 'Anton', 'Yurievich');


INSERT INTO Account(id_customer, account_number, balance) VALUES
('1', '100001', '2000.00'),
('2', '100002', '2000.00'),
('3', '100003', '2000.00');

INSERT INTO Card(id_account, card_number, pin) VALUES
('1', '5222232132139321', '1111'),
('2', '5223232108348731', '2222'),
('3', '5223095139345432', '2222');

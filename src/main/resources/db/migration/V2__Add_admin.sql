-- V4__Add_admin.sql

-- Добавление администратора с ролью "ROLE_ADMIN" в таблицу users
INSERT INTO users (username, password, role)
VALUES ('admin', '{encoded_password}', 'ROLE_ADMIN');

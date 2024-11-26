-- V4__Add_admin.sql

-- Добавление администратора с ролью "ROLE_ADMIN" в таблицу users, если его еще нет
DO $$
BEGIN
    -- Проверяем, существует ли администратор
    IF NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin') THEN
        -- Вставляем администратора, если его нет
        INSERT INTO users (username, password, role)
        VALUES ('admin', '$2a$10$yCEVRQKvyinfx3xfknKOP.lwBqaCfmnwnKeKLu7xncu59XKAsmFnC', 'ROLE_ADMIN');
    END IF;
END $$;

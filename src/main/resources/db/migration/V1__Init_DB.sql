-- V1__Initial_schema.sql

-- Создание таблицы products
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    coast FLOAT4,
    name VARCHAR(255),
    size VARCHAR(255),
    type VARCHAR(255)
);

-- Создание таблицы carts
CREATE TABLE carts (
    id INTEGER NOT NULL PRIMARY KEY
);

-- Создание таблицы users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    cart_id INTEGER,
    FOREIGN KEY (cart_id) REFERENCES carts(id)
);

-- Создание таблицы cart_products
CREATE TABLE cart_products (
    cart_id INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    PRIMARY KEY (cart_id, product_id),
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
-- V2__Add_unique_constraints.sql

-- Добавляем уникальные ограничения на колонку username в таблице users
ALTER TABLE users ADD CONSTRAINT UK_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);

-- Добавляем уникальные ограничения на колонку cart_id в таблице users
ALTER TABLE users ADD CONSTRAINT UK_pnp1baae4enifkkuq2cd01r9l UNIQUE (cart_id);
-- V3__Add_foreign_keys.sql

-- Добавление внешнего ключа для связи с таблицей products
ALTER TABLE cart_products ADD CONSTRAINT FKdayy17at10up1qqwlri9cocb3
    FOREIGN KEY (product_id) REFERENCES products(id);

-- Добавление внешнего ключа для связи с таблицей carts
ALTER TABLE cart_products ADD CONSTRAINT FKbilp3o9irlsvmbot68kfpthom
    FOREIGN KEY (cart_id) REFERENCES carts(id);

-- Добавление внешнего ключа для связи с таблицей carts в таблице users
ALTER TABLE users ADD CONSTRAINT FKdv26y3bb4vdmsr89c9ppnx85w
    FOREIGN KEY (cart_id) REFERENCES carts(id);

CREATE TABLE categories
(
    id         LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)                    NOT NULL UNIQUE,

    created_by LONG                            NOT NULL,
    updated_by LONG,
    created_at TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE suppliers
(
    id               LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255)                    NOT NULL,
    phone            VARCHAR(20),
    social_link      VARCHAR(255),
    location         VARCHAR(255),
    aisle_number     VARCHAR(50),
    container_number VARCHAR(50),

    created_by       LONG                            NOT NULL,
    updated_by       LONG,
    created_at       TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE seasons
(
    id         LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255)                    NOT NULL,

    created_by LONG                            NOT NULL,
    updated_by LONG,
    created_at TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE products
(
    id            LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)                    NOT NULL,
    barcode       VARCHAR(50)                     NOT NULL,
    code          VARCHAR(50)                     NOT NULL,
    category_id   LONG                          NOT NULL,
    cost_price    NUMERIC(38, 2)                  NOT NULL,
    selling_price NUMERIC(38, 2)                  NOT NULL,
    picture_path  VARCHAR(255),
    description   TEXT,
    supplier_id   LONG,
    season_id     LONG,
    FOREIGN KEY (category_id) REFERENCES categories (id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id),
    FOREIGN KEY (season_id) REFERENCES seasons (id),

    created_by    LONG                            NOT NULL,
    updated_by    LONG,
    created_at    TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Вставка данных в таблицу categories
INSERT INTO categories (id, name, created_by, created_at)
VALUES (1, 'Одежда', 1, CURRENT_TIMESTAMP),
       (2, 'Аксессуары', 2, CURRENT_TIMESTAMP),
       (3, 'Обувь', 1, CURRENT_TIMESTAMP);

-- Вставка данных в таблицу suppliers
INSERT INTO suppliers (id, name, phone, social_link, location, aisle_number, container_number, created_by, created_at)
VALUES (1, 'Поставщик А', '123-456-7890', 'https://supplier-a.com', 'Склад 1', 'Ряд А1', 'Контейнер C1', 1,
        CURRENT_TIMESTAMP),
       (2, 'Поставщик Б', '987-654-3210', 'https://supplier-b.com', 'Склад 2', 'Ряд B2', 'Контейнер C2', 2,
        CURRENT_TIMESTAMP),
       (3, 'Поставщик В', NULL, NULL, 'Склад 3', NULL, NULL, 3, CURRENT_TIMESTAMP);

-- Вставка данных в таблицу seasons
INSERT INTO seasons (id, name, created_by, created_at)
VALUES (1, 'Лето', 1, CURRENT_TIMESTAMP),
       (2, 'Зима', 2, CURRENT_TIMESTAMP),
       (3, 'Весна', 3, CURRENT_TIMESTAMP);


-- Вставка данных в таблицу products
INSERT INTO products (id, name, barcode, code, category_id, cost_price, selling_price, picture_path, description,
                      supplier_id, season_id, created_by, created_at)
VALUES (1, 'Футболка', '1234567890', 'TS123', 1, 10.00, 15.00, '/images/tshirt.jpg', 'Удобная футболка', 1, 1, 1,
        CURRENT_TIMESTAMP),
       (2, 'Куртка', '9876543210', 'JK456', 1, 50.00, 70.00, '/images/jacket.jpg', 'Тёплая куртка', 2, 2, 2,
        CURRENT_TIMESTAMP),
       (3, 'Кроссовки', '5432167890', 'SN789', 3, 40.00, 60.00, '/images/sneakers.jpg', 'Модные кроссовки', 3, 3, 3,
        CURRENT_TIMESTAMP);



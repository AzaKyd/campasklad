-- Создание таблицы warehouses (склады)
CREATE TABLE facilities
(
    id       LONG PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

-- Создание таблицы colors (цвета)
CREATE TABLE colors
(
    id    LONG PRIMARY KEY,
    value VARCHAR(50) NOT NULL
);

-- Создание таблицы sizes (размеры)
CREATE TABLE sizes
(
    id    LONG PRIMARY KEY,
    value VARCHAR(50) NOT NULL
);

-- Создание таблицы products (товары)
CREATE TABLE products
(
    id   LONG PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Создание таблицы product_variations (вариации товаров)
CREATE TABLE product_variations
(
    id         LONG PRIMARY KEY,
    product_id LONG NOT NULL,
    size_id    LONG NOT NULL,
    color_id   LONG NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    FOREIGN KEY (size_id) REFERENCES sizes (id) ON DELETE SET NULL,
    FOREIGN KEY (color_id) REFERENCES colors (id) ON DELETE SET NULL
);

-- Создание таблицы inventory (инвентарь на складах)
CREATE TABLE inventory
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    facility_id          LONG NOT NULL,
    quantity             INT NOT NULL,
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id) ON DELETE CASCADE,
    FOREIGN KEY (facility_id) REFERENCES facilities (id) ON DELETE CASCADE
);

-- Создание таблицы inventory_movements (движения инвентаря)
CREATE TABLE inventory_movements
(
    id                       LONG PRIMARY KEY,
    facility_id              LONG         NOT NULL,
    product_variation_id     LONG         NOT NULL,
    movement_type            VARCHAR(50) NOT NULL, -- Например: "in", "out", "transfer"
    quantity                 INT         NOT NULL,
    quantity_before_movement INT         NOT NULL,
    quantity_after_movement  INT         NOT NULL,
    FOREIGN KEY (facility_id) REFERENCES facilities (id) ON DELETE CASCADE,
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id) ON DELETE CASCADE
);

-- Создание таблицы postings (поставки)
CREATE TABLE postings
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    quantity             INT NOT NULL,
    facility_id          LONG NOT NULL,
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id) ON DELETE CASCADE,
    FOREIGN KEY (facility_id) REFERENCES facilities (id) ON DELETE CASCADE
);

-- Создание таблицы writeoffs (списания)
CREATE TABLE writeoffs
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    quantity             INT NOT NULL,
    facility_id          LONG NOT NULL,
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id) ON DELETE CASCADE,
    FOREIGN KEY (facility_id) REFERENCES facilities (id) ON DELETE CASCADE
);

-- Создание таблицы transfers (трансферы между складами)
CREATE TABLE transfers
(
    id                      LONG PRIMARY KEY,
    source_facility_id      LONG NOT NULL,
    destination_facility_id LONG NOT NULL,
    product_variation_id    LONG  NOT NULL,
    quantity                INT  NOT NULL,
    FOREIGN KEY (source_facility_id) REFERENCES facilities (id) ON DELETE CASCADE,
    FOREIGN KEY (destination_facility_id) REFERENCES facilities (id) ON DELETE CASCADE,
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id) ON DELETE CASCADE
);
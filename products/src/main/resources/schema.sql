CREATE TABLE IF NOT EXISTS categories (
    id          INTEGER         PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)    NOT NULL
);

CREATE TABLE suppliers (
   id INT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL,
   phone VARCHAR(20),
   social_link VARCHAR(255),
   location VARCHAR(255),
   aisle_number VARCHAR(50),
   container_number VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS products (
    id            BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)    NOT NULL,
    barcode       VARCHAR(50)     NOT NULL,
    code          VARCHAR(50)     NOT NULL,
    category_id   INTEGER         NOT NULL,
    cost_price    NUMERIC(38, 2)  NOT NULL,
    selling_price NUMERIC(38, 2)  NOT NULL,
    picture_path  VARCHAR(255),
    description   TEXT,
    supplier_id   BIGINT,
    season_id     INTEGER,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    FOREIGN KEY (season_id) REFERENCES seasons(id)
);


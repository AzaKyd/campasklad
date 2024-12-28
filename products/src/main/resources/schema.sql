CREATE TABLE categories (
    id              BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255)    NOT NULL UNIQUE,

    created_by      BIGINT          NOT NULL,
    updated_by      BIGINT,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE suppliers (
    id               BIGINT         PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255)   NOT NULL,
    phone            VARCHAR(20),
    social_link      VARCHAR(255),
    location         VARCHAR(255),
    aisle_number     VARCHAR(50),
    container_number VARCHAR(50)
);

CREATE TABLE seasons (
    id              BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255)    NOT NULL
);

CREATE TABLE sizes (
    id              BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name           VARCHAR(50)     NOT NULL
);

CREATE TABLE colors (
    id              BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name            VARCHAR(50)     NOT NULL UNIQUE,

    created_by      BIGINT          NOT NULL,
    updated_by      BIGINT,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE products (
                          id              BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
                          name            VARCHAR(255)    NOT NULL,
                          barcode         VARCHAR(50)     NOT NULL,
                          code            VARCHAR(50)     NOT NULL,
                          category_id     BIGINT         NOT NULL,
                          cost_price      NUMERIC(38, 2)  NOT NULL,
                          selling_price   NUMERIC(38, 2)  NOT NULL,
                          picture_path    VARCHAR(255),
                          description     TEXT,
                          supplier_id     BIGINT,
                          season_id       BIGINT,
                          FOREIGN KEY (category_id) REFERENCES categories(id),
                          FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
                          FOREIGN KEY (season_id) REFERENCES seasons(id)
);

CREATE TABLE product_variations (
    id              BIGINT          PRIMARY KEY AUTO_INCREMENT NOT NULL,
    product_id      BIGINT          NOT NULL,
    size_id         BIGINT          NOT NULL,
    color_id        BIGINT          NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (size_id) REFERENCES sizes(id),
    FOREIGN KEY (color_id) REFERENCES colors(id)
);


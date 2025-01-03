CREATE TABLE sizes
(
    id         LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name       VARCHAR(50)                     NOT NULL,
    created_by LONG                            NOT NULL,
    updated_by LONG,
    created_at TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE colors
(
    id         LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name       VARCHAR(50)                     NOT NULL UNIQUE,
    created_by LONG                            NOT NULL,
    updated_by LONG,
    created_at TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE product_variations
(
    id         LONG PRIMARY KEY AUTO_INCREMENT NOT NULL,
    product_id LONG                            NOT NULL,
    size_id    LONG                            NOT NULL,
    color_id   LONG                            NOT NULL,
    created_by LONG                            NOT NULL,
    updated_by LONG,
    created_at TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (size_id) REFERENCES sizes (id),
    FOREIGN KEY (color_id) REFERENCES colors (id)
);

CREATE TABLE facilities
(
    id         LONG PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    location   VARCHAR(255),
    created_by LONG         NOT NULL,
    updated_by LONG,
    created_at TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE facility_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG      NOT NULL,
    facility_id          LONG      NOT NULL,
    quantity             LONG      NOT NULL,
    created_by           LONG      NOT NULL,
    updated_by           LONG,
    created_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (facility_id) REFERENCES facilities (id),
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id)
);

CREATE TABLE postings
(
    id          LONG PRIMARY KEY,
    facility_id LONG      NOT NULL,
    status      VARCHAR(50),
    created_by  LONG      NOT NULL,
    updated_by  LONG,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);

CREATE TABLE posting_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG      NOT NULL,
    quantity             LONG      NOT NULL,
    posting_id           LONG      NOT NULL,
    created_by           LONG      NOT NULL,
    updated_by           LONG,
    created_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (posting_id) REFERENCES postings (id),
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id)
);

CREATE TABLE writeoffs
(
    id          LONG PRIMARY KEY,
    facility_id LONG      NOT NULL,
    status      VARCHAR(50),
    created_by  LONG      NOT NULL,
    updated_by  LONG,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);

CREATE TABLE writeoff_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG      NOT NULL,
    quantity             LONG      NOT NULL,
    writeoff_id          LONG      NOT NULL,
    created_by           LONG      NOT NULL,
    updated_by           LONG,
    created_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (writeoff_id) REFERENCES writeoffs (id),
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id)
);

CREATE TABLE transfers
(
    id                      LONG PRIMARY KEY,
    source_facility_id      LONG      NOT NULL,
    destination_facility_id LONG      NOT NULL,
    status                  VARCHAR(50),
    created_by              LONG      NOT NULL,
    updated_by              LONG,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (source_facility_id) REFERENCES facilities (id),
    FOREIGN KEY (destination_facility_id) REFERENCES facilities (id)
);

CREATE TABLE transfer_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG      NOT NULL,
    quantity             LONG      NOT NULL,
    transfer_id          LONG      NOT NULL,
    created_by           LONG      NOT NULL,
    updated_by           LONG,
    created_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (transfer_id) REFERENCES transfers (id),
    FOREIGN KEY (product_variation_id) REFERENCES product_variations (id)
);

INSERT INTO facilities (id, name, location, created_by, updated_by, created_at, updated_at)
VALUES (1, 'Facility A', 'Location A', 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'Facility B', 'Location B', 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 'Facility C', 'Location C', 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 'Facility D', 'Location D', 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 'Facility E', 'Location E', 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO sizes (id, name, created_by, updated_by, created_at, updated_at)
VALUES (1, 'Small', 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'Medium', 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 'Large', 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 'X-Large', 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 'XX-Large', 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO colors (id, name, created_by, updated_by, created_at, updated_at)
VALUES (1, 'Red', 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 'Blue', 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 'Green', 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 'Yellow', 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 'Black', 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO product_variations (id, product_id, size_id, color_id, created_by, updated_by, created_at, updated_at)
VALUES (1, 1, 1, 1, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 2, 2, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 3, 3, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 1, 4, 4, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 2, 5, 5, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO facility_products (id, product_variation_id, facility_id, quantity, created_by, updated_by,
                               created_at, updated_at)
VALUES (1, 1, 1, 100, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 2, 200, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 3, 300, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 4, 400, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 5, 500, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO postings (id, facility_id, status, created_by, updated_by, created_at, updated_at)
VALUES (1, 1, 'NEW', 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 'NEW', 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 'NEW', 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 'NEW', 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 'NEW', 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posting_products (id, product_variation_id, quantity, posting_id, created_by, updated_by, created_at,
                              updated_at)
VALUES (1, 1, 50, 1, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 150, 2, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 70, 3, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 90, 4, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 30, 5, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO writeoffs (id, facility_id, status, created_by, updated_by, created_at, updated_at)
VALUES (1, 1, 'NEW', 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 'NEW', 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 'NEW', 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 'NEW', 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 'NEW', 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO writeoff_products (id, product_variation_id, quantity, writeoff_id, created_by, updated_by, created_at,
                               updated_at)
VALUES (1, 1, 20, 1, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 30, 2, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 40, 3, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 25, 4, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 35, 5, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO transfers (id, source_facility_id, destination_facility_id, status, created_by, updated_by, created_at,
                       updated_at)
VALUES (1, 1, 2, 'NEW', 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 1, 'NEW', 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 4, 'NEW', 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 5, 'NEW', 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 3, 'NEW', 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO transfer_products (id, product_variation_id, quantity, transfer_id, created_by, updated_by, created_at,
                               updated_at)
VALUES (1, 1, 10, 1, 1, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 2, 15, 2, 2, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, 3, 20, 3, 3, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (4, 4, 25, 4, 4, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (5, 5, 30, 5, 5, NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
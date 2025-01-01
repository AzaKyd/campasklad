CREATE TABLE facilities
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE postings
(
    id          BIGINT PRIMARY KEY,
    facility_id BIGINT NOT NULL,
    status      VARCHAR(50),
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);

CREATE TABLE posting_products
(
    id                   BIGINT PRIMARY KEY,
    product_variation_id BIGINT NOT NULL,
    quantity             BIGINT NOT NULL,
    posting_id           BIGINT NOT NULL,
    FOREIGN KEY (posting_id) REFERENCES postings (id)
);

CREATE TABLE writeoffs
(
    id          BIGINT PRIMARY KEY,
    facility_id BIGINT NOT NULL,
    status      VARCHAR(50),
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);

CREATE TABLE writeoff_products
(
    id                   BIGINT PRIMARY KEY,
    product_variation_id BIGINT NOT NULL,
    quantity             BIGINT NOT NULL,
    writeoff_id          BIGINT NOT NULL,
    FOREIGN KEY (writeoff_id) REFERENCES writeoffs (id)
);

CREATE TABLE transfers
(
    id                      BIGINT PRIMARY KEY,
    source_facility_id      BIGINT NOT NULL,
    destination_facility_id BIGINT NOT NULL,
    status                  VARCHAR(50),
    FOREIGN KEY (source_facility_id) REFERENCES facilities (id),
    FOREIGN KEY (destination_facility_id) REFERENCES facilities (id)
);

CREATE TABLE transfer_products
(
    id                   BIGINT PRIMARY KEY,
    product_variation_id BIGINT NOT NULL,
    quantity             BIGINT NOT NULL,
    transfer_id          BIGINT NOT NULL,
    FOREIGN KEY (transfer_id) REFERENCES transfers (id)
);

CREATE TABLE facility_products
(
    id                   BIGINT PRIMARY KEY,
    product_variation_id BIGINT NOT NULL,
    facility_id          BIGINT NOT NULL,
    quantity             BIGINT NOT NULL,
    product_id           BIGINT NOT NULL,
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);
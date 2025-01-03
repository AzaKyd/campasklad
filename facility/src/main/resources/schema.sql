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
    product_id           LONG      NOT NULL,
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

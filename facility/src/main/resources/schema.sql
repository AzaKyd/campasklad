CREATE TABLE facilities
(
    id       LONG PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    location VARCHAR(255)
);

CREATE TABLE postings
(
    id          LONG PRIMARY KEY,
    facility_id LONG NOT NULL,
    status      VARCHAR(50),
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);

CREATE TABLE posting_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    quantity             LONG NOT NULL,
    posting_id           LONG NOT NULL,
    FOREIGN KEY (posting_id) REFERENCES postings (id)
);

CREATE TABLE writeoffs
(
    id          LONG PRIMARY KEY,
    facility_id LONG NOT NULL,
    status      VARCHAR(50),
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);

CREATE TABLE writeoff_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    quantity             LONG NOT NULL,
    writeoff_id          LONG NOT NULL,
    FOREIGN KEY (writeoff_id) REFERENCES writeoffs (id)
);

CREATE TABLE transfers
(
    id                      LONG PRIMARY KEY,
    source_facility_id      LONG NOT NULL,
    destination_facility_id LONG NOT NULL,
    status                  VARCHAR(50),
    FOREIGN KEY (source_facility_id) REFERENCES facilities (id),
    FOREIGN KEY (destination_facility_id) REFERENCES facilities (id)
);

CREATE TABLE transfer_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    quantity             LONG NOT NULL,
    transfer_id          LONG NOT NULL,
    FOREIGN KEY (transfer_id) REFERENCES transfers (id)
);

CREATE TABLE facility_products
(
    id                   LONG PRIMARY KEY,
    product_variation_id LONG NOT NULL,
    facility_id          LONG NOT NULL,
    quantity             LONG NOT NULL,
    product_id           LONG NOT NULL,
    FOREIGN KEY (facility_id) REFERENCES facilities (id)
);


-- Inserting default data into facilities table
INSERT INTO facilities (id, name, location)
VALUES (1, 'Facility 1', 'Location 1'),
       (2, 'Facility 2', 'Location 2'),
       (3, 'Facility 3', 'Location 3'),
       (4, 'Facility 4', 'Location 4'),
       (5, 'Facility 5', 'Location 5');

-- Inserting default data into postings table
INSERT INTO postings (id, facility_id, status)
VALUES (1, 1, 'Status 1'),
       (2, 2, 'Status 2'),
       (3, 3, 'Status 3'),
       (4, 4, 'Status 4'),
       (5, 5, 'Status 5');

-- Inserting default data into posting_products table
INSERT INTO posting_products (id, product_variation_id, quantity, posting_id)
VALUES (1, 1, 10, 1),
       (2, 2, 20, 2),
       (3, 3, 30, 3),
       (4, 4, 40, 4),
       (5, 5, 50, 5);

-- Inserting default data into writeoffs table
INSERT INTO writeoffs (id, facility_id, status)
VALUES (1, 1, 'Writeoff Status 1'),
       (2, 2, 'Writeoff Status 2'),
       (3, 3, 'Writeoff Status 3'),
       (4, 4, 'Writeoff Status 4'),
       (5, 5, 'Writeoff Status 5');

-- Inserting default data into writeoff_products table
INSERT INTO writeoff_products (id, product_variation_id, quantity, writeoff_id)
VALUES (1, 1, 5, 1),
       (2, 2, 10, 2),
       (3, 3, 15, 3),
       (4, 4, 20, 4),
       (5, 5, 25, 5);

-- Inserting default data into transfers table
INSERT INTO transfers (id, source_facility_id, destination_facility_id, status)
VALUES (1, 1, 2, 'Transfer Status 1'),
       (2, 2, 3, 'Transfer Status 2'),
       (3, 3, 4, 'Transfer Status 3'),
       (4, 4, 5, 'Transfer Status 4'),
       (5, 5, 1, 'Transfer Status 5');

-- Inserting default data into transfer_products table
INSERT INTO transfer_products (id, product_variation_id, quantity, transfer_id)
VALUES (1, 1, 2, 1),
       (2, 2, 4, 2),
       (3, 3, 6, 3),
       (4, 4, 8, 4),
       (5, 5, 10, 5);

-- Inserting default data into facility_products table
INSERT INTO facility_products (id, product_variation_id, facility_id, quantity, product_id)
VALUES (1, 1, 2, 3, 1),
       (2, 2, 3, 6, 2),
       (3, 3, 4, 9, 3),
       (4, 4, 5, 12, 1),
       (5, 5, 1, 15, 2),
       (6, 6, 2, 18, 3),
       (7, 7, 3, 21, 4),
       (8, 8, 4, 24, 1),
       (9, 9, 5, 27, 2),
       (10, 10, 1, 30, 3);
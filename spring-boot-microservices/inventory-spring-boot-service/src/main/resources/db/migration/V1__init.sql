CREATE TABLE inventory (
    id BIGINT PRIMARY KEY,
    inventory_id varchar(36),
    inventory_name varchar(255)
);

CREATE TABLE inventory_item (
    id        BIGINT PRIMARY KEY,
    item_id   varchar(36),
    item_name varchar(255),
    item_desc varchar(255),
    price     decimal,
    quantity  integer
);
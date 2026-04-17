CREATE TABLE customers (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    shop_id     BIGINT          NOT NULL,
    name        VARCHAR(50)     NOT NULL,
    phone       VARCHAR(20),
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (shop_id) REFERENCES shops(id)
);
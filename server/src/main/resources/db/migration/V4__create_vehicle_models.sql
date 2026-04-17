CREATE TABLE vehicle_models (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    shop_id     BIGINT          NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (shop_id) REFERENCES shops(id)
);
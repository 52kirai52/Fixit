CREATE TABLE repairs (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    shop_id     BIGINT          NOT NULL,
    vehicle_id  BIGINT          NOT NULL,
    status      VARCHAR(20)     NOT NULL DEFAULT 'RECEIVED',
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_at   DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (shop_id) REFERENCES shops(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);
CREATE TABLE vehicles (
    id              BIGINT          NOT NULL AUTO_INCREMENT,
    customer_id     BIGINT          NOT NULL,
    model_id        BIGINT,
    plate_number    VARCHAR(20),
    last_mileage    INT,
    created_at      DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (model_id) REFERENCES vehicle_models(id)
);
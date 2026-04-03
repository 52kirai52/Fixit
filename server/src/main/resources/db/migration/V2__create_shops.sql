CREATE TABLE shops (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    owner_id    BIGINT          NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    address     VARCHAR(255),
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);
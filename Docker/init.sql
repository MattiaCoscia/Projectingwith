CREATE TABLE IF NOT EXISTS game_map
(
    id   INT PRIMARY KEY    NOT NULL,
    name VARCHAR(24) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS item_blueprint
(
    Id          INT PRIMARY KEY    NOT NULL,
    name        VARCHAR(24) UNIQUE NOT NULL,
    description VARCHAR(64)        NOT NULL,
    volume      INT                NOT NULL
);


CREATE TABLE IF NOT EXISTS room_type
(
    id   INT PRIMARY KEY NOT NULL,
    name VARCHAR(24)     NOT NULL
);


CREATE TABLE IF NOT EXISTS inventoryDTO
(
    id INT PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS item_stored
(
    id           INT PRIMARY KEY NOT NULL,
    inventory_id INT             NOT NULL references inventoryDTO (id),
    blueprint_id INT             NOT NULL references item_blueprint (id),
    quantity     INT             NOT NULL
);

CREATE TABLE IF NOT EXISTS roomDTO
(
    id           INT PRIMARY KEY    NOT NULL,
    name         VARCHAR(24) UNIQUE NOT NULL,
    position_x   INT                NOT NULL,
    position_y   INT                NOT NULL,
    type_id      VARCHAR(24)        NOT NULL references room_type (id),
    map_id       INT                NOT NULL references game_map (id),
    inventory_id INT references inventoryDTO (id)
);

CREATE TABLE IF NOT EXISTS direction_enum
(
    id   INT PRIMARY KEY NOT NULL,
    name VARCHAR(24) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS bag
(
    Id              INT PRIMARY KEY NOT NULL,
    volume          INT             NOT NULL,
    occupied_volume INT             NOT NULL,
    inventory_id INT NOT NULL references inventoryDTO (id)
);

CREATE TABLE IF NOT EXISTS player
(
    Id         INT PRIMARY KEY NOT NULL,
    name       VARCHAR(24)     NOT NULL,
    position_x INT             NOT NULL,
    position_y INT             NOT NULL,
    lifepoints INT             NOT NULL,
    bag_id     INT             NOT NULL references bag (id)
);

CREATE TABLE IF NOT EXISTS room_connection
(
    from_room_id INT NOT NULL,
    direction_id INT NOT NULL references direction_enum (id),
    to_room_id   INT NOT NULL,
    PRIMARY KEY (from_room_id, to_room_id),
    FOREIGN KEY (from_room_id) REFERENCES room (id),
    FOREIGN KEY (to_room_id) REFERENCES room (id)
);

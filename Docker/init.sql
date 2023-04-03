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


CREATE TABLE IF NOT EXISTS type_room
(
    id VARCHAR(24) PRIMARY KEY NOT NULL
);


CREATE TABLE IF NOT EXISTS inventory
(
    id INT PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS item_stored
(
    id           INT PRIMARY KEY NOT NULL,
    inventory_id INT             NOT NULL references inventory (id),
    blueprint_id INT             NOT NULL references item_blueprint (id),
    quantity     INT             NOT NULL
);

CREATE TABLE IF NOT EXISTS room
(
    id           INT PRIMARY KEY    NOT NULL,
    name         VARCHAR(24) UNIQUE NOT NULL,
    position_x   INT                NOT NULL,
    position_y   INT                NOT NULL,
    type_id      VARCHAR(24)        NOT NULL references type_room (id),
    map_id       INT                NOT NULL references game_map (id),
    inventory_id INT references inventory (id)
);

CREATE TABLE IF NOT EXISTS direction
(
    id VARCHAR(24) PRIMARY KEY    NOT NULL
);

CREATE TABLE IF NOT EXISTS bag
(
    Id              INT PRIMARY KEY NOT NULL,
    volume          INT             NOT NULL,
    occupied_volume INT             NOT NULL,
    inventory_id INT NOT NULL references inventory (id)
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
    from_room_id INT         NOT NULL,
    direction_id varchar(24) NOT NULL references direction (id),
    to_room_id   INT         NOT NULL,
    PRIMARY KEY (from_room_id, to_room_id)
);

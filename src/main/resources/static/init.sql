CREATE TABLE IF NOT EXISTS game_map
(
    id   SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(24) UNIQUE NOT NULL,
    height_map INT NOT NULL,
    width_map INT NOT NULL
);

CREATE TABLE IF NOT EXISTS item_blueprint
(
    Id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(24) UNIQUE NOT NULL,
    description VARCHAR(64)        NOT NULL,
    volume      INT                NOT NULL
);


CREATE TABLE IF NOT EXISTS room_type
(
    id   SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(24)        NOT NULL
);


CREATE TABLE IF NOT EXISTS inventory
(
    id SERIAL PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS item_stored
(
    id            SERIAL PRIMARY KEY NOT NULL,
    personal_name VARCHAR(24)        NOT NULL,
    inventory_id  INT                references inventory (id),
    blueprint_id  INT                NOT NULL references item_blueprint (id),
    quantity      INT                NOT NULL,
    hash_key      INT                NOT NULL
);

CREATE TABLE IF NOT EXISTS room
(
    id           SERIAL PRIMARY KEY NOT NULL,
    name         VARCHAR(24) UNIQUE NOT NULL,
    position_x   INT                NOT NULL,
    position_y   INT                NOT NULL,
    type_id      INT                NOT NULL references room_type (id),
    map_id       INT                NOT NULL references game_map (id),
    inventory_id INT references inventory (id),
    chain_position INT NOT NULL
);

-- CREATE TABLE IF NOT EXISTS direction_enum
-- (
--     id   INT PRIMARY KEY NOT NULL,
--     value VARCHAR(24) UNIQUE NOT NULL
-- );

CREATE TABLE IF NOT EXISTS bag
(
    Id              SERIAL PRIMARY KEY NOT NULL,
    volume          INT                NOT NULL,
    occupied_volume INT                NOT NULL,
    inventory_id    INT                NOT NULL references inventory (id)
);

CREATE TABLE IF NOT EXISTS player
(
    Id         SERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(24)        NOT NULL,
    position_x INT                NOT NULL,
    position_y INT                NOT NULL,
    lifepoints INT                NOT NULL,
    bag_id     INT                NOT NULL references bag (id)
);

CREATE TABLE IF NOT EXISTS door
(
    id         SERIAL PRIMARY KEY NOT NULL,
    is_open    BOOLEAN            NOT NULL,
    itemkey_id INT                NOT NULL references item_stored (id)
);

CREATE TABLE IF NOT EXISTS door_connection
(
    from_room_id INT NOT NULL,
    direction_id VARCHAR(24) NOT NULL, --references direction_enum (name),
    to_door_id   INT NOT NULL,
    PRIMARY KEY (from_room_id, to_door_id),
    FOREIGN KEY (from_room_id) REFERENCES room (id),
    FOREIGN KEY (to_door_id) REFERENCES door (id)
);

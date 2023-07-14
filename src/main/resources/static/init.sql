CREATE TABLE IF NOT EXISTS game_map
(
    id   SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(24) NOT NULL,
    height_map INT NOT NULL,
    width_map INT NOT NULL
);

CREATE TABLE IF NOT EXISTS entity
(
    Id         SERIAL PRIMARY KEY NOT NULL,
    name       VARCHAR(255)       NOT NULL,
    entity_type VARCHAR(255) NOT NULL,
    position_x INT                NOT NULL,
    position_y INT                NOT NULL,
    lifepoints FLOAT                NOT NULL,
    animal_name VARCHAR(255),
    prefered_food VARCHAR(255),
    age INT,
    date_entry VARCHAR(255),
    weight FLOAT,
    height FLOAT,
    tail_lenght FLOAT,
    wings_spawn FLOAT,
    bag_id INT
);

CREATE TABLE IF NOT EXISTS item_blueprint
(
    Id          SERIAL PRIMARY KEY NOT NULL,
    name        VARCHAR(24) NOT NULL,
    description VARCHAR(64)        NOT NULL,
    volume      INT                NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory
(
    id SERIAL PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS item_stored
(
    id            SERIAL PRIMARY KEY NOT NULL,
    personal_name VARCHAR(255)       NOT NULL,
    blueprint_id  INT                NOT NULL references item_blueprint (id),
    quantity      INT                NOT NULL,
    hash_key      INT                NOT NULL
);

CREATE TABLE IF NOT EXISTS item_stored_inventory_correlation(
    inventory_id INT NOT NULL,
    item_stored_id INT NOT NULL,
    personal_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (inventory_id, item_stored_id),
    FOREIGN KEY (item_stored_id) references item_stored (id),
    FOREIGN KEY (inventory_id) references inventory (id)
);

CREATE TABLE IF NOT EXISTS room
(
    id           SERIAL PRIMARY KEY NOT NULL,
    name         VARCHAR(24)  NOT NULL,
    position_x   INT                NOT NULL,
    position_y   INT                NOT NULL,
    type_id      VARCHAR(255)       NOT NULL,
    inventory_id INT references inventory (id),
    chain_position INT NOT NULL
);

CREATE TABLE IF NOT EXISTS bag
(
    Id              SERIAL PRIMARY KEY NOT NULL,
    volume          INT                NOT NULL,
    occupied_volume INT                NOT NULL,
    inventory_id    INT                NOT NULL references inventory (id)
);

CREATE TABLE IF NOT EXISTS door
(
    id         SERIAL PRIMARY KEY NOT NULL,
    is_open    BOOLEAN            NOT NULL,
    inventory_id INT              NOT NULL references inventory (id)
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

CREATE TABLE IF NOT EXISTS game_map_rooms
(
    rooms_id   INT NOT NULL,
    game_mapdto_id INT NOT NULL,
    rooms_key VARCHAR(255) NOT NULL,
    PRIMARY KEY (rooms_key, game_mapdto_id),
    FOREIGN KEY (game_mapdto_id) REFERENCES game_map (id),
    FOREIGN KEY (rooms_id) REFERENCES room (id)
);

CREATE TABLE IF NOT EXISTS room_npcs
(
    roomdto_id INT NOT NULL,
    npcs_id INT NOT NULL,
    PRIMARY KEY (roomdto_id, npcs_id),
    FOREIGN KEY (roomdto_id) REFERENCES room (id),
    FOREIGN KEY (npcs_id) REFERENCES entity (id)
);
--
-- CREATE SEQUENCE IF NOT EXISTS entity_seq;
-- ALTER SEQUENCE entity_seq owner to paw;
-- ALTER SEQUENCE entity_seq owned by entity.id;


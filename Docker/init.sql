CREATE TABLE IF NOT EXISTS game_map(
id INT PRIMARY KEY NOT NULL,
name VARCHAR(24) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS item(
Id INT PRIMARY KEY NOT NULL,
name VARCHAR(24) UNIQUE NOT NULL,
description VARCHAR(64) NOT NULL,
volume INT NOT NULL
);

CREATE TABLE IF NOT EXISTS type_room(
id INT PRIMARY KEY NOT NULL,
name VARCHAR(24) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS room(
id INT PRIMARY KEY NOT NULL,
name VARCHAR(24) UNIQUE NOT NULL,
position_x INT NOT NULL,
position_y INT NOT NULL,
type_id INT NOT NULL references type_room(id),
map_id INT NOT NULL references game_map(id)
);

CREATE TABLE IF NOT EXISTS direction(
id INT PRIMARY KEY NOT NULL,
name VARCHAR(24) UNIQUE NOT NULL);

CREATE TABLE IF NOT EXISTS room_connection(
id INT PRIMARY KEY NOT NULL,
from_room_id INT NOT NULL references room(id),
direction_id INT NOT NULL references direction(id),
to_room_id INT NOT NULL references room(id)
);

CREATE TABLE IF NOT EXISTS item_room(
Id INT PRIMARY KEY NOT NULL,
Quantity INT NOT NULL,
Item_id INT NOT NULL references item(id),
Room_id INT NOT NULL references room(id)
);

CREATE TABLE IF NOT EXISTS player(
Id INT PRIMARY KEY NOT NULL,
name VARCHAR(24) NOT NULL,
position_x INT NOT NULL,
position_y INT NOT NULL,
lifepoints INT NOT NULL
);

CREATE TABLE IF NOT EXISTS bag(
Id INT PRIMARY KEY NOT NULL,
volume INT NOT NULL,
occupied_volume INT NOT NULL,
player_id INT references player(id)
);

CREATE TABLE IF NOT EXISTS item_bag(
Id INT PRIMARY KEY NOT NULL,
quantity INT NOT NULL,
item_id INT NOT NULL references item(id),
bag_id INT NOT NULL references bag(id)
);

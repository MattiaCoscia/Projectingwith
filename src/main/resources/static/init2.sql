create table eagle
(
    id            bigint not null primary key,
    lifepoints    double precision,
    name          varchar(255),
    position_x    integer,
    position_y    integer,
    age           integer,
    animal_name   varchar(255),
    date_entry    varchar(255),
    height        double precision,
    prefered_food varchar(255),
    weight        double precision,
    wings_spawn   double precision
);

alter table eagle
    owner to paw;

create table game_map
(
    id         serial primary key,
    height_map integer,
    name       varchar(255),
    width_map  integer
);

alter table game_map
    owner to paw;

create table hibernate_sequences
(
    sequence_name varchar(255) not null primary key,
    next_val      bigint
);

alter table hibernate_sequences
    owner to paw;

create table inventory
(
    id bigserial
        primary key
);

alter table inventory
    owner to paw;

create table bag
(
    id              serial primary key,
    occupied_volume integer,
    volume          integer,
    inventory_id    bigint references inventory
);

alter table bag
    owner to paw;

create table item_blueprint
(
    id          serial
        primary key,
    description varchar(255),
    name        varchar(255),
    volume      integer
);

alter table item_blueprint
    owner to paw;

create table item_stored
(
    id            serial primary key,
    hash_key      integer,
    personal_name varchar(255),
    quantity      integer,
    inventory_id  bigint references inventory,
    blueprint_id  integer references item_blueprint
);

alter table item_stored
    owner to paw;

create table door
(
    id         serial
        primary key,
    is_open    boolean,
    itemkey_id integer
        constraint fk5g6vilpavaxmws2nlec7iv59w
            references item_stored
);

alter table door
    owner to paw;

create table inventory_items
(
    inventorydto_id bigint  not null
        constraint fkrk67qac2ilbj6qsoj549ne87q
            references inventory,
    items_id        integer not null
        constraint uk_1qlo05rkkbch6nfcjvkbccgg7
            unique
        constraint fkk3ecjjinf7i420tt92i1g2uad
            references item_stored,
    primary key (inventorydto_id, items_id)
);

alter table inventory_items
    owner to paw;

create table lion
(
    id            bigint not null
        primary key,
    lifepoints    double precision,
    name          varchar(255),
    position_x    integer,
    position_y    integer,
    age           integer,
    animal_name   varchar(255),
    date_entry    varchar(255),
    height        double precision,
    prefered_food varchar(255),
    weight        double precision,
    tail_lenght   double precision
);

alter table lion
    owner to paw;

create table player
(
    id         bigint not null
        primary key,
    lifepoints double precision,
    name       varchar(255),
    position_x integer,
    position_y integer,
    bag_id     integer
        constraint fklbq8qeqa2b9u27i1g04nr99qg
            references bag
);

alter table player
    owner to paw;

create table room
(
    id             serial
        primary key,
    chain_position integer,
    name           varchar(255),
    position_x     integer,
    position_y     integer,
    type_id        varchar(255),
    inventory_id   bigint
        constraint fkrifymwqffxsvxg8jvlbf7t9ck
            references inventory
);

alter table room
    owner to paw;

create table door_connection
(
    from_room_id integer      not null
        constraint fk5ks9m4tt35nmqb7uunwbcy9g0
            references room,
    to_door_id   integer      not null
        constraint fk308tbjpjkw8mn1iny0u9fcwtw
            references door,
    direction_id varchar(255) not null,
    primary key (to_door_id, direction_id)
);

alter table door_connection
    owner to paw;

create table game_map_rooms
(
    game_mapdto_id integer      not null
        constraint fk2t3klwhb92gir2nh7jecqa28h
            references game_map,
    rooms_id       integer      not null
        constraint uk_81nx8n5a4pceihmedd3dbk3qk
            unique
        constraint fkc8ts1arl2vm0e6mbyf9u703mh
            references room,
    rooms_key      varchar(255) not null,
    primary key (game_mapdto_id, rooms_key)
);

alter table game_map_rooms
    owner to paw;

create table room_npcs
(
    roomdto_id integer not null
        constraint fk6q9ow7c9pmh6r2kvd0lasddj3
            references room,
    npcs_id    bigint  not null
        constraint uk_i57sn6kwbl5uq51kfwvn5elcp
            unique
);

alter table room_npcs
    owner to paw;

create table tiger
(
    id            bigint not null
        primary key,
    lifepoints    double precision,
    name          varchar(255),
    position_x    integer,
    position_y    integer,
    age           integer,
    animal_name   varchar(255),
    date_entry    varchar(255),
    height        double precision,
    prefered_food varchar(255),
    weight        double precision,
    tail_lenght   double precision
);

alter table tiger
    owner to paw;


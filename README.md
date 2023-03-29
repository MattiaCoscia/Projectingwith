#This is a project made by Mattia Coscia and Daniele Liburdi.

**The trace for said project is present in lezione2**

------------

#### **The project has said characteristic**

- Tile movement for the player, directions available are north, south, east and west;

- Procedural generation of a map based on a seed (Generation available are Tree-like and Cavern-like)
    - **Tree-like**:
        - The map is generated starting from a room and amplying its size through branches formed by rooms and corridors;
    - **Cavern-like**
        - The map is generated with a stracture having ampious corridors and rooms giving the resemblance of a cavern;

- Render of the map with information about the room in which the player resides and available directions.

------------


### Actions given to the player
- Action given before playing:
    - The player can choose the seed on which the map will be generated and the type of generations among the possible;
- Actions given while playing:
    - **Go**: the player can choose in which direction to go if possible,
        1. North
        1. South
        1. East
        1. West
    - **Get**: The player can choose to get itemStoreds present in the room in which he is present;
    - **Drop**:The player can choose to drop itemStoreds in the room in which he is present;
    - **Look**:The player will receive information about the room in which he is present;
    - **Bag**:The player will receive information on what its bag has inside.

------------

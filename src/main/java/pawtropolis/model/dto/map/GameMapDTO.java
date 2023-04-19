package pawtropolis.model.dto.map;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "game_map")
public class GameMapDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int widthMap;
    private int heightMap;
    @OneToMany
    @MapKeyEnumerated(EnumType.STRING)
    private Map<String, RoomDTO> rooms;
    public GameMapDTO() {
        this.rooms = new HashMap<>();
        widthMap = 10;
        heightMap = 10;
    }
}

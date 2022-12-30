package pawtropolis;

import pawtropolis.controller.GameMapGenerator;
import pawtropolis.view.RenderMap;

public class Avvio {
    public static void main(String[] args) {
        RenderMap.printMap(GameMapGenerator.generateMap());
    }
}

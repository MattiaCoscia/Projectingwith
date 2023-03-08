package pawtropolis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawtropolis.model.entity.Player;
import pawtropolis.model.map.GameMap;
import pawtropolis.view.RenderMapService;
import pawtropolis.view.model.MainFrame;

@Service
@Slf4j
public class GameStarterService {
    @Autowired
    private Player player;
    @Autowired
    private GameMapGeneratorService gameMapGeneratorService;
    @Autowired
    private RenderMapService renderMapService;
    @Autowired
    private MainFrame mainFrame;
    public void execute() {
        mainFrame.start();
        mainFrame.getStarterPanel().getButton().addActionListener(click -> {
            player.setName(mainFrame.getStarterPanel().getNameField().getText());

            gameMapGeneratorService.run(
                    mainFrame.getStarterPanel().getSeedField().getText().hashCode(),
                    String.valueOf(mainFrame.getStarterPanel().getMapTypeField().getSelectedItem())
            );
            if (mainFrame.getStarterPanel().getMapIsVisibleField().isSelected()) {
                renderMapService.setShowMap(true);
            }
            renderMapService.printMap();
            mainFrame.remove(mainFrame.getStarterPanel());
        });
    }

}

package pawtropolis.view.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
@Data
public class MainFrame extends JFrame {
    private StarterOptionsPanel starterPanel;
    @Autowired
    public MainFrame(StarterOptionsPanel starterPanel){
        this.starterPanel = starterPanel;
    }

    public void start(){
        setVisible(true);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocation(0,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        starterPanel.setPreferredSize(new Dimension(getWidth()/3,getHeight()/3));
        add(starterPanel);
        pack();
    }
}

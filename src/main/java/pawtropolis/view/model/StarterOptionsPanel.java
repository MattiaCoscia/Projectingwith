package pawtropolis.view.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pawtropolis.view.Util.SpringUtilities;

import javax.swing.*;
import java.awt.*;

@Component
@Data
public class StarterOptionsPanel extends JPanel {
    private final JButton button = new JButton();
    private final JTextField nameField = new JTextField();
    private final JTextField seedField = new JTextField();
    private final String[] choices = { "cave","tree","frame"};
    private final JComboBox mapTypeField = new JComboBox(choices);
    private final JCheckBox mapIsVisibleField = new JCheckBox();

    private static final Color TEXT_COLOR = Color.lightGray;
    private static final Color BACKGROUND_COLOR = Color.darkGray;

    @Autowired
    public StarterOptionsPanel(){
        setVisible(true);
        setBackground(BACKGROUND_COLOR);
        setLayout(new SpringLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(TEXT_COLOR);
        nameLabel.setLabelFor(nameField);

        JLabel seedLabel = new JLabel("Seed:");
        seedLabel.setForeground(TEXT_COLOR);
        seedLabel.setLabelFor(seedField);

        JLabel mapTypeLabel = new JLabel("Map Type:");
        mapTypeLabel.setForeground(TEXT_COLOR);
        mapTypeLabel.setLabelFor(mapTypeField);

        JLabel allMapVisibleLabel = new JLabel("map visible:");
        allMapVisibleLabel.setForeground(TEXT_COLOR);
        allMapVisibleLabel.setLabelFor(mapIsVisibleField);
        mapIsVisibleField.setBackground(BACKGROUND_COLOR);

        JLabel buttonLabel=new JLabel("confirm");
        buttonLabel.setForeground(TEXT_COLOR);
        buttonLabel.setLabelFor(button);
        button.setText("confirm choices");

        add(nameLabel);
        add(nameField);

        add(seedLabel);
        add(seedField);

        add(mapTypeLabel);
        add(mapTypeField);

        add(allMapVisibleLabel);
        add(mapIsVisibleField);

        add(buttonLabel);
        add(button);

        SpringUtilities.makeCompactGrid(this,
                5, 2,
                6, 6,
                6, 6
        );
    }
}

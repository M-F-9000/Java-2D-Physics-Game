package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSettingsPanel {
    private final MainMenuPanel controlP;
    private final JFrame frame;
    private JPanel mainPanel;
    private JSlider volume;
    private JComboBox level;
    private JButton applyButton;

    public MainSettingsPanel(MainMenuPanel panel) {
        this.controlP = panel;
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(360,480));
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        // Makes apply button save the volume and level value and closes the window
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlP.setSettings(volume.getValue(),level.getSelectedIndex() + 1);
                frame.setVisible(false);
            }
        });

    }

    public void closeWindow() {
        frame.setVisible(false);
    }
}

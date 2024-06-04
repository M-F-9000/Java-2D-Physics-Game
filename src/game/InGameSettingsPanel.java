package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InGameSettingsPanel {
    private final JFrame frame;
    private JPanel mainPanel;
    private JSlider volume;
    private JButton applyButton;
    private JLabel settingsLabel;
    private JLabel volumeLabel;

    public InGameSettingsPanel() {
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(400,250));
        frame.add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        // Makes apply button save the volume value and closes the window
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.updateVolume(volume.getValue());
                frame.setVisible(false);
            }
        });

    }
}

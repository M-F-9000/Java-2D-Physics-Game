package game;

import handler.HighScoreHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenuPanel {
    private final MainMenuPanel controlP;
    private JPanel mainPanel;
    private MainSettingsPanel settingsPanel;
    private JButton playButton;
    private JButton settingsButton;
    private JButton quitButton;
    private JTextField name;
    private JLabel Label;
    private JButton printHighScore;
    private final ArrayList<Integer> settings = new ArrayList<>(3);

    public MainMenuPanel() {
        controlP = this;
        // Adds default volume and starting level values
        settings.add(5);
        settings.add(1);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settingsPanel != null) {
                    settingsPanel.closeWindow();
                }

                // Only starts the game if a name is inputted
                if (!name.getText().isEmpty()) {
                    new Game(settings.get(1));
                } else {
                    System.out.println("Please enter a valid name");
                }
            }
        });

        printHighScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HighScoreHandler scoreH = new HighScoreHandler();
                try {
                    scoreH.readScores();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsPanel = new MainSettingsPanel(controlP);

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    /**
     * Gets the main panel that displays the GUI components.
     *
     * @return panel housing all the components
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Gets the players name.
     *
     * @return string value stored in the text field.
     */
    public String getPlayerName () {
        return name.getText();
    }

    /**
     * Gets volume.
     *
     * @return integer value stored in the slider.
     */
    public Integer getVolume() {
        return settings.get(0);
    }

    /**
     * Sets the starting game settings.
     * <p>
     * Stores the volume and starting level based on the inputs in the settings GUI.
     *
     * @param  volume volume for all game sounds
     * @param  level level to start the game on
     */
    public void setSettings(Integer volume, Integer level){
        settings.add(0,volume);
        settings.add(1,level);
    }
}

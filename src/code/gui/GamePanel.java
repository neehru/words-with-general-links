package src.code.gui;

import javax.swing.*;
import javax.swing.border.Border;

import src.code.Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel{

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolkit.getScreenSize();
    public int windowWidth = dimension.width;
    public int windowHeight = dimension.height;
    Dimension dimension2 = new Dimension(windowWidth/2, windowHeight/2);

    ButtonPanel buttonPanel;
    LivesPanel livesPanel;
    MessagePanel messagePanel;
    JLabel livesRemaining;
    JLabel messageLabel;

    Game game;

    public GamePanel(Game game){


        this.game = game;

        setBackground(Color.white);
        Border blackline = BorderFactory.createLineBorder(Color.black);

        setLayout(new BorderLayout());
        setBorder(blackline);

        // top panel - will consist of title and instruction panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(windowWidth, windowHeight/5));
        add(topPanel, BorderLayout.NORTH);

        JPanel topPadding = new JPanel();
        topPadding.setPreferredSize(new Dimension(windowWidth, (windowHeight/5)/2));
        topPanel.add(topPadding, BorderLayout.NORTH);

        // title "words with general connections" at top of screen
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Words With General Links");
        title.setFont(new Font("Calibri", Font.PLAIN, windowWidth/40));
        titlePanel.add(title);
        topPanel.add(titlePanel, BorderLayout.CENTER);

        // instruction that says create 4 groups of 4
        JPanel instructionPanel = new JPanel();
        JLabel instruction = new JLabel("Create four groups of four");
        instruction.setFont(new Font("Calibri", Font.PLAIN, windowWidth/80));
        instructionPanel.add(instruction);
        topPanel.add(instructionPanel, BorderLayout.SOUTH);

        // button panel in center
        buttonPanel = new ButtonPanel(this);
        buttonPanel.resetClickedButtons();
        buttonPanel.setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.CENTER);

        // left and right padding
        JPanel leftPadding = new JPanel();
        JPanel rightPadding = new JPanel();
        leftPadding.setPreferredSize(new Dimension(windowWidth/4, 4*windowHeight/5));
        rightPadding.setPreferredSize(new Dimension(windowWidth/4, 4*windowHeight/5));
        add(leftPadding, BorderLayout.WEST);
        add(rightPadding, BorderLayout.EAST);

        // bottom panel - will consist of mistakes and submit etc
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(windowWidth/2, windowHeight/4));
        buttonPanel.add(bottomPanel, BorderLayout.SOUTH);

        // lives remaining panel
        livesPanel = new LivesPanel(this);
        livesRemaining = new JLabel();
        livesPanel.setPreferredSize(new Dimension(windowWidth, windowHeight/20));
        livesPanel.add(livesRemaining);
        bottomPanel.add(livesPanel, BorderLayout.NORTH);

        // submit, deselect, shuffle etc
        SelectPanel selectPanel = new SelectPanel(this);

        // SUBMIT button
        selectPanel.submitButton.setEnabled(false);

        // DESELECT button
        selectPanel.deselectButton.setEnabled(false);

        // SHUFFLE button
        selectPanel.shuffleButton.setBackground(buttonPanel.colour);
        selectPanel.shuffleButton.setForeground(Color.white);

        // RETRY button
        selectPanel.retryButton.setBackground(buttonPanel.colour);
        selectPanel.retryButton.setForeground(Color.white);

        // NEW GAME button
        selectPanel.newGameButton.setBackground(buttonPanel.colour);
        selectPanel.newGameButton.setForeground(Color.white);

        selectPanel.add(selectPanel.shuffleButton);
        selectPanel.add(selectPanel.deselectButton);
        selectPanel.add(selectPanel.submitButton);

        selectPanel.add(selectPanel.retryButton);
        selectPanel.add(selectPanel.newGameButton);

        bottomPanel.add(selectPanel, BorderLayout.CENTER);

        // message panel
        messagePanel = new MessagePanel(this);
        messageLabel = new JLabel();
        messagePanel.setPreferredSize(new Dimension(windowWidth, windowHeight/20));
        messagePanel.add(messageLabel);
        
        bottomPanel.add(messagePanel, BorderLayout.SOUTH);

        // adding name at bottom
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Neehru Tumber, 2024"), BorderLayout.CENTER);
        add(namePanel, BorderLayout.SOUTH);

        this.addListeners();
        selectPanel.addListeners();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        repaint();
        
    }

    public void addListeners(){

        buttonPanel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                buttonPanel.buttonPressed((int)getMousePosition().getX(), (int)getMousePosition().getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
    }
}

package com.company.tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI {
    // Declaration of variables
    JFrame frame;
    JButton[][] button;
    JPanel panel;
    tictactoe t;
    
    public GUI() throws TextFieldEmptyException {
        // Initialization of variables
        frame = new JFrame();
        button = new JButton[3][3];
        panel = new JPanel();
        t = new tictactoe();
        t.playerName();
        
        panel.setLayout(new java.awt.GridLayout(3, 3));
        
        //this loop run 9 times for each of the 9 buttons
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                //empty text is assigned to btn, name of the btn is set equal to corresponding row and column, font is set as Arial and font size as 40
                btn.setText("");
                btn.setName(i + "" + j);
                btn.setFont(new Font("Arial", Font.PLAIN, 40));
                
                //when btn is clicked
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean r = t.playerTurn(btn);
                        
                        //clear all the 'X' and 'O' from all the buttons if playerTurn returns true
                        //Resets the game
                        if(r) {
                            for(int x = 0; x < 3; x++) {
                                for(int y = 0; y < 3; y++) {
                                    button[x][y].setText("");
                                }
                            }
                        }
                    }
                });
                
                //value of the temporary button variable is stored in button Array
                button[i][j] = btn;
                //Each button is added to panel
                panel.add(button[i][j]);
            }
        }
        
        //Set JFrame locate at center
        frame.setLocationRelativeTo(null);
        
        //panel is added to frame, size for frame is set to (400, 400) and it's visibility is set to true
        frame.add(panel);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}

package com.company.tictactoe;

import java.awt.GridLayout;
import javax.swing.*;
public class tictactoe {
    // Declaration of variables
    int counter = 0;
    char board[][] = new char[3][3];
    String player1;
    String player2;
    
    boolean playerTurn(JButton button){
        int i = Character.getNumericValue(button.getName().charAt(0));
        int j = Character.getNumericValue(button.getName().charAt(1));
        boolean reset = false;
        
        if(button.getText() == ""){
            if(counter%2 == 0) {
                button.setText("O");
                board[i][j] = 'O';
                counter++;
                reset = gameResult();
            } else if(counter%2 == 1) {
                button.setText("X");
                board[i][j] = 'X';
                counter++;
                reset = gameResult();
            }
        }
        
        return reset;
    }
    
    boolean gameResult(){
        boolean reset = false;
        for(int i = 0; i < 3; i++){
            //horizontal win
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && (board[i][1] == 'X' || board[i][1] == 'O')){
                reset = won(board[i][1]);
                break;
            }
            
            //vertical win
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && (board[1][i] == 'X' || board[1][i] == 'O')){
                reset = won(board[1][i]);
                break;
            }
        }
        
        //diagonal win
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && (board[1][1] == 'X' || board[1][1] == 'O')){
            reset = won(board[1][1]);
        }
    
        //diagonal win
        if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && (board[1][1] == 'X' || board[1][1] == 'O')){
            reset = won(board[1][1]);
        }
        
        //draw
        if(counter >= 9) {
            reset = draw();
        }
        return reset;
    }
    
    // This function resets the whole game
    void reset() {
        //clears the board
        board = new char[3][3];
        counter = 0; //set counter to 0
    }
    
    // This function shows JOptionPane Dialog and asks if the players want to continue or not!
    boolean won(char c) {
        int result = JOptionPane.showConfirmDialog(null, getName(c) + " won. Do you want to play again?","Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(JOptionPane.YES_OPTION == result){
            //Reset if yes is selected
            reset();
            return true;
        }else if (result == JOptionPane.NO_OPTION){
            //Exit if no is selected
            System.exit(0);
            return false;
        }else {
            //Exit if cancelled
            System.exit(0);
            return false;
        }
    }
    
    // This function shows JOptionPane Dialog and asks if the players want to continue or not!
    boolean draw() {
        int result = JOptionPane.showConfirmDialog(null,"It is a draw. Do you want to play again?","Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(JOptionPane.YES_OPTION == result){
            //Reset if yes is selected
            reset();
            return true;
        }else if (result == JOptionPane.NO_OPTION){
            //Exit if no is selected
            System.exit(0);
            return false;
        }else {
            //Exit if cancelled
            System.exit(0);
            return false;
        }
    }
    
    void playerName() throws TextFieldEmptyException{
        //Declaration and initialization of GUI Elements
        //These GUI elements will be shown in dialog window
        JPanel Joptionpanel = new JPanel();
        Joptionpanel.setLayout(new GridLayout(4,1));
        
        JLabel p1 = new JLabel("Name of Player 1");
        JLabel p2 = new JLabel("Name of Player 2");
        
        JTextField textField1 = new JTextField(12);
        JTextField textField2 = new JTextField(12);
        
        Joptionpanel.add(p1);
        Joptionpanel.add(textField1);
        Joptionpanel.add(p2);
        Joptionpanel.add(textField2);
        
        int result = JOptionPane.showConfirmDialog(null, Joptionpanel, "Enter the name of the players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(JOptionPane.OK_OPTION == result){
            player1 = textField1.getText();
            player2 = textField2.getText();
            
            //exception handling for empty string as a value for name of the players
            try {
                if(player1.isEmpty()){
                    throw new TextFieldEmptyException("Name of player 1 is Empty.");
                }
                if(player2.isEmpty()) {
                    throw new TextFieldEmptyException("Name of player 2 is Empty.");
                }
            }
            catch(TextFieldEmptyException e){
                JOptionPane.showMessageDialog(null, e);
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
    
    //This function is used to get correcponding player name for their mark
    String getName(char c) {
        if(c == 'O') {
            return player1;
        } else {
            return player2;
        }
    }
}
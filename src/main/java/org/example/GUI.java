package org.example;

import javax.swing.*;
import java.awt.*;

public class GUI {

    JPanel panel;
    JButton park;
    JButton leave;
    JTextField carNumber;
    JLabel upLabel;
    JLabel infoText;


    public JPanel getPanel(){
        return panel;
    }

    public void onPark(GUIAction action){
        addAction(park, action);
    }

    public void onLeave(GUIAction action){
        addAction(leave, action);
    }

    public void addAction(JButton button, GUIAction action){
        button.addActionListener(e -> {
            String text = carNumber.getText();
            if (text.isBlank()){
                showError("Car number must be specified!");
                return;
            }
            try {
                action.execute(text) ;
                showInfo("The job is done");
            }
            catch (GUIException g){
                showError(g.getMessage());
            }
            finally {
                carNumber.setText("");
            }
        });
    }

    public void showInfo(String msg){
        infoText.setForeground(Color.black);
        infoText.setText(msg);
    }

    public void showError(String msg){
        infoText.setForeground(Color.red);
        infoText.setText(msg);
    }

}


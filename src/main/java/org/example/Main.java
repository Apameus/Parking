package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, LocalDateTime> cars = new HashMap<>();

        GUI gui = new GUI();

        gui.onPark(inputNumber -> {
            if (cars.containsKey(inputNumber)){
                throw new GUIException("Car number already in the list");
            }
            cars.put(inputNumber,LocalDateTime.now());
            return "Car parked";
        });

        gui.onLeave(e ->{
            if (!cars.containsKey(e)) {
                throw new GUIException("Car number isn't in the list");
            }
            else {
                LocalDateTime start = cars.get(e);
                var hours = ChronoUnit.HOURS.between(start, LocalDateTime.now());
                return  "You must pay: " + priceFor((int) hours);
            }
        });

        JFrame frame = new JFrame("Parking");
        JPanel panel = gui.getPanel();

        frame.add(panel);

        frame.setSize(300, 300);
//        panel.setSize(800, 800);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }



    private static int priceFor (int hours){
        if (hours < 3){
            return 3 * hours;
        }
        if (hours > 3 && hours < 10){
            return 2 * hours;
        }
        return 1 * hours;

    }
}
package tree.graphics;

import tree.*;
import tree.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


// AWT Abstract Window Toolkit
// Swing
// http://zetcode.com/tutorials/javaswingtutorial/

public class MainFrame extends JFrame {

    private ViewPanel view;
    private Timer timer;

    private void onStartClick() {
//        view.SetRandom(10);
        timer.start();
    }

    public MainFrame() {
        setTitle("Рисуем в Java");
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton button = new JButton();
        button.setText("Start");


        JPanel top = new JPanel();
        top.setSize(100, 40);
        top.add(button);

        ArrayList<Point> a = new ArrayList<>();
        Random k = new Random();

        for (int i = 0; i < 10; i++) {
            a.add(new tree.Point(k.nextDouble() , k.nextDouble() ));
        }
        Ktree temp = new Ktree(a);
        temp.build();

        add(top, BorderLayout.NORTH);

        view = new ViewPanel( temp.vertexList, a);
        add(view);

        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.next();
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Hello, world!");
                onStartClick();
            }
        });
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

        //EventQueue.invokeLater(new Runnable() {
        //    @Override
        //    public void run() {
        //        MainFrame frame = new MainFrame();
        //        frame.setVisible(true);
        //    }
        //});
    }
}

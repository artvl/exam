package algojava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// AWT Abstract Window Toolkit
// Swing
// http://zetcode.com/tutorials/javaswingtutorial/

public class MainFrame extends JFrame {

    private ViewPanel view;
    private Timer timer;

    private void onStartClick() {
        view.SetRandom(10);
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

        JButton button2 = new JButton();
        button2.setText("X");

        JButton button3 = new JButton();
        button3.setText("Long button text");

        JPanel top = new JPanel();
        top.setSize(100, 40);
        top.add(button);
        top.add(button2);
        top.add(button3);

        add(top, BorderLayout.NORTH);

        view = new ViewPanel();
        add(view);

        timer = new Timer(1000, new ActionListener() {
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

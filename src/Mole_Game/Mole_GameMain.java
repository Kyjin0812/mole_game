package Mole_Game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Mole_GameMain extends JFrame implements ActionListener {
    static final int WIDTH = 1024;
    static final int HEIGHT = 720;
    private JLabel label1, label2;
    Hammer2p hammer2p = new Hammer2p(720, 250);
    Normal_Mole Normal_Mole = new Normal_Mole((int) (Math.random() * (Mole_GameMain.WIDTH-100)),
            (int) (Math.random() * (Mole_GameMain.HEIGHT-115)));
    class MyPanel extends JPanel {
        public MyPanel() {
            Runnable task = () -> {
                while (true) {
                    if((Normal_Mole.x <= hammer2p.x && hammer2p.x <= Normal_Mole.x+100)&&(Normal_Mole.y <= hammer2p.y+100 && hammer2p.y+100 <= Normal_Mole.y+115)&& hammer2p.smash_state){
                        Normal_Mole.update((int) (Math.random() * (Mole_GameMain.WIDTH-100)), (int) (Math.random() * (Mole_GameMain.HEIGHT-115)));
                    }
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignore) {
                    }
                }
            };
            new Thread(task).start();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Normal_Mole.draw(g);
            hammer2p.draw(g);
        }
    }

    public Mole_GameMain() {
        MyPanel panel = new MyPanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(panel);
        pack();
        setVisible(true);
        panel.requestFocus();
        panel.setFocusable(true);

        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    hammer2p.move_left();
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    hammer2p.move_right();
                }
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    hammer2p.move_up();
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    hammer2p.move_down();
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    hammer2p.smash();
                }
            }

            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    hammer2p.return_hammer();
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        //repaint();
    }

    public static void main(String[] args) {
        Mole_GameMain m = new Mole_GameMain();
    }
}

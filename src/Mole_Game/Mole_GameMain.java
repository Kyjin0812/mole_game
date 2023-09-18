package Mole_Game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Mole_GameMain extends JFrame implements ActionListener {
    static final int WIDTH = 1024;
    static final int HEIGHT = 720;
    private JLabel label1, label2;
    Hammer1p hammer1p = new Hammer1p(120, 250);
    Hammer2p hammer2p = new Hammer2p(720, 250);
    public ArrayList<Normal_Mole> normal_mole_list = new ArrayList<Normal_Mole>(5);

    class MyPanel extends JPanel {
        public MyPanel() {
            Runnable mole = () -> {
                while (true) {
                    for(int i=0; i<normal_mole_list.size(); i++) {
                        normal_mole_list.get(i).time_decrease();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {
                    }
                }
            };
            Runnable hammer = () -> {
                for(int i=0; i<5; i++) {
                    normal_mole_list.add(new Normal_Mole((int) (Math.random() * (Mole_GameMain.WIDTH-100)),
                            (int) (Math.random() * (Mole_GameMain.HEIGHT-115)), (int) (Math.random() * 7 + 3)));
                }

                while (true) {
                    for(int i=0; i<normal_mole_list.size(); i++) {
                        if((normal_mole_list.get(i).x <= hammer2p.x && hammer2p.x <= normal_mole_list.get(i).x+100)&&(normal_mole_list.get(i).y <= hammer2p.y+100 && hammer2p.y+100 <= normal_mole_list.get(i).y+115)&& hammer2p.smash_state){
                            normal_mole_list.get(i).update_by_smash((int) (Math.random() * (Mole_GameMain.WIDTH-100)), (int) (Math.random() * (Mole_GameMain.HEIGHT-115)));
                        }
                        if(normal_mole_list.get(i).cooldown == 0) {
                            normal_mole_list.get(i).update_by_time_reset((int) (Math.random() * (Mole_GameMain.WIDTH-100)), (int) (Math.random() * (Mole_GameMain.HEIGHT-115)), (int) (Math.random() * 7 + 3));
                        }
                    }
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignore) {
                    }
                }
            };
            new Thread(mole).start();
            new Thread(hammer).start();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Normal_Mole nm : normal_mole_list) {
                nm.draw(g);
            }
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

package Mole_Game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Mole_GameMain extends JFrame implements ActionListener {
    static final int WIDTH = 1024;
    static final int HEIGHT = 720;
    static final int MOLE_COUNT = 3;
    private JLabel label1, label2, label3;
    public ArrayList<Normal_Mole> normal_mole_list = new ArrayList<Normal_Mole>(MOLE_COUNT);

    static Hammer1p hammer1p = new Hammer1p(120, 250);
    static Hammer2p hammer2p = new Hammer2p(720, 250);
    static int score_1p = 0, score_2p = 0, time = 0;

    class MyPanel extends JPanel {
        public MyPanel() {
            Runnable mole = () -> {
                while (true) {
                    for (Normal_Mole normalMole : normal_mole_list) {
                        normalMole.time_decrease();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {
                    }
                }
            };
            Runnable hammer = () -> {
                for(int i=0; i<MOLE_COUNT; i++) {
                    normal_mole_list.add(new Normal_Mole((int) (Math.random() * (Mole_GameMain.WIDTH-100)),
                            (int) (Math.random() * (Mole_GameMain.HEIGHT-115)), (int) (Math.random() * 7 + 3)));
                }
                while (true) {
                    for (Normal_Mole normalMole : normal_mole_list) {
                        if (normalMole.cooldown == 0) {
                            normalMole.update_by_time_reset((int) (Math.random() * (Mole_GameMain.WIDTH - 200) + 100), (int) (Math.random() * (Mole_GameMain.HEIGHT - 150) + 80), (int) (Math.random() * 7 + 3));
                        }
                        if ((normalMole.x <= hammer2p.x && hammer2p.x <= normalMole.x + 100) && (normalMole.y <= hammer2p.y + 80 && hammer2p.y + 80 <= normalMole.y + 115) && hammer2p.smash_state) {
                            normalMole.update_by_smash((int) (Math.random() * (Mole_GameMain.WIDTH - 100)), (int) (Math.random() * (Mole_GameMain.HEIGHT - 115)));
                            score_2p += 10;
                        }
                        if ((normalMole.x <= hammer1p.x + 100 && hammer1p.x + 100 <= normalMole.x + 100) && (normalMole.y <= hammer1p.y + 80 && hammer1p.y + 80 <= normalMole.y + 115) && hammer1p.smash_state) {
                            normalMole.update_by_smash((int) (Math.random() * (Mole_GameMain.WIDTH - 100)), (int) (Math.random() * (Mole_GameMain.HEIGHT - 115)));
                            score_1p += 10;
                        }
                    }
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignore) {
                    }
                }
            };
            Runnable timer = () -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignore) {
                    }
                    if(time < 60) {
                        time += 1;
                        label3.setText(""+time);
                    }
                    else {
                        break;
                    }
                }
            };
            new Thread(mole).start();
            new Thread(hammer).start();
            new Thread(timer).start();
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Normal_Mole nm : normal_mole_list) {
                nm.draw(g);
            }
            hammer1p.draw(g);
            hammer2p.draw(g);
        }
    }

    public Mole_GameMain() {
        MyPanel panel = new MyPanel();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setTitle("Mole Game");
        add(panel);
        pack();
        label1 = new JLabel("1p Score : " + score_1p);
        label2 = new JLabel("2p Score : " + score_2p);
        label3 = new JLabel("" + time);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        setVisible(true);
        panel.setFocusable(true);
        panel.setLayout(null);
        panel.requestFocus();

        label1.setBounds(0, 0, 100, 20);
        label2.setBounds(WIDTH-90, 0, 100, 20);
        label3.setBounds(WIDTH/2, 0, 100, 20);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    hammer1p.move_left();
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    hammer1p.move_right();
                }
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    hammer1p.move_up();
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    hammer1p.move_down();
                }
                if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    hammer1p.smash();
                }
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
                if(e.getKeyCode() == KeyEvent.VK_SLASH) {
                    hammer2p.smash();
                }
            }

            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    hammer1p.return_hammer();
                    label1.setText("1p Score : " + score_1p);
                }
                if(e.getKeyCode() == KeyEvent.VK_SLASH) {
                    hammer2p.return_hammer();
                    label2.setText("2p Score : " + score_2p);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        new Mole_GameMain();
    }
}

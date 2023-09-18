package Mole_Game;

import javax.swing.*;
import java.awt.*;

public class Hammer1p {
    int x, y;
    Boolean smash_state = false;
    Image img;

    Hammer1p(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon icon = new ImageIcon("..\\Mole_Game\\src\\Mole_Game\\hammer1.png");
        img = icon.getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public void move_left() {
        x -= 10;
        if(x<0) {
            x=0;
        }
    }

    public void move_right() {
        x += 10;
        if(x>Mole_GameMain.WIDTH-139) {
            x = Mole_GameMain.WIDTH-139;
        }
    }

    public void move_up() {
        y -= 10;
        if(y<0) {
            y=0;
        }
    }

    public void move_down() {
        y += 10;
        if(y>Mole_GameMain.HEIGHT-164) {
            y=Mole_GameMain.HEIGHT-164;
        }
    }

    public void smash() {
        ImageIcon icon = new ImageIcon("../Mole_Game/src/Mole_Game/smash1.png");
        img = icon.getImage();
        smash_state = true;
    }

    public void return_hammer() {
        ImageIcon icon = new ImageIcon("../Mole_Game/src/Mole_Game/hammer1.png");
        img = icon.getImage();
        smash_state = false;
    }
}
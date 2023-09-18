package Mole_Game;

import javax.swing.*;
import java.awt.*;

public class Normal_Mole {
    int x, y, cooldown;
    Image img;

    Normal_Mole(int x, int y, int cooldown) {
        this.x = x;
        this.y = y;
        this.cooldown = cooldown;
        ImageIcon icon = new ImageIcon("../Mole_Game/src/Mole_Game/Mole.png");
        img = icon.getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public void time_decrease() {
        this.cooldown --;
    }

    public void update_by_time_reset(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.cooldown = time;
    }

    public void update_by_smash(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

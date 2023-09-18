package Mole_Game;

import javax.swing.*;
import java.awt.*;

public class Normal_Mole {
    int x, y;
    Image img;

    Normal_Mole(int x, int y) {
        this.x = x;
        this.y = y;
        ImageIcon icon = new ImageIcon("../Mole_Game/src/Mole_Game/Mole.png");
        img = icon.getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

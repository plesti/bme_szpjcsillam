package hu.gghf.view;

import hu.gghf.entities.Player;
import hu.gghf.entities.Replicator;
import hu.gghf.entities.ZPM;
import hu.gghf.model.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapPanel extends Panel {
    private final Font font;
    private Map map;

    public MapPanel(Map map) {
        this.map = map;
        this.font = new Font("CounterFont", Font.PLAIN, 15);
    }

    @Override
    public void paint(Graphics graphics) {
        // Ez a buffer
        BufferedImage buffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics canvas = buffer.getGraphics();

        int wsize = this.getWidth()/map.maxsize;
        int hsize = this.getHeight()/map.maxsize;

        for (int i = 0; i < map.maxsize; i++) {
            for (int j = 0; j < map.maxsize; j++) {
                BufferedImage img = map.getMapObject(new Point(i, j)).getImage();
                canvas.drawImage(img, i * wsize, j * hsize, wsize, hsize, this);

                hu.gghf.entities.Box b;
                if ((b = map.getBox(new Point(i, j))) != null) {
                    canvas.drawImage(b.getImage(), i * wsize, j * hsize, wsize, hsize, this);
                }
            }

            Replicator rep;
            if ((rep = map.getReplicator()) != null) {
                Point pos = rep.getPosition();
                canvas.drawImage(rep.getImage(), pos.x * wsize, pos.y * hsize, wsize, hsize, this);
            }
            Player player1;
            if ((player1 = map.getPlayer("1")) != null) {
                Point pos = player1.getPosition();
                canvas.drawImage(player1.getImage(), pos.x * wsize, pos.y * hsize, wsize, hsize, this);
            }
            Player player2;
            if ((player2 = map.getPlayer("0")) != null) {
                Point pos = player2.getPosition();
                canvas.drawImage(player2.getImage(), pos.x * wsize, pos.y * hsize, wsize, hsize, this);
            }
            canvas.setFont(this.font);
            canvas.drawString("Oneil: " + ZPM.getZPMCount(player2), 10, font.getSize());
            canvas.drawString("Jaffa: " + ZPM.getZPMCount(player1), 10, 2*font.getSize());
        }
        graphics.drawImage(buffer, 0, 0, this);
    }
}

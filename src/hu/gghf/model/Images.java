package hu.gghf.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    public static BufferedImage emptycell;
    public static BufferedImage hole;
    public static BufferedImage portal_blue;
    public static BufferedImage portal_blue_open;
    public static BufferedImage portal_green;
    public static BufferedImage portal_green_open;
    public static BufferedImage portal_red;
    public static BufferedImage portal_red_open;
    public static BufferedImage portal_yellow;
    public static BufferedImage portal_yellow_open;
    public static BufferedImage portal_wall;
    public static BufferedImage pressure_plate;
    public static BufferedImage shieldgen;
    public static BufferedImage wall;
    public static BufferedImage zpm;

    public static BufferedImage door_closed;
    public static BufferedImage door_open;

    public static BufferedImage box;
    public static BufferedImage replicator;
    public static BufferedImage jaffa;
    public static BufferedImage oneil;

    public static void load() {
        try {
            emptycell = ImageIO.read(new File("images/cell.png"));
            hole = ImageIO.read(new File("images/hole.png"));
            portal_blue = ImageIO.read(new File("images/portal_blue.png"));
            portal_blue_open = ImageIO.read(new File("images/portal_blue_open.png"));
            portal_green = ImageIO.read(new File("images/portal_green.png"));
            portal_green_open = ImageIO.read(new File("images/portal_green.png"));
            portal_red = ImageIO.read(new File("images/portal_red.png"));
            portal_red_open = ImageIO.read(new File("images/portal_red.png"));
            portal_yellow = ImageIO.read(new File("images/portal_yellow.png"));
            portal_yellow_open = ImageIO.read(new File("images/portal_yellow_open.png"));
            portal_wall = ImageIO.read(new File("images/portal_wall.png"));
            pressure_plate = ImageIO.read(new File("images/pressure_plate.png"));
            shieldgen = ImageIO.read(new File("images/shield_generator.png"));
            wall = ImageIO.read(new File("images/wall.png"));
            zpm = ImageIO.read(new File("images/zpm.png"));

            box = ImageIO.read(new File("images/box.png"));
            replicator = ImageIO.read(new File("images/replicator.png"));
            jaffa = ImageIO.read(new File("images/jaffa.png"));
            oneil = ImageIO.read(new File("images/oneil.png"));

            door_closed = ImageIO.read(new File("images/door.png"));
            door_open = ImageIO.read(new File("images/door_open.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

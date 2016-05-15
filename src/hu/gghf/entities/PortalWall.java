package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Location;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Application;
import hu.gghf.view.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class PortalWall extends Location implements CellInterface {
    private static Map<Color, PortalWall> portals = new HashMap<>();

    public PortalWall(Point position) {
        this.setPosition(position);
    }

    @Override
    public boolean isStepable() {
        if (portals.get(Color.BLUE) == this && portals.containsKey(Color.YELLOW)) {
            return true;
        } else if (portals.get(Color.YELLOW) == this && portals.containsKey(Color.BLUE)) {
            return true;
        } else if (portals.get(Color.RED) == this && portals.containsKey(Color.GREEN)) {
            return true;
        } else if (portals.get(Color.GREEN) == this && portals.containsKey(Color.RED)) {
            return true;
        }
        return false;
    }

    public Color getColor() {
        for (Color color : portals.keySet()) {
            if (portals.get(color) == this) {
                return color;
            }
        }
        return null;
    }

    @Override
    public void onStepIn(Moveable object) {
        Color color = getColor();
        PortalWall otherside = null;
        if (color == Color.BLUE && portals.containsKey(Color.YELLOW)) {
            otherside = portals.get(Color.YELLOW);
        } else if (color == Color.YELLOW && portals.containsKey(Color.BLUE)) {
            otherside = portals.get(Color.BLUE);
        } else if (color == Color.RED && portals.containsKey(Color.GREEN)) {
            otherside = portals.get(Color.GREEN);
        } else if (color == Color.GREEN && portals.containsKey(Color.RED)) {
            otherside = portals.get(Color.RED);
        } else {
            return;
        }
        object.setPosition(otherside.getPosition());
        object.setDirection(otherside.getDirection());
    }

    @Override
    public void onStepOut(Moveable object) {
    }

    @Override
    public boolean isShootable() {
        return true;
    }

    @Override
    public void shot(Player player, Color color) {
//        Remove the any other color from this portal
        if (portals.containsValue(this)) {
            portals.remove(this.getColor());
        }

//        Set color for this portal
        if (portals.containsKey(color)) {
            portals.replace(color, this);
        } else {
            portals.put(color, this);
        }

        Direction pdir = player.getDirection();

//        Set the direction of this portal opposite to the player
//        When a player steps teleports to this portal this direction will be applied
        switch (pdir) {
            case UP:
                setDirection(Direction.DOWN);
                break;
            case DOWN:
                setDirection(Direction.UP);
                break;
            case LEFT:
                setDirection(Direction.RIGHT);
                break;
            case RIGHT:
                setDirection(Direction.LEFT);
                break;
        }

        Application.printCall(String.format("Portal loves: [%s,%s] %s -> %s",
                getPosition().x,
                getPosition().y,
                color,
                isStepable()));
    }

    @Override
    public BufferedImage getImage() {
        if (getColor() == null) {
            return Images.portal_wall;
        }
        
        if (isStepable()) {
            switch (getColor()) {
                case BLUE:
                    return Images.portal_blue_open;
                case YELLOW:
                    return Images.portal_yellow_open;
                case RED:
                    return Images.portal_red_open;
                case GREEN:
                    return Images.portal_green_open;
            }
        } else {
            switch (getColor()) {
                case BLUE:
                    return Images.portal_blue;
                case YELLOW:
                    return Images.portal_yellow;
                case RED:
                    return Images.portal_red;
                case GREEN:
                    return Images.portal_green;
            }
        }
        return null;
    }
}
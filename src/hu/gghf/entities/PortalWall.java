package hu.gghf.entities;

import hu.gghf.model.Application;

import java.awt.*;

public class PortalWall extends Location implements CellInterface {
    private static PortalWall blue = null;
    private static PortalWall yellow = null;

    public PortalWall(Point position) {
        this.setPosition(position);
    }

    @Override
    public boolean isStepable() {
        Application.printCall(this, "-->isStepable()");

        if (blue == this && yellow != null) {
            Application.printCall(this, "<--");
            return true;
        } else if (yellow == this && blue != null) {
            Application.printCall(this, "<--");
            return true;
        }
        Application.printCall(this, "<--");
        return false;
    }

    @Override
    public void onStepIn(Location object) {
        Application.printCall(this, "-->onStepIn()");

        if (blue == this && yellow != null) {
            object.setPosition(yellow.getPosition());
            object.setDirection(yellow.getDirection());
        } else if (yellow == this && blue != null) {
            object.setPosition(blue.getPosition());
            object.setDirection(blue.getDirection());
        }
        Application.printCall(this, "<--");
    }

    @Override
    public void onStepOut() {
        Application.printCall(this, "-->onStepOut()");

        Application.printCall(this, "<--");
    }

    @Override
    public boolean isShootable() {
        Application.printCall(this, "-->isShootable()");

        Application.printCall(this, "<--");
        return true;
    }

    @Override
    public void shoot(Player player, Color color) {
        Application.printCall(this, "-->shoot()");

        if (color == Color.BLUE) {
            blue = this;
            System.out.println("Kekvagyok");
//            System.out.println(String.format("Kekvagyok: %s,%s", getPosition().x, getPosition().y));
        } else {
            yellow = this;
            System.out.println("Sargavagyok");
//            System.out.println(String.format("Sargavagyok: %s,%s", getPosition().x, getPosition().y));
        }

        Direction pdir = player.getDirection();

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
        Application.printCall(this, "<--");
    }

    @Override
    public void destroy() {
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }
}
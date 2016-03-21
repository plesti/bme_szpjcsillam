package hu.gghf.entities;

import hu.gghf.model.Application;
import hu.gghf.model.Game;
import hu.gghf.model.Test;

import java.awt.*;
import java.io.IOException;

public class Player extends Location {
    private Box carryObject;
    private Game game;

    public Player(Game game) {
        this.game = game;
    }

    @Override
    public void destroy() {
        Application.printCall(this, "-->destroy()");
        Application.printCall(this, "<--");
    }

    public void setCarry(Box box) {
        Application.printCall(this, "-->setCarry()");
        carryObject = box;
        Application.printCall(this, "<--");
    }
    public Box getCarry() {
        Application.printCall(this, "-->getCarry()");
//        return carryObject;

//        TODO: TOROLNI
        Application.printCall(this, "<--");
        return Test.carryBox;
    }
    public boolean isCarry() {
        Application.printCall(this, "-->isCarry()");
//        return carryObject != null;

//        TODO: TOROLNI
        Application.printCall(this, "<--");
        return Test.iscarry;
    }

    public Point posInDirection(Direction direction, int distance) {
        Application.printCall(this, "-->posInDirection()");

        Point pos = getPosition();
        int x = pos.x, y = pos.y;

        switch (direction) {
            case UP:
                y -= distance;
                break;
            case DOWN:
                y += distance;
                break;
            case LEFT:
                x -= distance;
                break;
            case RIGHT:
                x += distance;
                break;
        }
        Application.printCall(this, "<--");
        return new Point(x, y);
    }

    public CellInterface findTarget() {
        Application.printCall(this, "-->findTarget()");

        Direction dir = getDirection();

        CellInterface target;

        // 8 egyseg tavolra lehet loni
        for (int i = 1; i < 8; i++) {
            Point pos = posInDirection(dir, i);
            target = game.getMapObject(pos);

            boolean isshootable = target.isShootable();
            if (isshootable) {

                Application.printCall(this, "<-- target");
                return target;
            }
        }
        Application.printCall(this, "<--");
        return null;
    }

    public void goForward() {
        Application.printCall(this, "-->goForward()");

        Location.Direction dir = getDirection();

        Point frontpos = posInDirection(dir, 1);
        Point currentpos = getPosition();

//        TODO: KITOROLNI PROTOBa
        System.out.println("?Milyen objektum legyen a jatekos elott?");
        Test.setSelected();

        CellInterface frontcell = game.getMapObject(frontpos);

        // TODO: ez is debug kod->TOROLNI! res mezot adjon vissza a getMapObject
        Test.selected = Test.empty;

        CellInterface currentcell = game.getMapObject(currentpos);

        boolean isstepable = frontcell.isStepable();

        if (isstepable) {

            // TODO: Torolni! A teszt resze
            Test.setIscarry();

            boolean iscarry = isCarry();

            Box getbox = game.getBox(frontpos);

            if (iscarry) {
                Point frontpos2 = posInDirection(dir, 2);

                // TODO: Torolni! A teszt resze
                System.out.println("?Milyen objektum legyen a kettovel jatekos elott?");
                Test.setSelected();

                CellInterface frontcell2 = game.getMapObject(frontpos2);
                Box getbox2 = game.getBox(frontpos2);

                // Ha nincs elotte doboz es kettovel elotte ures
                if (frontcell2.isStepable() && getbox2 == null) {
                    currentcell.onStepOut();
                    frontcell.onStepOut();

                    // Player elore lep
                    setPosition(frontpos);
                    frontcell.onStepIn(this);

                    // Azert kell mert lehet h portal-ba lep es akkor a doboznake ele kell kerulnie
                    Point newfront = posInDirection(dir, 1);

                    Box box = getCarry();
                    box.setPosition(newfront);
                    frontcell2.onStepIn(box);
                }

            } else if (!iscarry && getbox == null) {
                currentcell.onStepOut();
                setPosition(frontpos);
                frontcell.onStepIn(this);
            }
        }

        Application.printCall(this, "<--");
    }

    public void turn(Direction newdir) {
        Application.printCall(this, "-->turn()");

        // TODO: Torolni! A teszt resze
        System.out.println("?Visz a jatekos dobozt?\ni/n");
        boolean iscarry = false;
        try {
            iscarry = (Test.br.readLine().equals("i"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        boolean iscarry = isCarry();

        if (iscarry) {
            // TODO itt volt egy getDirection ami nem kell

            Point newpos = posInDirection(newdir, 1);

            // TODO: torolni
            System.out.println("?Milyen objektum legyen a doboz jovobeli helyen?");
            Test.setSelected();

            CellInterface newcell = game.getMapObject(newpos);
            Box box = getCarry();
            Point boxpos = box.getPosition();

            // TODO: ez is debug kod->TOROLNI! res mezot adjon vissza a getMapObject
            Test.selected = Test.empty;

            CellInterface boxcell = game.getMapObject(boxpos);

            boolean isbox = game.isBox(newpos);
            boolean isstepable = newcell.isStepable();

            if (!isbox && isstepable) {
                boxcell.onStepOut();
                setDirection(newdir);
                box.setPosition(newpos);
                newcell.onStepIn(box);
            }

        } else {
            setDirection(newdir);
        }
        Application.printCall(this, "<--");
    }
}

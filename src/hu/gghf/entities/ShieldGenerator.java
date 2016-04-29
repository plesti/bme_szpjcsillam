package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.Application;
import hu.gghf.model.Images;

import java.awt.image.BufferedImage;

public class ShieldGenerator implements CellInterface {
    public ShieldGenerator() {
    }

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable object) {
        int zpms = ZPM.getZPMCount(object);

        if (zpms >= 4) {
            Application.printCall(this, "Jatekos nyert!");
        }
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
    }

    @Override
    public BufferedImage getImage() {
        return Images.shieldgen;
    }
}

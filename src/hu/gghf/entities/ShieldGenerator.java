package hu.gghf.entities;

import hu.gghf.interfaces.CellInterface;
import hu.gghf.interfaces.Moveable;
import hu.gghf.model.GameEventHandler;
import hu.gghf.view.Application;
import hu.gghf.view.Images;

import java.awt.image.BufferedImage;

public class ShieldGenerator implements CellInterface {
    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable object) {
        int zpms = ZPM.getZPMCount(object);

        if (zpms >= 4) {
            try {
                GameEventHandler.playerWins((Player) object);
            } catch (Exception e) {
                GameEventHandler.printCall("Ismeretlen objektum nyert");
            }
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

package hu.gghf.entities;

public class ZPM extends AbstractCell {
    private boolean discovered = false;

    @Override
    public boolean isStepable() {
        return true;
    }

    @Override
    public void onStepIn(Moveable obj) {
        super.onStepIn(obj);

        if (!discovered && obj.getClass() == Player.class) {
            Player player = (Player) obj;
            player.addZPM();
        }
    }
}

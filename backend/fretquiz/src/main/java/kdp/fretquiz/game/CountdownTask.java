package kdp.fretquiz.game;

import java.util.TimerTask;
import java.util.function.Consumer;

class CountdownTask extends TimerTask {

    private int secondsLeft;
    private final Consumer<Integer> everySecond;
    private final Runnable beforeCancel;

    public CountdownTask(int secondsLeft, Consumer<Integer> everySecond, Runnable beforeCancel) {
        this.secondsLeft = secondsLeft;
        this.everySecond = everySecond;
        this.beforeCancel = beforeCancel;
    }

    @Override
    public void run() {
        if (secondsLeft <= 0) {
            beforeCancel.run();
            cancel();
        } else {
            everySecond.accept(secondsLeft);
            --secondsLeft;
        }
    }
}

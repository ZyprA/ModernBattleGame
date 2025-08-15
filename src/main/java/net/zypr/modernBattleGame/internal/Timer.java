package net.zypr.modernBattleGame.internal;

public class Timer {
    private final int duration;
    private int tick = 0;

    public Timer(int duration) {
        this.duration = duration;
    }

    public int getTick() {
        return this.tick;
    }

    public int getDuration() {
        return this.duration;
    }

    public void update() {
        this.tick += duration;
    }
}

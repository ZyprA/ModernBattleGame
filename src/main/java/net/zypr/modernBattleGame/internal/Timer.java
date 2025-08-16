package net.zypr.modernBattleGame.internal;

public class Timer {
    private int tick = 0;
    private boolean clock = false;

    protected void setTick(int tick) {
        this.tick = tick;
    }
    protected void addTick(int tick) {
        this.tick += tick;
    }
    protected void updateClock() {this.clock = !this.clock;}

    public boolean getClock() {return this.clock;}
    public int getTick() {return this.tick;}
}

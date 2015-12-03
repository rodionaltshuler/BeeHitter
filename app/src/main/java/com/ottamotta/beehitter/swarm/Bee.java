package com.ottamotta.beehitter.swarm;

public abstract class Bee {

    private int hitPointsRemaining;

    private int id;

    public Bee(int id) {
        this.id = id;
        hitPointsRemaining = getLifespan();
    }

    public void hit() {
        hitPointsRemaining -= getHitpoints();
    }

    public boolean isDead() {
        return hitPointsRemaining <= 0;
    }

    public boolean killsAllOthersWhenDie() {
        return false;
    }

    abstract int getLifespan();
    abstract int getHitpoints();
    public abstract Swarm.BeeType getBeeType();

    public int getHitPointsRemaining() {
        return hitPointsRemaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bee bee = (Bee) o;

        return id == bee.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}

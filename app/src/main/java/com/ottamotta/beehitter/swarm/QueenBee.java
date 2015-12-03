package com.ottamotta.beehitter.swarm;

class QueenBee extends Bee {

    public QueenBee(int id) {
        super(id);
    }

    @Override
    int getLifespan() {
        return 100;
    }

    @Override
    int getHitpoints() {
        return 8;
    }

    @Override
    public Swarm.BeeType getBeeType() {
        return Swarm.BeeType.QUEEN;
    }

    @Override
    public boolean killsAllOthersWhenDie() {
        return true;
    }
}

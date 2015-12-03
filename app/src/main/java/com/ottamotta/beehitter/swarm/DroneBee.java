package com.ottamotta.beehitter.swarm;

class DroneBee extends Bee {

    public DroneBee(int id) {
        super(id);
    }

    @Override
    int getLifespan() {
        return 50;
    }

    @Override
    int getHitpoints() {
        return 12;
    }

    @Override
    public Swarm.BeeType getBeeType() {
        return Swarm.BeeType.DRONE;
    }
}

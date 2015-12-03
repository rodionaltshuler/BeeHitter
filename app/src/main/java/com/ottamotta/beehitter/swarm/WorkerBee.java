package com.ottamotta.beehitter.swarm;

class WorkerBee extends Bee {

    public WorkerBee(int id) {
        super(id);
    }

    @Override
    int getLifespan() {
        return 75;
    }

    @Override
    int getHitpoints() {
        return 10;
    }

    @Override
    public Swarm.BeeType getBeeType() {
        return Swarm.BeeType.WORKER;
    }

}

package com.ottamotta.beehitter.swarm;

public class SwarmPresenter implements SwarmContract.UserActionListener {

    private Swarm swarm;
    private SwarmContract.View view;

    public SwarmPresenter(Swarm swarm, SwarmContract.View view) {
        this.swarm = swarm;
        this.view = view;
    }

    @Override
    public void hitRandomBee() {
        try {
            Bee bee = swarm.getRandomBee();
            swarm.hit(bee);
            view.updateBee(bee);
            if (bee.isDead()) {
                view.removeBee(bee);
            }
            if (swarm.allBeesDead()) {
                swarm = Swarm.reCreate();
                view.showBees(swarm.getAllBees());
            }
        } catch (SwarmDeadException e) {
            swarm = Swarm.reCreate();
            view.showBees(swarm.getAllBees());
        }
    }

    @Override
    public void showSwarmToUser() {
        view.showBees(swarm.getAllBees());
    }
}

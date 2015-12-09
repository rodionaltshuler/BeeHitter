package com.ottamotta.beehitter.swarm;

import java.util.List;

public interface SwarmContract {

    interface UserActionListener {
        Bee hitRandomBee();
        void showSwarmToUser();
    }

    interface View {

        void showBees(List<Bee> bees);
        void removeBee(Bee bee);
        void updateBee(Bee bee);
    }
}

package com.ottamotta.beehitter.swarm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Swarm {

    public static final int QUEENS_COUNT = 1;
    public static final int WORKERS_COUNT = 5;
    public static final int DRONES_COUNT = 8;

    private static Swarm instance;

    List<Bee> bees = new ArrayList<>();

    public enum BeeType {
        QUEEN(QUEENS_COUNT) {
            @Override
            Bee createBee(int id) {
                return new QueenBee(id);
            }
        }, WORKER(WORKERS_COUNT) {
            @Override
            Bee createBee(int id) {
                return new WorkerBee(id);
            }
        }, DRONE(DRONES_COUNT) {
            @Override
            Bee createBee(int id) {
                return new DroneBee(id);
            }
        };

        private int count;

        BeeType(int count) {
            this.count = count;
        }

        abstract Bee createBee(int id);

    }

    public static Swarm reCreate() {
        instance = null;
        return getInstance();
    }

    public static synchronized Swarm getInstance() {
        if (null == instance) {
            instance = new Swarm();
        }
        return instance;
    }

    Swarm() {
        createBees();
    }

    List<Bee> createBees() {
        int counter = 0;
        for (BeeType beeType : BeeType.values()) {
            for (int i = 0; i < beeType.count; i++) {
                bees.add(beeType.createBee(counter++));
            }
        }
        return bees;
    }

    Bee getQueen() {
        for (Bee bee : bees) {
            if (bee instanceof QueenBee) return bee;
        }
        return null;
    }

    public Bee getRandomBee() {
        Random r = new Random();
        int beeIndex = r.nextInt(bees.size());
        return  bees.get(beeIndex);
    }

    public Bee hit(Bee bee) throws SwarmDeadException {
        bee.hit();
        if (bee.isDead()) {
            if (bee.killsAllOthersWhenDie()) {
                bees.clear();
                throw new SwarmDeadException();
            } else {
                bees.remove(bee);
            }
        }
        return bee;
    }

    public boolean allBeesDead() {
        return bees.isEmpty();
    }

    public List<Bee> getAllBees() {
        return Collections.unmodifiableList(bees);
    }

}

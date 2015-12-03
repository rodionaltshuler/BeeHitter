package com.ottamotta.beehitter.swarm;

import junit.framework.TestCase;

import java.util.List;

public class SwarmTest extends TestCase {

    private Swarm swarm;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        swarm = new Swarm();
    }

    public void testCreateSwarmRightBeesCount() throws Exception{
        int expectedSwarmSize = getExpectedSwarmSize();
        assertTrue("Expected new swarm size is " + expectedSwarmSize + ", but " + swarm.getAllBees() + " swarm size is created",
                swarm.getAllBees().size() == expectedSwarmSize);
    }

    private int getExpectedSwarmSize() {
        return Swarm.QUEENS_COUNT + Swarm.WORKERS_COUNT + Swarm.DRONES_COUNT;
    }


    public void testCreateSwarmRightQueensCount() throws Exception {
        int expectedQueensCount = Swarm.QUEENS_COUNT;
        int actualCount = 0;
        List<Bee> bees = swarm.getAllBees();
        for (Bee bee : bees) {
            if (bee instanceof QueenBee) {
                actualCount++;
            }
        }
        assertTrue("Expected Queens count is " + expectedQueensCount + ", but " + actualCount + " are present",
                expectedQueensCount == actualCount);
    }

    public void testHitRandomBeeConsumesHitpoints() throws Exception {
        Bee bee = swarm.getRandomBee();
        int hitpointsBeforHit = bee.getHitPointsRemaining();
        Bee hittedBee = swarm.hit(bee);
        assertTrue(hitpointsBeforHit == hittedBee.getHitPointsRemaining() + hittedBee.getHitpoints());
    }

    public void testSwarmDiesWhenQueenDies() {
        Bee queen = swarm.getQueen();
        boolean swarmDeadExceptionThrown = false;
        while (!queen.isDead()) {
            try {
                swarm.hit(queen);
            } catch (SwarmDeadException e) {
                swarmDeadExceptionThrown = true;
            }
        }
        assertTrue("Queen is dead, but SwarmDeadException wasn't thrown", swarmDeadExceptionThrown);
    }

    public void testSwarmDoesntDieIfNonQueenDies() {
        Bee bee = null;
        boolean swarmDeadExceptionThrown = false;
        while (bee == null){
            Bee randomBee = swarm.getRandomBee();
            if (!(randomBee instanceof QueenBee)) {
                bee = randomBee;
            }
        }
        while (!bee.isDead()) {
            try {
                swarm.hit(bee);
            } catch (SwarmDeadException e) {
                swarmDeadExceptionThrown = true;
            }
        }
        assertTrue("Non-Queen bee is dead, but SwarmDeadException thrown", !swarmDeadExceptionThrown);
    }

    public void testSwarmIsEmptyWhenQueenDies() {
        Bee queen = swarm.getQueen();
        while (!queen.isDead()) {
            try {
                swarm.hit(queen);
            } catch (SwarmDeadException e) {
                //ignore
            }
        }
        assertTrue("Queen is dead, but swarm isn't empty", swarm.allBeesDead());
    }

    public void testReCreateCreatesSwarmOfProperSize() throws Exception {
        Swarm swarm = Swarm.reCreate();
        int expectedSwarmSize = getExpectedSwarmSize();
        assertTrue("Expected re-created swarm size is " + expectedSwarmSize + ", but " + swarm.getAllBees() + " swarm size is created",
                swarm.getAllBees().size() == expectedSwarmSize);
    }

}

package com.ottamotta.beehitter.swarm;

import android.support.annotation.NonNull;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import java.util.List;

public class SwarmTest extends TestCase {

    private Swarm swarm;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        swarm = new Swarm();
    }

    @SmallTest
    public void testInstantinateSwarmRightBeesCount() throws Exception{
        int expectedSwarmSize = getExpectedSwarmSize();
        assertTrue("Expected new swarm size is " + expectedSwarmSize + ", but " + swarm.getAllBees() + " swarm size is created",
                swarm.getAllBees().size() == expectedSwarmSize);
    }

    @SmallTest
    public void testInstantinateSwarmRightQueensCount() throws Exception {
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

    @SmallTest
    public void testHitRandomBeeConsumesHitpoints() throws Exception {
        Bee bee = swarm.getRandomBee();
        int hitpointsBeforHit = bee.getHitPointsRemaining();
        Bee hittedBee = swarm.hit(bee);
        assertTrue(hitpointsBeforHit == hittedBee.getHitPointsRemaining() + hittedBee.getHitpoints());
    }

    @SmallTest
    public void testHitSwarmDiesWhenQueenDies() {
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

    @SmallTest
    public void testHitSwarmDoesntDieIfNonQueenDies()  throws Exception {
        Bee bee = getRandomNonQueenBee();
        boolean swarmDeadExceptionThrown = false;
        while (!bee.isDead()) {
            try {
                swarm.hit(bee);
            } catch (SwarmDeadException e) {
                swarmDeadExceptionThrown = true;
            }
        }
        assertTrue("Non-Queen bee is dead, but SwarmDeadException thrown", !swarmDeadExceptionThrown);
    }

    @SmallTest
    public void testHitSwarmSizeDecreasedWhenBeeDies() throws Exception {
        int initialSwarmSize = swarm.getAllBees().size();
        Bee bee = getRandomNonQueenBee();
        while (!bee.isDead()) {
            try {
                swarm.hit(bee);
            } catch (SwarmDeadException e) {
                //ignore
            }
        }
        int swarmSizeAfterBeeIsDead = swarm.getAllBees().size();
        int diff = initialSwarmSize - swarmSizeAfterBeeIsDead;
        assertTrue("After one non-Queen bee is dead, swarm size should be decreased by 1, but descread by " + diff, diff == 1);
    }


    @SmallTest
    public void testHitSwarmIsEmptyWhenQueenDies()  throws Exception {
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

    @SmallTest
    public void testReCreateCreatesSwarmOfProperSize() throws Exception {
        Swarm swarm = Swarm.reCreate();
        int expectedSwarmSize = getExpectedSwarmSize();
        assertTrue("Expected re-created swarm size is " + expectedSwarmSize + ", but " + swarm.getAllBees() + " swarm size is created",
                swarm.getAllBees().size() == expectedSwarmSize);
    }

    private int getExpectedSwarmSize() {
        return Swarm.QUEENS_COUNT + Swarm.WORKERS_COUNT + Swarm.DRONES_COUNT;
    }

    private @NonNull Bee getRandomNonQueenBee() {
        Bee randomBee = swarm.getRandomBee();
        while (randomBee instanceof QueenBee){
            randomBee = swarm.getRandomBee();
        }
        return randomBee;
    }


}

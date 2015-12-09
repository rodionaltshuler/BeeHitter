package com.ottamotta.beehitter.swarm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SwarmPresenterTest {

    private SwarmPresenter swarmPresenter;

    @Mock
    private SwarmContract.View swarmView;

    @Mock
    private Swarm swarm;

    @Mock
    private Bee bee;

    @org.junit.Before
    public void setUp() throws Exception {
        when(swarm.getRandomBee()).thenReturn(bee);
        swarmPresenter = new SwarmPresenter(swarm, swarmView);
    }

    @org.junit.Test
    public void testHitRandomBee_beeUpdated() throws Exception {
        Bee bee = swarmPresenter.hitRandomBee();
        verify(swarmView).updateBee(bee);
    }

    @Test
    public void testShowSwarmToUser_showAllBees() throws Exception {
        swarmPresenter.showSwarmToUser();
        verify(swarmView).showBees(swarm.getAllBees());
    }

}

package com.ottamotta.beehitter.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ottamotta.beehitter.R;
import com.ottamotta.beehitter.swarm.Bee;
import com.ottamotta.beehitter.swarm.Swarm;
import com.ottamotta.beehitter.swarm.SwarmDeadException;

public class BeeHitActivity extends AppCompatActivity {

    public static final int COLUMNS = 3;

    private RecyclerView recyclerView;

    private BeesAdapter adapter;

    private Swarm swarm = Swarm.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bee_hit);
        recyclerView = (RecyclerView) findViewById(android.R.id.list);
        View hitButton = findViewById(R.id.hit_button);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        initAdapter();
        hitButton.setOnClickListener(hitClickListener);
    }

    private void initAdapter() {
        adapter = new BeesAdapter(swarm.getAllBees());
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener hitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Bee bee = swarm.getRandomBee();
                swarm.hit(bee);
                adapter.updateBee(bee);
                if (bee.isDead()) {
                    adapter.remove(bee);
                }
                if (swarm.allBeesDead()) {
                    restart();
                }
            } catch (SwarmDeadException e) {
                e.printStackTrace();
                restart();
            }
        }
    };

    private void restart() {
        swarm = Swarm.reCreate();
        initAdapter();
    }


}

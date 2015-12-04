package com.ottamotta.beehitter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ottamotta.beehitter.R;
import com.ottamotta.beehitter.swarm.Bee;
import com.ottamotta.beehitter.swarm.SwarmPresenter;
import com.ottamotta.beehitter.swarm.Swarm;
import com.ottamotta.beehitter.swarm.SwarmContract;

import java.util.List;

public class BeeHitActivity extends AppCompatActivity implements SwarmContract.View {

    public static final int COLUMNS = 3;

    private RecyclerView recyclerView;

    private BeesAdapter adapter;

    private SwarmContract.UserActionListener userActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bee_hit);

        recyclerView = (RecyclerView) findViewById(android.R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS));

        android.view.View hitButton = findViewById(R.id.hit_button);
        hitButton.setOnClickListener(hitClickListener);

        userActionListener = new SwarmPresenter(Swarm.getInstance(), this);
        userActionListener.showSwarmToUser();
    }

    private void initAdapter(List<Bee> bees) {
        adapter = new BeesAdapter(bees);
        recyclerView.setAdapter(adapter);
    }

    private android.view.View.OnClickListener hitClickListener = new android.view.View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            userActionListener.hitRandomBee();
        }
    };

    @Override
    public void showBees(List<Bee> bees) {
        initAdapter(bees);
    }

    @Override
    public void removeBee(Bee bee) {
        adapter.remove(bee);
    }

    @Override
    public void updateBee(Bee bee) {
        adapter.updateBee(bee);
    }
}

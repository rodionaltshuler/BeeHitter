package com.ottamotta.beehitter.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ottamotta.beehitter.R;
import com.ottamotta.beehitter.swarm.Bee;

public class BeeViewHolder extends RecyclerView.ViewHolder {

    private TextView beeType;

    private TextView hitPointsLeft;

    public BeeViewHolder(View itemView) {
        super(itemView);
        beeType = (TextView) itemView.findViewById(R.id.bee_type);
        hitPointsLeft = (TextView) itemView.findViewById(R.id.hitpoints);
    }

    public void fill(Bee bee) {
        beeType.setText(bee.getBeeType().toString());
        hitPointsLeft.setText(String.valueOf(bee.getHitPointsRemaining()));
    }

}

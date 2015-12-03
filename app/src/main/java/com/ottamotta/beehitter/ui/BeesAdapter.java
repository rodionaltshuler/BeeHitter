package com.ottamotta.beehitter.ui;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ottamotta.beehitter.R;
import com.ottamotta.beehitter.swarm.Bee;

import java.util.List;

public class BeesAdapter extends RecyclerView.Adapter<BeeViewHolder> {

    private SortedListAdapterCallback<Bee> callback = new SortedListAdapterCallback<Bee>(this) {
        @Override
        public int compare(Bee o1, Bee o2) {
            if (o1.hashCode() > o2.hashCode()) return 1;
            return -1;
        }

        @Override
        public boolean areContentsTheSame(Bee oldItem, Bee newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(Bee item1, Bee item2) {
            return item1.equals(item2);
        }
    };

    private SortedList<Bee> bees = new SortedList<>(Bee.class, callback);

    public BeesAdapter(List<Bee> newBees) {
        this.bees.addAll(newBees);
    }

    @Override
    public BeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new BeeViewHolder(layoutInflater.inflate(R.layout.bee_viewholder, parent, false));
    }

    @Override
    public void onBindViewHolder(BeeViewHolder holder, int position) {
        holder.fill(bees.get(position));
    }

    @Override
    public int getItemCount() {
        return bees.size();
    }

    public void remove(Bee bee) {
        bees.removeItemAt(getBeeIndex(bee));
    }

    public void updateBee(Bee bee) {
        bees.updateItemAt(getBeeIndex(bee), bee);
    }

    private int getBeeIndex(Bee bee) {
        for (int i = 0; i < bees.size(); i++) {
            if (bees.get(i).equals(bee)) {
                return i;
            }
        }
        return -1;
    }
}

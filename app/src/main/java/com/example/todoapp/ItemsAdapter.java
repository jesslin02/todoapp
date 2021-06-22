package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/* responsible for taking data from the model (list of strings)
and displaying it in a row in the recycler view */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    /* items in our to-do list */
    List<String> items;
    /* reference to main activity class for deletion functionality */
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a ViewHolder and return it
        return new ViewHolder(todoView);
    }

    /* binds data to a particular View Holder */
    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        // get item at position
        String item = items.get(position);
        //bind item into specified view holder
        holder.bind(item);
    }

    /* tells recycler view how many items are in the list */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /* container to provide easy access to views that represent each row of the list */
    class ViewHolder extends RecyclerView.ViewHolder {
        /* text view inside the view that we passed into the constructor */
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        /* updates the view inside of the View Holder with the item */
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // notifies longClickListener (inside MainActivity) the position of
                    // the item that needs to be deleted
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return false;
                }
            });
        }
    }

}

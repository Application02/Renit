package com.sau.rentalclothsapp.Owner.adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sau.rentalclothsapp.R;

import java.util.ArrayList;

public class RecyclerInboxAdapter extends RecyclerView.Adapter {
    ArrayList personNames;
    Context context;
    public RecyclerInboxAdapter(Context context/*, ArrayList personNames*/) {
        this.context = context;
        this.personNames = personNames;
    }
    @Override
    public RecyclerInboxAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // set the data in items
        //   holder.name.setText(personNames.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                //  Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // return personNames.size();
        return 10;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            //  name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}


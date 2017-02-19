package com.example.mohitkumar.medolx;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.AbsSavedState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohitkumar on 19/02/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.RecyclerViewHolder> {

    Context context;
    ArrayList<CardData> arrayList = new ArrayList<CardData>();

    public CardAdapter(Context context,ArrayList arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,context,arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        CardData cardData = arrayList.get(position);
        holder.name.setText(cardData.getMedname());
        holder.dosage.setText(cardData.getMeddose());
        holder.expiredate.setText(cardData.getExpirydate());
        holder.address.setText(cardData.getAddress());
        holder.quantity.setText(cardData.getQuantity());
        final String usrname = cardData.getUsrname().toString();

        holder.floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,OrderActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Medicine",holder.name.getText().toString());
                intent.putExtra("Desc",holder.dosage.getText().toString());
                intent.putExtra("expdate",holder.expiredate.getText().toString());
                intent.putExtra("quantity",holder.quantity.getText().toString());
                intent.putExtra("price",holder.address.getText().toString());
                intent.putExtra("usrname",usrname);
                //intent.putExtra("Medicine",holder.name.getText().toString());
                //intent.putExtra("Medicine",holder.name.getText().toString());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ArrayList<CardData> arrayList = new ArrayList<CardData>();
        TextView name,dosage,expiredate,address,quantity;
        FloatingActionButton floatingbutton;
        public RecyclerViewHolder(View itemView,Context context,ArrayList<CardData> arrayList) {
            super(itemView);
            address = (TextView) itemView.findViewById(R.id.card_addr);
            name = (TextView) itemView.findViewById(R.id.card_med_name);
            dosage = (TextView) itemView.findViewById(R.id.card_dosage);
            expiredate = (TextView) itemView.findViewById(R.id.card_expire);
            quantity = (TextView)itemView.findViewById(R.id.card_quant);
            floatingbutton = (FloatingActionButton)itemView.findViewById(R.id.float_button);
            this.context = context;
            this.arrayList = arrayList;
        }
    }
}

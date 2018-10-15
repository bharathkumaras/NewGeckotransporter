package com.example.sandy.newgeckotransporter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sandy on 01-Oct-18.
 */

public class MessgeRecyclerview extends RecyclerView.Adapter<MessgeRecyclerview.MyViewHolder> {

    List<MessageModelclass> message;
    private LayoutInflater inflater;


    public MessgeRecyclerview(List<MessageModelclass> messages,Context context) {
        this.message = messages;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.message_view,parent,false);
        MyViewHolder view1= new MyViewHolder(view);
        return view1;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MessageModelclass mm = message.get(position);
        holder.message.setText(mm.getMessage());

    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView message;
        public MyViewHolder(View itemView) {
            super(itemView);
            message = (TextView)itemView.findViewById(R.id.messagetext);

        }
    }


}

package com.example.sandy.newgeckotransporter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sandy on 04-Oct-18.
 */

public class Retrofitreccyclerview extends RecyclerView.Adapter<Retrofitreccyclerview.Myviewholder> {

    List<EmployeeData> data;

    Retrofitreccyclerview(List<EmployeeData> data)
    {
        this.data = data;
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.retrofitviewholder, parent, false);
        return new Myviewholder(view);
    }


    @Override
    public void onBindViewHolder(Myviewholder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.email.setText(data.get(position).getEmail());
        holder.phone.setText(data.get(position).getPhone());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Myviewholder extends RecyclerView.ViewHolder {
        TextView name,email,phone;
        public Myviewholder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txt_employee_name);
            email =(TextView)itemView.findViewById(R.id.txt_employee_email);
            phone = (TextView)itemView.findViewById(R.id.txt_employee_phone);
        }
    }
}

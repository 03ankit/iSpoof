package com.coretechies.ispoof;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.holder>implements Filterable{

    ArrayList<ContactModel> data;
    ArrayList<ContactModel> backup;

    public contactAdapter(ArrayList<ContactModel> data)
    {
        this.data = data;
        backup=new ArrayList<>(data);
    }


    @NonNull
    @Override
    public contactAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_single_row, parent, false);
        return new contactAdapter.holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.contactTextView.setText(data.get(position).getHeader());
        holder.contactTextView2.setText(data.get(position).getDesc());
        holder.contactTextView3.setText(data.get(position).getIdContact());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

   @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence keyword)
        {
            ArrayList<ContactModel> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(ContactModel obj : backup)
                {
                    if(obj.getHeader().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }

            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return results;
        }

        @Override  // main UI thread
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            data.clear();
            data.addAll((ArrayList<ContactModel>)results.values);
            notifyDataSetChanged();
        }
    };


    class holder extends RecyclerView.ViewHolder {
        ImageView contactImg;
        TextView contactTextView, contactTextView2, contactTextView3;
        View view;

        public holder(@NonNull View itemView) {
            super(itemView);
            contactImg = itemView.findViewById(R.id.contactimageView4);
            contactTextView =  itemView.findViewById(R.id.contactTextView1);
            contactTextView2 = itemView.findViewById(R.id.contactTextView2);
            contactTextView3 = itemView.findViewById(R.id.contactid);
            view =itemView.findViewById(R.id.view8);
        }
    }
}

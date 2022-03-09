package com.coretechies.ispoof;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;


public class Call_Log_Adapter extends RecyclerView.Adapter<Call_Log_Adapter.holder>implements Filterable {

    ArrayList<Call_log_Model> dataholder;

    private DBHandler dbHandler;
    public Call_Log_Adapter(ArrayList<Call_log_Model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.call_log_singlerow, parent, false);
        return new holder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.callTextView.setText(dataholder.get(position).getName());
        holder.callTextView2.setText(dataholder.get(position).getNumber());
        holder.callTextView3.setText(getFormattedDate(dataholder.get(position).getDate()) );

        holder.callLogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler = new DBHandler(view.getContext());
                Long Ldate = System.currentTimeMillis();
                String phonenumber =dataholder.get(position).getNumber();
                SharedPreferences preferences = view.getContext().getSharedPreferences("iSpoof", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.putString("setNumber", phonenumber);
                edit.apply();
                Intent callIntent =new Intent(view.getContext(),endCallDisplay.class);
                view.getContext().startActivity(callIntent);
                dbHandler.addNewCourse(phonenumber, phonenumber, Ldate);
                //Toast.makeText(view.getContext(), "clicked on " +dataholder.get(position).getNumber(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    class holder extends RecyclerView.ViewHolder {
        ImageView callImg1,callImg2;
        TextView callTextView, callTextView2, callTextView3;
        LinearLayout callLogLayout ;
        View view;

        public holder(@NonNull View itemView) {
            super(itemView);

            callImg1 = itemView.findViewById(R.id.single_row_callImage);
            callImg2 = itemView.findViewById(R.id.single_row_callImage);
            callTextView = itemView.findViewById(R.id.single_row_contact);
            callTextView2 = itemView.findViewById(R.id.single_row_number);
            callTextView3 = itemView.findViewById(R.id.single_row_date);
            callLogLayout =itemView.findViewById(R.id.callLayout);
            view = itemView.findViewById(R.id.view8);
            callTextView3.setSelected(true);


        }
    }

    public String getFormattedDate(Long dataholder) {
        Calendar smsTime = Calendar.getInstance();
        smsTime.setTimeInMillis(dataholder);

        Calendar now = Calendar.getInstance();

        final String timeFormatString = "h:mm aa";
        final String dateTimeFormatString = "MMM dd, yyyy, hh:mm a,EEE";
        final long HOURS = 60 * 60 * 60;
        if (now.get(Calendar.DATE) == smsTime.get(Calendar.DATE)) {
            return "Today " + DateFormat.format(timeFormatString, smsTime);
        } else if (now.get(Calendar.DATE) - smsTime.get(Calendar.DATE) == 1) {
            return "Yesterday " + DateFormat.format(timeFormatString, smsTime);
        } else if (now.get(Calendar.YEAR) == smsTime.get(Calendar.YEAR)) {
            return DateFormat.format(dateTimeFormatString, smsTime).toString();
        } else {
            return DateFormat.format("MMM dd yyyy, h:mm aa", smsTime).toString();
        }
    }
}

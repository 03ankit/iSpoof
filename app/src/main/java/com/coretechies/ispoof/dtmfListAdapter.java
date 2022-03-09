package com.coretechies.ispoof;

import static android.content.Context.CLIPBOARD_SERVICE;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.coretechies.ispoof.ui.DBHandlerDtmf;

import java.util.ArrayList;
import java.util.Calendar;

public class dtmfListAdapter extends RecyclerView.Adapter<dtmfListAdapter.holder>implements Filterable {



    ArrayList<DtmfModel> dataholder;

    public dtmfListAdapter(ArrayList<DtmfModel> dataholder)
    {
        this.dataholder = dataholder;
    }
    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dtmf_signal_list, parent, false);
        return new dtmfListAdapter.holder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull dtmfListAdapter.holder holder, int position) {
        holder.DtmfView.setText(dataholder.get(position).getNumber());
        holder.DtmfView2.setText(getFormattedDate(dataholder.get(position).getDate()) );
        holder.DtmfNumber.setText(String.valueOf(position+1));
        holder.DtmfDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandlerDtmf db = new DBHandlerDtmf(v.getContext());
                db.deleteDtmf(dataholder.get(position).getID());
                dataholder.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    public class holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView DtmfView, DtmfView2,DtmfNumber;
        ImageView DtmfCopy,DtmfDelete;
        View view;

        public holder(@NonNull View itemView) {
            super(itemView);
            DtmfNumber = itemView.findViewById(R.id.textViewNumber);
            DtmfView =  itemView.findViewById(R.id.Dtmfview1);
            DtmfView2 = itemView.findViewById(R.id.DtmfView2);
            DtmfCopy = itemView.findViewById(R.id.Dtmfcopy);
            DtmfDelete = itemView.findViewById(R.id.Dtmfdelete);
            DtmfCopy.setOnClickListener(this);
            view =itemView.findViewById(R.id.view8);
        }
        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onClick(View v) {
           if (DtmfCopy.isPressed())
            {
                DtmfCopy.setColorFilter(Color.parseColor("#3673c5"));
                ClipboardManager clipboard = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
                    clipboard = (ClipboardManager)v.getContext().getSystemService(CLIPBOARD_SERVICE);
                }
                ClipData myClip = ClipData.newPlainText("text", DtmfView.getText().toString());
                clipboard.setPrimaryClip(myClip);
                Log.e("clipbord ","clipbord copy");
            }
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

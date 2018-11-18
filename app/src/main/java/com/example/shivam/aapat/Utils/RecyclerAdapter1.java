package com.example.shivam.aapat.Utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivam.aapat.Models.Hospital;
import com.example.shivam.aapat.R;

import java.util.ArrayList;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ListItemHolder> {

    Context context;
    ArrayList<Hospital> hospitals;

    public RecyclerAdapter1(Context context, ArrayList<Hospital> hospitals) {
        this.context = context;
        this.hospitals = hospitals;
    }

    @Override
    public ListItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.list_item_hospital, parent, false);
        return new ListItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemHolder holder, int position) {
        Hospital hospital = hospitals.get(position);
        holder.hname.setText(hospital.getName());
        holder.address.setText(hospital.getAddress());
        holder.distance.setText(hospital.getDistance());
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:900..."));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    class ListItemHolder extends RecyclerView.ViewHolder{
        View mainView;
        TextView hname;
        TextView address;
        TextView distance;
        public ListItemHolder(View itemView) {
            super(itemView);
            mainView = itemView;
            hname = (TextView) itemView.findViewById(R.id.hName);
            address = (TextView) itemView.findViewById(R.id.address);
            distance = (TextView) itemView.findViewById(R.id.distance);
        }
    }
}

package com.example.shivam.aapat.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shivam.aapat.R;


/**
 * Created by ishaandhamija on 26/09/17.
 */

public class RecyclerHolder extends RecyclerView.ViewHolder {

    ImageView ivProblemImg;
    TextView tvProblem;
    View view;

    public RecyclerHolder(View itemView, final Context ctx) {
        super(itemView);

        this.ivProblemImg = (ImageView) itemView.findViewById(R.id.ivProblemImg);
        this.tvProblem = (TextView) itemView.findViewById(R.id.tvProblem);
        this.view = itemView;
    }
}

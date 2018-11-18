package com.example.shivam.aapat.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shivam.aapat.Models.Problem;
import com.example.shivam.aapat.R;

import java.util.ArrayList;


/**
 * Created by ishaandhamija on 26/09/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {

    Context ctx;
    ArrayList<Problem> problemsList;

    public RecyclerAdapter(Context context, ArrayList<Problem> list) {
        this.ctx = context;
        this.problemsList = list;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.problem_layout, parent, false);

        return new RecyclerHolder(itemView, ctx);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        final Problem problem = problemsList.get(position);
        holder.ivProblemImg.setImageResource(problem.getImg());
        holder.tvProblem.setText(problem.getName());
    }

    @Override
    public int getItemCount() {
        return problemsList.size();
    }

}
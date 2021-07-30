package com.projects.BookSearch;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DownAdapter extends RecyclerView.Adapter<DownAdapter.ViewHolder> {
    private ArrayList<DownDetails> down;
    private Context context;

    public DownAdapter(ArrayList<DownDetails> down, Context context){
        this.context = context;
        this.down = down;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookdl_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        // bind the data

        DownDetails dDet = down.get(position);
        holder.title.setText(dDet.getTitle());
        holder.auth.setText(dDet.getAuth());
        holder.pub.setText(dDet.getPub());
        holder.year.setText(dDet.getYear());
        //Picasso.get().load(dDet.getUrl()).into(holder.url);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(context, DownDetails.class);
                j.putExtra("title", dDet.getTitle());
                j.putExtra("author", dDet.getAuth());
                j.putExtra("publisher", dDet.getPub());
                j.putExtra("year", dDet.getYear());
                j.putExtra("url", dDet.getUrl());
                context.startActivity(j);
            }
        });
    }


    @Override
    public int getItemCount() {
        return down.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView title, auth, pub, year;
        public ImageButton url;
        public RecyclerView recyclerView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.idTVBookTitle);
            auth = itemView.findViewById(R.id.idTVauth);
            pub = itemView.findViewById(R.id.idTVpublisher);
            year = itemView.findViewById(R.id.idTVyear);
            url = itemView.findViewById(R.id.idDown);

            url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DownDetails dDet = down.get(getAdapterPosition());
                    String t_url = dDet.getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(t_url));
                    context.startActivity(intent);

                }
            });



        }
    }
}
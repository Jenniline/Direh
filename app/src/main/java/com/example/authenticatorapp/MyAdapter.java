package com.example.authenticatorapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String data1[], data2[];
    int images[];
   int contact[];

    Context context;


    public MyAdapter(Context ct, String[] s1, String[] s2, int[] img,  int[] cont){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
        contact = cont;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
       View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage1.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1,myText2;
        ImageView myImage1,myImage2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.report_title_txt);
            myText1 = itemView.findViewById(R.id.description_txt);
            myImage1 = itemView.findViewById(R.id.contact_image);
            myImage2 = itemView.findViewById(R.id.contact_button);
        }
    }
}

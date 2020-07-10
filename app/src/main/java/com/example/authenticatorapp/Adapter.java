package com.example.authenticatorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder>  {



    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder , int position) {

        String resource = modelClassList.get(position).getImageIcon();
        String title = modelClassList.get(position).getTitle();
        String description = modelClassList.get(position).getDescription();
        viewholder.setData(resource,title,description);
    }

    @Override
    public int getItemCount() {
        return  modelClassList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView description;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title    = itemView.findViewById(R.id.textTitle);
            description = itemView.findViewById(R.id.textDescription);


        }

        private void setData (String imageResource,String titleText, String textdescription){

            imageView.setImageResource(imageResource);
            title.setText(titleText);
            description.setText(textdescription);

        }

    }



}

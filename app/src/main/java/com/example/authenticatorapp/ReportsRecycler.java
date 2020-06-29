package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ReportsRecycler extends AppCompatActivity { String s1[],s2[];

    RecyclerView recyclerView;


    int images[] = {R.drawable.missingbooks,R.drawable.missingpen,R.drawable.missingschoolbag,R.drawable.missingpencil,R.drawable.missingcolorpencil,R.drawable.missingeraser,R.drawable.missingruler,R.drawable.missingphone,R.drawable.missingkeys};
    int contact[] = {R.drawable.contact_button};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_recycler);

        recyclerView  = findViewById(R.id.recyclerView);

        s1 = getResources() .getStringArray(R.array.REPORTS);
        s2 = getResources() .getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter( this, s1,s2,images,contact);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
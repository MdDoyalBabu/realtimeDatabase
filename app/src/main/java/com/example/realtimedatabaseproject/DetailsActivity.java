package com.example.realtimedatabaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<Model> modelList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        databaseReference= FirebaseDatabase.getInstance().getReference("information");

        listView=findViewById(R.id.listview_id);


        modelList=new ArrayList<>();

        customAdapter=new CustomAdapter(DetailsActivity.this,modelList);

    }

    @Override
    protected void onStart() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                modelList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                        Model model=dataSnapshot.getValue(Model.class);

                        modelList.add(model);

                }

                listView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(DetailsActivity.this, "Erro"+error, Toast.LENGTH_SHORT).show();
            }
        });


        super.onStart();
    }
}
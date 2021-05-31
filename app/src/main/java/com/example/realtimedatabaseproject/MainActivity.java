package com.example.realtimedatabaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText nameEdittext,ageEdittext;
    Button saveDatabButton,loadDataButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("information");

        nameEdittext=findViewById(R.id.edittext_name_id);
        ageEdittext=findViewById(R.id.edittext_age_id);

        saveDatabButton=findViewById(R.id.save_btn_id);
        loadDataButton=findViewById(R.id.load_btn_id);



        saveDatabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });

        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveData() {

        String name=nameEdittext.getText().toString();
        String age=ageEdittext.getText().toString();

        String key=databaseReference.push().getKey();

       Model model=new Model(name,age);

       databaseReference.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if (task.isSuccessful())
               {
                   Toast.makeText(MainActivity.this, "Data insert is succeful", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(MainActivity.this, "Not Successful"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   Log.d("Error",task.getException().getMessage());
               }
           }
       });


    }
}
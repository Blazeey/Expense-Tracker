package com.example.aksha.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class expense4 extends AppCompatActivity {
    String income,policy,fixeddeposit,gift,vacation,cable1,electricity,grocery1,newspaper,water1,accessories,clothesA,clothesK;
    String cosmetics,food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense4);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final DatabaseReference ref4 = ref3.child("household");
        Button button = (Button) findViewById(R.id.Button41);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense4.this, expense3.class);
                startActivity(intent);
            }
        });
        Button savebtn = (Button) findViewById(R.id.housesavebtn);
        final EditText grocery,cable,elec,water,news;
        grocery = (EditText) findViewById(R.id.grocery);
        cable = (EditText) findViewById(R.id.cable);
        elec = (EditText) findViewById(R.id.electricity);
        water = (EditText) findViewById(R.id.water);
        news = (EditText) findViewById(R.id.newspaper);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(grocery.getText().toString().trim().equals("")){

                    Toast.makeText(expense4.this,"grocery field empty!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (cable.getText().toString().trim().equals("")) {

                        Toast.makeText(expense4.this,"cable field empty!!!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(elec.getText().toString().trim().equals("")){

                            Toast.makeText(expense4.this,"electricity field empty!!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(water.getText().toString().trim().equals("")){

                                Toast.makeText(expense4.this,"water field empty!!!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(news.getText().toString().trim().equals("")){

                                    Toast.makeText(expense4.this,"newspaper field empty!!!",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    ref4.child("grocery").setValue(grocery.getText().toString());
                                    ref4.child("cable").setValue(cable.getText().toString());
                                    ref4.child("electricity").setValue(elec.getText().toString());
                                    ref4.child("water").setValue(water.getText().toString());
                                    ref4.child("newspaper").setValue(news.getText().toString());

                                    Toast.makeText(expense4.this,"saved to database!",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    income = dataSnapshot.child("income").getValue().toString();
                    fixeddeposit = dataSnapshot.child("bank").child("fixed deposit").getValue().toString();
                    policy = dataSnapshot.child("bank").child("policy").getValue().toString();
                    gift = dataSnapshot.child("extras").child("gift").getValue().toString();
                    vacation = dataSnapshot.child("extras").child("vacation").getValue().toString();
                    cable1 = dataSnapshot.child("household").child("cable").getValue().toString();
                    electricity = dataSnapshot.child("household").child("electricity").getValue().toString();
                    grocery1 = dataSnapshot.child("household").child("grocery").getValue().toString();
                    newspaper = dataSnapshot.child("household").child("newspaper").getValue().toString();
                    water1 = dataSnapshot.child("household").child("water").getValue().toString();
                    accessories = dataSnapshot.child("shopping").child("accessories").getValue().toString();
                    clothesA = dataSnapshot.child("shopping").child("clothesA").getValue().toString();
                    clothesK = dataSnapshot.child("shopping").child("clothesK").getValue().toString();
                    cosmetics = dataSnapshot.child("shopping").child("cosmetics").getValue().toString();
                    food = dataSnapshot.child("shopping").child("food").getValue().toString();
                    grocery.setText(grocery1);
                    cable.setText(cable1);
                    elec.setText(electricity);
                    news.setText(newspaper);
                    water.setText(water1);
                }catch (Exception e){
                    //Toast.makeText(expense3.this,"database error",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

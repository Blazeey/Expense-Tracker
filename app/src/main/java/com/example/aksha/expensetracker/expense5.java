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

public class expense5 extends AppCompatActivity {
    String income,policy,fixeddeposit,gift,vacation,cable,electricity,grocery,newspaper,water,accessories,clothesA1,clothesK1;
    String cosmetics1,food1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense5);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final DatabaseReference ref4 = ref3.child("shopping");
        Button button = (Button) findViewById(R.id.Button51);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense5.this, expense3.class);
                startActivity(intent);
            }
        });

        Button savebtn = (Button) findViewById(R.id.shoppingsave);
        final EditText clothesA,clothesK,cosmetics,acs,food;
        clothesA = (EditText) findViewById(R.id.clothesA);
        clothesK = (EditText) findViewById(R.id.clothesK);
        cosmetics = (EditText) findViewById(R.id.cosmetics);
        acs = (EditText) findViewById(R.id.accessories);
        food = (EditText) findViewById(R.id.food);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clothesA.getText().toString().trim().equals("")){

                    Toast.makeText(expense5.this,"clothes (adult) field empty!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (clothesK.getText().toString().trim().equals("")) {

                        Toast.makeText(expense5.this,"clothes (Kid) field empty!!!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(cosmetics.getText().toString().trim().equals("")){

                            Toast.makeText(expense5.this,"cosmetics field empty!!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(acs.getText().toString().trim().equals("")){

                                Toast.makeText(expense5.this,"accessories field empty!!!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if(food.getText().toString().trim().equals("")){

                                    Toast.makeText(expense5.this,"food field empty!!!",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    ref4.child("clothesA").setValue(clothesA.getText().toString());
                                    ref4.child("clothesK").setValue(clothesK.getText().toString());
                                    ref4.child("cosmetics").setValue(cosmetics.getText().toString());
                                    ref4.child("accessories").setValue(acs.getText().toString());
                                    ref4.child("food").setValue(food.getText().toString());

                                    Toast.makeText(expense5.this,"saved to database!",Toast.LENGTH_SHORT).show();
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
                    cable = dataSnapshot.child("household").child("cable").getValue().toString();
                    electricity = dataSnapshot.child("household").child("electricity").getValue().toString();
                    grocery = dataSnapshot.child("household").child("grocery").getValue().toString();
                    newspaper = dataSnapshot.child("household").child("newspaper").getValue().toString();
                    water = dataSnapshot.child("household").child("water").getValue().toString();
                    accessories = dataSnapshot.child("shopping").child("accessories").getValue().toString();
                    clothesA1 = dataSnapshot.child("shopping").child("clothesA").getValue().toString();
                    clothesK1 = dataSnapshot.child("shopping").child("clothesK").getValue().toString();
                    cosmetics1= dataSnapshot.child("shopping").child("cosmetics").getValue().toString();
                    food1 = dataSnapshot.child("shopping").child("food").getValue().toString();
                    acs.setText(accessories);
                    clothesA.setText(clothesA1);
                    clothesK.setText(clothesK1);
                    cosmetics.setText(cosmetics1);
                    food.setText(food1);

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

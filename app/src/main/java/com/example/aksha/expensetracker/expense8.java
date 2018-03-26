package com.example.aksha.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class expense8 extends AppCompatActivity {
    String income,policy,fixeddeposit,gift,vacation,cable,electricity,grocery,newspaper,water,accessories,clothesA,clothesK;
    String cosmetics,food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense8);
        Button button = (Button) findViewById(R.id.Button81);
        final TextView balance = (TextView) findViewById(R.id.balance);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense8.this, expense3.class);
                startActivity(intent);
            }
        });

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    System.out.println("testing 123");
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
                    clothesA = dataSnapshot.child("shopping").child("clothesA").getValue().toString();
                    clothesK = dataSnapshot.child("shopping").child("clothesK").getValue().toString();
                    cosmetics = dataSnapshot.child("shopping").child("cosmetics").getValue().toString();
                    food = dataSnapshot.child("shopping").child("food").getValue().toString();
                    System.out.println("all values"+income+fixeddeposit+policy+gift);
                    int income1,fixeddeposit1,policy1,gift1,vacation1,cable1,electricity1,grocery1,newspaper1,water1,accessories1,clothesA1,clothesK1,cosmetics1,food1;
                    income1 = Integer.parseInt(income);
                    fixeddeposit1 = Integer.parseInt(fixeddeposit);
                    policy1 = Integer.parseInt(policy);
                    gift1 = Integer.parseInt(gift);
                    vacation1 = Integer.parseInt(vacation);
                    cable1 = Integer.parseInt(cable);
                    electricity1 = Integer.parseInt(electricity);
                    grocery1 = Integer.parseInt(grocery);
                    newspaper1 = Integer.parseInt(newspaper);
                    water1 = Integer.parseInt(water);
                    accessories1 = Integer.parseInt(accessories);
                    clothesA1 = Integer.parseInt(clothesA);
                    clothesK1 = Integer.parseInt(clothesK);
                    cosmetics1 = Integer.parseInt(cosmetics);
                    food1 = Integer.parseInt(food);
                    int bal = income1 - (fixeddeposit1+policy1+gift1+vacation1+cable1+electricity1+grocery1+newspaper1+water1+accessories1+clothesA1+clothesK1+cosmetics1+food1);
                    balance.setText(String.valueOf(bal));
                    System.out.println("balance testing"+bal);
                }catch (Exception e){
                    e.printStackTrace();
                    //Toast.makeText(expense3.this,"database error",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

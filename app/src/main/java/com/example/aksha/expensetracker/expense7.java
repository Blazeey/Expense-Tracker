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

public class expense7 extends AppCompatActivity {
    String income,policy,fixeddeposit,gift1,vacation1,cable,electricity,grocery,newspaper,water,accessories,clothesA,clothesK;
    String cosmetics,food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense7);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final DatabaseReference ref4 = ref3.child("extras");
        Button button = (Button) findViewById(R.id.Button71);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense7.this, expense3.class);
                startActivity(intent);
            }
        });
        Button savebtn = (Button) findViewById(R.id.extrasavebtn);
        final EditText vacation,gift;
        vacation = (EditText) findViewById(R.id.vacation);
        gift = (EditText) findViewById(R.id.gift);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vacation.getText().toString().trim().equals("")){

                    Toast.makeText(expense7.this,"vacation field empty!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (gift.getText().toString().trim().equals("")) {

                        Toast.makeText(expense7.this,"gift field empty!!!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ref4.child("vacation").setValue(vacation.getText().toString());
                        ref4.child("gift").setValue(gift.getText().toString());
                        Toast.makeText(expense7.this,"saved to database",Toast.LENGTH_SHORT).show();

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
                    gift1 = dataSnapshot.child("extras").child("gift").getValue().toString();
                    vacation1 = dataSnapshot.child("extras").child("vacation").getValue().toString();
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
                    gift.setText(gift1);
                    vacation.setText(vacation1);
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

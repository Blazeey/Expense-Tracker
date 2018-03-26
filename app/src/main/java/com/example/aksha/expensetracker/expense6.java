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

public class expense6 extends AppCompatActivity {
    String income,policy1,fixeddeposit,gift,vacation,cable,electricity,grocery,newspaper,water,accessories,clothesA,clothesK;
    String cosmetics,food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense6);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final DatabaseReference ref4 = ref3.child("bank");
        Button button = (Button) findViewById(R.id.Button61);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense6.this, expense3.class);
                startActivity(intent);
            }
        });
        Button savebtn = (Button) findViewById(R.id.savebank);
        final EditText fd,policy;
        fd = (EditText) findViewById(R.id.fixeddeposit);
        policy = (EditText) findViewById(R.id.policy);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fd.getText().toString().trim().equals("")){

                    Toast.makeText(expense6.this,"fixed deposit field empty!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (policy.getText().toString().trim().equals("")) {

                        Toast.makeText(expense6.this,"policy field empty!!!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ref4.child("fixed deposit").setValue(fd.getText().toString());
                        ref4.child("policy").setValue(policy.getText().toString());
                        Toast.makeText(expense6.this,"saved to database",Toast.LENGTH_SHORT).show();

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
                    policy1 = dataSnapshot.child("bank").child("policy").getValue().toString();
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
                    fd.setText(fixeddeposit);
                    policy.setText(policy1);

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

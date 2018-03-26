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

public class expense3 extends AppCompatActivity {

    String income,policy,fixeddeposit,gift,vacation,cable,electricity,grocery,newspaper,water,accessories,clothesA,clothesK;
    String cosmetics,food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense3);


        Button saveBn = (Button) findViewById(R.id.savebutton);
        final EditText incomeinput = (EditText) findViewById(R.id.income);/*;
        ref3.child("income").setValue("1234567");
        ref3.child("bank").child("fixed deposit").setValue("");
        ref3.child("bank").child("policy").setValue("");
        ref3.child("extras").child("gift").setValue("");
        ref3.child("extras").child("vacation").setValue("");
        ref3.child("household").child("cable").setValue("");
        ref3.child("household").child("electricity").setValue("");
        ref3.child("household").child("grocery").setValue("");
        ref3.child("household").child("newspaper").setValue("");
        ref3.child("household").child("water").setValue("");
        ref3.child("shopping").child("accessories").setValue("");
        ref3.child("shopping").child("clothesA").setValue("");
        ref3.child("shopping").child("clothesK").setValue("");
        ref3.child("shopping").child("cosmetics").setValue("");
        ref3.child("shopping").child("food").setValue("");*/
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference ref = db.getReference();
        final DatabaseReference ref2 = ref.child("users");
        final DatabaseReference ref3 = ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

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
                    clothesA = dataSnapshot.child("shopping").child("clothesA").getValue().toString();
                    clothesK = dataSnapshot.child("shopping").child("clothesK").getValue().toString();
                    cosmetics = dataSnapshot.child("shopping").child("cosmetics").getValue().toString();
                    food = dataSnapshot.child("shopping").child("food").getValue().toString();
                    incomeinput.setText(income);
                }catch (Exception e){
                    //Toast.makeText(expense3.this,"database error",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button reportbtn = (Button) findViewById(R.id.reportbtn);
        reportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(expense3.this,expense9.class));
            }
        });
        saveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(incomeinput.getText().toString().trim().equals("")){
                    Toast.makeText(expense3.this,"income field empty!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    ref2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("income").setValue(incomeinput.getText().toString());
                }
            }
        });


        Button button = (Button) findViewById(R.id.next_button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense3.this, expense4.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.next_button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense3.this, expense5.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.next_button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense3.this, expense6.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.Button61);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense3.this, expense.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.next_button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense3.this, expense7.class);
                startActivity(intent);
            }
        });
        button = (Button) findViewById(R.id.next_button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(expense3.this, expense8.class);
                startActivity(intent);
            }
        });

        Button rembtn = (Button) findViewById(R.id.remainderbtn);
        rembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(expense3.this,expense10.class));
            }
        });



    }
}

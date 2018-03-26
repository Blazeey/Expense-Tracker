package com.example.aksha.expensetracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class expense1 extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense1);
        auth = FirebaseAuth.getInstance();
        Button signup = (Button) findViewById(R.id.Button11);
        final EditText uname = (EditText) findViewById(R.id.e11);
        final EditText pass  = (EditText) findViewById(R.id.pass);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uname.getText().toString().trim().equals("")){
                    Toast.makeText(expense1.this,"email field is empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.getText().toString().trim().equals("")){
                        Toast.makeText(expense1.this,"password field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        auth.createUserWithEmailAndPassword(uname.getText().toString().trim(), pass.getText().toString().trim())
                                .addOnCompleteListener(expense1.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                       // Toast.makeText(expense1.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                        if (!task.isSuccessful()) {
                                            Toast.makeText(expense1.this, "Authentication failed." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(expense1.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(expense1.this, expense3.class));
                                            finish();
                                        }
                                    }
                                });}
                }
            }
        });


    }
}

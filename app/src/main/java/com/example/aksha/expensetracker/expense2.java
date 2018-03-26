package com.example.aksha.expensetracker;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class expense2 extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense2);



        Button button = (Button) findViewById(R.id.Button2);
        final EditText uname = (EditText) findViewById(R.id.email);
        final EditText pass = (EditText) findViewById(R.id.e1);
        auth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(uname.getText().toString().trim().equals("")){
                    Toast.makeText(expense2.this,"email field is empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.getText().toString().trim().equals("")){
                        Toast.makeText(expense2.this,"password field is empty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        auth.signInWithEmailAndPassword(uname.getText().toString(), pass.getText().toString())
                                .addOnCompleteListener(expense2.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            // there was an error
                                            System.out.println(task.getException());
                                            Toast.makeText(expense2.this,"username or password is incorrect", Toast.LENGTH_LONG).show();

                                        } else {
                                            Intent intent = new Intent(expense2.this, expense3.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                    }
                }
                /*Intent intent = new Intent(expense1.this, expense2.class);
                startActivity(intent);*/

            }
        });
    }
}

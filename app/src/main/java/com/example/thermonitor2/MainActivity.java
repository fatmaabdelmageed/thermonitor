package com.example.thermonitor2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
     EditText email,password;
     Button btnLogin;
     Button register;
    TextView signup;
    ImageView lock;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            FirebaseApp.initializeApp(this);


       // FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance(); //error



//        firebaseAuth = FirebaseAuth.getInstance(); //error
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        lock= findViewById(R.id.lock);
        signup = findViewById(R.id.registertext);
        register = findViewById(R.id.registerbutton);
        btnLogin = (Button) findViewById(R.id.buttonLogin);


//       btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent;
//                intent = new Intent(MainActivity.this, ListActivity.class);
//                startActivity(intent);
//            }
//        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(I);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = email.getText().toString();
                String userPaswd = password.getText().toString();

                if (userEmail.isEmpty()) {
                    email.setError("Provide your Email first!");
                    email.requestFocus();
                } else
                {
                    if (userPaswd.isEmpty()) {
                        password.setError("Enter Password!");
                        password.requestFocus();
                    } else  {

                   // Toast.makeText(MainActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
              // else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                   firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                           if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, ListActivity.class));
                            }
                        }
                    });
                }
//                    else {
//                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
            }
        }}
        );
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(I);
                } else {
                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}







package com.saiful2682070.notesappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mlogin,mgotosignup;
    private EditText mloginemail,mloginpassword;
    private TextView mforgotpassword;
    ProgressBar mprogressbarofmainactivity;


    private FirebaseAuth firebaseAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mlogin = findViewById(R.id.login);
        mgotosignup = findViewById(R.id.gotosignup);
        mloginemail = findViewById(R.id.loginemail);
        mloginpassword = findViewById(R.id.loginpassword);
        mforgotpassword = findViewById(R.id.forgotpassword);
        mprogressbarofmainactivity = findViewById(R.id.progressbarofmainactivity);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();


        if (firebaseUser != null){

            Intent myIntent = new Intent(MainActivity.this,notesActivity.class);
            startActivity(myIntent);
            finish();
        }


        mforgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this,forgotpassword.class);
                startActivity(myIntent);
            }
        });

        mgotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, signup.class);
                startActivity(myIntent);
            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = mloginemail.getText().toString().trim();
                String password = mloginpassword.getText().toString().trim();
                if (mail.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "eMail and Password field is required", Toast.LENGTH_SHORT).show();
                }
                else {
                    //login to next


                    mprogressbarofmainactivity.setVisibility(View.VISIBLE);

                    firebaseAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               checkmailverification();
                           }
                           else {
                               Toast.makeText(getApplicationContext(),"Your account doesn't exists",Toast.LENGTH_SHORT).show();
                               mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
                           }
                        }
                    });

                }
            }
        });







    }

    private void checkmailverification(){
       FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
       if (firebaseUser.isEmailVerified()==true){
           Toast.makeText(getApplicationContext(),"Logged IN",Toast.LENGTH_SHORT).show();
           Intent myIntent = new Intent(MainActivity.this,notesActivity.class);
           startActivity(myIntent);
       }
       else{
           mprogressbarofmainactivity.setVisibility(View.INVISIBLE);
           Toast.makeText(getApplicationContext(),"Verify eMail first",Toast.LENGTH_SHORT).show();
           firebaseAuth.signOut();
       }
    }



}
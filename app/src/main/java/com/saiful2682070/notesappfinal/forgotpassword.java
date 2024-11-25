package com.saiful2682070.notesappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private TextView mgobacktologin;
    private Button mrecoverybutton;
    private EditText mforgotmail;

    FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgotpassword);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mgobacktologin = findViewById(R.id.gobacktologin);
        mrecoverybutton = findViewById(R.id.recoverybutton);
        mforgotmail = findViewById(R.id.forgotmail);


        firebaseAuth = FirebaseAuth.getInstance();


        mgobacktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(forgotpassword.this ,MainActivity.class);
                startActivity(myintent);
            }
        });

        mrecoverybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = mforgotmail.getText().toString().trim();
                if (mail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your registered eMail first",Toast.LENGTH_SHORT).show();
                }

                else {

                    //we send code on mail

                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(getApplicationContext(),"Mail sent,You can reset your password using mail",Toast.LENGTH_SHORT).show();
                               finish();
                               Intent myIntent = new Intent(forgotpassword.this,MainActivity.class);
                               startActivity(myIntent);
                           }
                           else {
                               Toast.makeText(getApplicationContext(),"eMail wrong or account doesn't exists",Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
            }
        });





    }
}
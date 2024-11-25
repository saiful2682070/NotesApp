package com.saiful2682070.notesappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class notedetails extends AppCompatActivity {

    private TextView mcontentofnotedetails, mtitleofnotedetails;
    FloatingActionButton mgotoeditnote;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notedetails);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mcontentofnotedetails = findViewById(R.id.contentofnotedetails);
        mtitleofnotedetails = findViewById(R.id.titleofnotedetails);
        mgotoeditnote = findViewById(R.id.gotoeditnote);

        Toolbar toolbar = findViewById(R.id.toolbarofnotedetails);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent data = getIntent();



        mgotoeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),editnoteactivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("noteId",data.getStringExtra("noteId"));
                view.getContext().startActivity(intent);

                //Toast.makeText(getApplicationContext(),"This is working",Toast.LENGTH_SHORT).show();


            }
        });

        mcontentofnotedetails.setText(data.getStringExtra("content") != null ? data.getStringExtra("content") : "No Content");
        mtitleofnotedetails.setText(data.getStringExtra("title") != null ? data.getStringExtra("title") : "Untitled");


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Check if the up button was pressed
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
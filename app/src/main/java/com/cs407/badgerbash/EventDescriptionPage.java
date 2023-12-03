package com.cs407.badgerbash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventDescriptionPage extends AppCompatActivity {

    private DatabaseReference rootRef;
    EventInfo event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description_page);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        double lat=intent.getDoubleExtra("lat",0);
        double lon=intent.getDoubleExtra("lon",0);
        String createdBy=intent.getStringExtra("createdBy");
        String brief=intent.getStringExtra("brief");
        String full=intent.getStringExtra("full");
        event=new EventInfo(name,lat,lon,createdBy,brief,full);

        Button homeButton=findViewById(R.id.HomeButton);
        setUpHomeButton(homeButton);
        Button signUpButton=findViewById(R.id.SignUpButton);
        setUpSignUpButton(signUpButton);
        Button locationButton=findViewById(R.id.LocationButton);
        setUpLocationButton(locationButton);

        TextView description=findViewById(R.id.fullDescrip);
        description.setText(full);
    }

    private void setUpLocationButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDescriptionPage.this, Map.class);
                startActivity(intent);
            }
        });
    }
    private void setUpHomeButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDescriptionPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpSignUpButton(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDescriptionPage.this, MainActivity.class);
                addToSignedUp();
                startActivity(intent);
            }
        });
    }

    private void addToSignedUp(){
        rootRef= FirebaseDatabase.getInstance().getReference().child("Users");
        rootRef.child(Username).child("SignedUp").setValue(event);
    }
}
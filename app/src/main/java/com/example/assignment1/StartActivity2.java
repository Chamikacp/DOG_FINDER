package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity2 extends AppCompatActivity {

    private boolean isTimerSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);

        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from Start activity 2
    }

    //To launch the Identity the Dog Activity
    public void launchIdTheDog(View view) {
        Intent intent = new Intent(this, IdentifyTheDog.class);
        intent.putExtra("switchValue", isTimerSwitchOn);     //To pass the boolean value to the other activity
        startActivity(intent);                                      //To start the IdentifyTheDog activity
    }
}

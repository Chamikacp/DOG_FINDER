package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MenuActivity extends AppCompatActivity {

    Switch switchTimer;
    Button option1;
    Button option2;
    boolean isTimerSwitchOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        switchTimer = findViewById(R.id.switch_timer);
        option1 = findViewById(R.id.button_id_the_breed);
        option2 = findViewById(R.id.button_id_the_dog);

        //To change the boolean value when the switch button clicked
        switchTimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isTimerSwitchOn = true;
                }else{
                    isTimerSwitchOn = false;
                }
            }
        });
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchStart1(view);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchStart2(view);
            }
        });
    }

    //To launch the Start  Activity 1
    public void launchStart1(View view) {
        Intent intent = new Intent(this, StartActivity1.class);
        intent.putExtra("switchValue", isTimerSwitchOn);   //To pass the boolean value to the other activity
        startActivity(intent);                                   //To start the StartActivity1
    }

    //To launch the Start Activity 2
    public void launchStart2(View view) {
        Intent intent = new Intent(this, StartActivity2.class);
        intent.putExtra("switchValue", isTimerSwitchOn);     //To pass the boolean value to the other activity
        startActivity(intent);                                      //To start the StartActivity2
    }

    //To launch the Search the Breed Activity
    public void launchSearchBreed(View view) {
        Intent intent = new Intent(this, SearchTheBreed.class);
        startActivity(intent);                                      //To start the SearchTheBreed activity
    }

    //To exit the game
    public void ExitTheGame(View view) {
        finish();
        System.exit(0);
    }

}

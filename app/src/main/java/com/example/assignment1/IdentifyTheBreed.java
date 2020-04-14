package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class IdentifyTheBreed extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String spinnerLabel;
    private String breedName;
    private Button button;
    private TextView correctBreed;
    private TextView result;
    private TextView timer;
    private CountDownTimer countDownTimer;
    private boolean isTimerSwitchOn = false;

    DogBreeds dogBreeds = new DogBreeds();
    public ArrayList<String> dogBreedArray = new ArrayList<>();
    private int numberOfDogImagesOfOneBreed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_breed);

        this.dogBreedArray = dogBreeds.getDogBreadArray();
        this.numberOfDogImagesOfOneBreed = dogBreeds.getNumberOfDogImagesOfOneBreed();

        timer = findViewById(R.id.textView_timer);
        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from main activity

        if (isTimerSwitchOn){    //Check weather switch is on or not
            switchTimer();
            start();
        }else {
            start();
        }

    }

    //To start the countdown in the text view
    public void switchTimer(){
        countDownTimer = new CountDownTimer(11000,1000) {  //Create the countDownTimer
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timer.setText(seconds);
            }
            @Override
            public void onFinish() {   //method for when the timer finished
                submitAuto();
            }
        };
        countDownTimer.start();       //To Start the countdown
    }

    //Method for the when the activity start
    public void start(){

        ImageView image_breed = findViewById(R.id.imageView_dog);

        breedName = getRandomDogBreed();
        String imageName = breedName + "_" + getRandomInteger(1, numberOfDogImagesOfOneBreed);  //To get the random imageName

        //To assign the random image to the image view
        image_breed.setImageDrawable( getResources().getDrawable(getImageID(imageName, "drawable", getApplicationContext())));

        // Create the spinner.
        Spinner spinner = findViewById(R.id.spinner_breed);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.breeds_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        //Set the button label to Submit
        button = findViewById(R.id.button_submit);
        button.setText("Submit");
    }


    //To check that the image name is match with the images in the drawable folder
    protected final static int getImageID(final String imageName, final String imageType, final Context context) {
        final int ImageID = context.getResources().getIdentifier(imageName, imageType, context.getApplicationInfo().packageName);
        if (ImageID == 0) {
           throw new IllegalArgumentException("No image string found with name " + imageName);
        }else{
            return ImageID;
        }
    }

    //To get the selected label from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //To get a random breed name from the breed array
    public String getRandomDogBreed(){
        return dogBreedArray.get(getRandomInteger(0,(dogBreedArray.size()-1)));
    }

    //To get a random integer between the number of images from one breed
    public static int getRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    //Method to auto submit when time over
    public void submitAuto(){

            button.setText("Next");
            if (spinnerLabel.equals(breedName) ){
                String answer = "CORRECT !";
                result = findViewById(R.id.my_answer);
                result.setTextColor(Color.GREEN);
                result.setText(answer);

                correctBreed = findViewById(R.id.correct_answer);
                correctBreed.setText(breedName.toUpperCase());

            }else {
                String answer = "WRONG !";
                result = findViewById(R.id.my_answer);
                result.setTextColor(Color.RED);
                result.setText(answer);

                correctBreed = findViewById(R.id.correct_answer);
                correctBreed.setText(breedName.toUpperCase());
            }

    }

    //To check the validation of the users answer by clicking the submit button
    public void onClickButton(View view) {
        if (button.getText().equals("Submit")){
            button.setText("Next");
            if (spinnerLabel.equals(breedName) ){
                String answer = "CORRECT !";
                result = findViewById(R.id.my_answer);
                result.setTextColor(Color.GREEN);
                result.setText(answer);

                correctBreed = findViewById(R.id.correct_answer);
                correctBreed.setText(breedName.toUpperCase());

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }

            }else {
                String answer = "WRONG !";
                result = findViewById(R.id.my_answer);
                result.setTextColor(Color.RED);
                result.setText(answer);

                correctBreed = findViewById(R.id.correct_answer);
                correctBreed.setText(breedName.toUpperCase());

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }
        }else {        //When the button label is set to Next
            start();
            if (isTimerSwitchOn){
                countDownTimer.cancel();   //To reset the timer
                switchTimer();
            }

            result = findViewById(R.id.my_answer);
            result.setText("");

            correctBreed = findViewById(R.id.correct_answer);
            correctBreed.setText("");
        }
    }
}

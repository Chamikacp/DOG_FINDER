package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class IdentifyTheDog extends AppCompatActivity {

    private String imageName1;
    private String imageName2;
    private String imageName3;
    private TextView result;
    private String guessingBreed;
    private int imageCount;
    private TextView timer;
    private CountDownTimer countDownTimer;
    private boolean isTimerSwitchOn = false;

    public ArrayList<String> dogBreedArray = new ArrayList<>();
    private int numberOfDogImagesOfOneBreed;
    DogBreeds dogBreeds = new DogBreeds();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_dog);

        this.dogBreedArray = dogBreeds.getDogBreadArray();
        this.numberOfDogImagesOfOneBreed = dogBreeds.getNumberOfDogImagesOfOneBreed();

        timer = findViewById(R.id.textView_timer);
        Intent intent = getIntent();
        isTimerSwitchOn = intent.getExtras().getBoolean("switchValue");  //To get the boolean value from main activity

        if (isTimerSwitchOn){   //Check weather switch is on or not
            switchTimer();
            start();
        }else {
            start();
        }
    }

    //To start the countdown in the text view
    public void switchTimer(){
        countDownTimer = new CountDownTimer(11000,1000) {   //Create the countDownTimer
            @Override
            public void onTick(long millisUntilFinished) {
                String seconds = millisUntilFinished / 1000 + "";
                timer.setText(seconds);
            }
            @Override
            public void onFinish() {   //method for when the timer finished
                imageCount++;
                result = findViewById(R.id.textView_correct_answer);
                String notAnswered = "NOT ANSWERED";
                result.setTextColor(Color.RED);
                result.setText(notAnswered);

                highlightedImage();
            }
        };
        countDownTimer.start();    //To Start the countdown
    }

    //Method for the when the activity start
    public void start(){

        imageCount = 0;  //To get the count of the clicked images

        ImageView image_breed1 = findViewById(R.id.dog_image1);
        ImageView image_breed2 = findViewById(R.id.dog_image2);
        ImageView image_breed3 = findViewById(R.id.dog_image3);

        ArrayList<String> randomImagesChosen;
        randomImagesChosen = getRandomBreedArray();  //To add the chosen random 3 images array

        guessingBreed = randomImagesChosen.get(getRandomInteger(0,2));   //To get the image name of guessing breed

        //To assign chosen images to the image views
        imageName1 = randomImagesChosen.get(0) + "_" + getRandomInteger(1,numberOfDogImagesOfOneBreed);
        image_breed1.setImageDrawable( getResources().getDrawable(getImageID(imageName1, "drawable", getApplicationContext())));

        imageName2 = randomImagesChosen.get(1) + "_" +getRandomInteger(1,numberOfDogImagesOfOneBreed);
        image_breed2.setImageDrawable( getResources().getDrawable(getImageID(imageName2, "drawable", getApplicationContext())));

        imageName3 = randomImagesChosen.get(2) + "_" + getRandomInteger(1,numberOfDogImagesOfOneBreed);
        image_breed3.setImageDrawable( getResources().getDrawable(getImageID(imageName3, "drawable", getApplicationContext())));

        result = findViewById(R.id.textView_dogBreed);
        result.setText(guessingBreed.toUpperCase());  //To show the guessing breed
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

    //To get a random breed name from the breed array
    public String getRandomDogBreed(){
        return dogBreedArray.get(getRandomInteger(0,(dogBreedArray.size()-1)));
    }

    //To get a random integer between the number of images from one breed
    public static int getRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    //To get 3 unique random images
    public ArrayList<String> getRandomBreedArray(){

        ArrayList<String> checkingBreedArray = new ArrayList<>();

        do{
            String checkingDogBread = getRandomDogBreed();
            if(checkingBreedArray.indexOf(checkingDogBread) == -1){
                checkingBreedArray.add(checkingDogBread);
            }
        }while(checkingBreedArray.size()!=3);
        return checkingBreedArray;
    }

    //Method for the Next button
    public void OnClick(View view){
        if (imageCount != 0){                           //To refresh the activity
            start();
            if(isTimerSwitchOn){
                countDownTimer.cancel();                 //To reset the timer
                switchTimer();
            }
            result = findViewById(R.id.textView_correct_answer);
            result.setText("");

            ImageView image_breed1 = findViewById(R.id.dog_image1);
            ImageView image_breed2 = findViewById(R.id.dog_image2);
            ImageView image_breed3 = findViewById(R.id.dog_image3);

            image_breed1.setBackgroundColor(Color.WHITE);
            image_breed2.setBackgroundColor(Color.WHITE);
            image_breed3.setBackgroundColor(Color.WHITE);


        }else {               //if the user not select an image
            Toast.makeText(getApplicationContext(), "Touch an Image !",
                    Toast.LENGTH_SHORT).show();
        }

    }

    //To highlight the correct dog image
    public void highlightedImage(){
        String breed1 = imageName1.split("_")[0];     //to get the breed name 1 form the image name
        String breed2 = imageName2.split("_")[0];     //to get the breed name 2 form the image name
        String breed3 = imageName3.split("_")[0];     //to get the breed name 3 form the image name

        ImageView image_breed1 = findViewById(R.id.dog_image1);
        ImageView image_breed2 = findViewById(R.id.dog_image2);
        ImageView image_breed3 = findViewById(R.id.dog_image3);

        if (guessingBreed.equals(breed1)){
            image_breed1.setBackgroundColor(Color.BLUE);
        }else if (guessingBreed.equals(breed2)){
            image_breed2.setBackgroundColor(Color.BLUE);
        } else if(guessingBreed.equals(breed3)){
            image_breed3.setBackgroundColor(Color.BLUE);
        }
    }

    //Method for the clicked image1
    public void clickableImage1(View view){
        imageCount++;

        String breed1 = imageName1.split("_")[0];     //to get the breed name 1 form the image name

        if (imageCount == 1){
            if (guessingBreed.equals(breed1)){
                String answer = "CORRECT !";
                result = findViewById(R.id.textView_correct_answer);
                result.setTextColor(Color.GREEN);
                result.setText(answer.toUpperCase());

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }else {
                String answer = "WRONG !";
                result = findViewById(R.id.textView_correct_answer);
                result.setTextColor(Color.RED);
                result.setText(answer.toUpperCase());
                highlightedImage();                       //To show the correct image

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }
        }else {                                         //With this user can't do multiple attempts
            Toast.makeText(getApplicationContext(), "Press the Next button to Continue",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Method for the clicked image2
    public void clickableImage2(View view){
        imageCount++;
        String breed2 = imageName2.split("_")[0];     //to get the breed name 2 form the image name

        if (imageCount == 1){
            if (guessingBreed.equals(breed2)){
                String answer = "CORRECT !";
                result = findViewById(R.id.textView_correct_answer);
                result.setTextColor(Color.GREEN);
                result.setText(answer.toUpperCase());

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }else {
                String answer = "WRONG !";
                result = findViewById(R.id.textView_correct_answer);
                result.setTextColor(Color.RED);
                result.setText(answer.toUpperCase());
                highlightedImage();                          //To show the correct image

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }
        }else {                                             //With this user can't do multiple attempts
            Toast.makeText(getApplicationContext(), "Press the Next button to Continue",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Method for the clicked image2
    public void clickableImage3(View view){
        imageCount++;
        String breed3 = imageName3.split("_")[0];     //to get the breed name 3 form the image name

        if (imageCount == 1){
            if (guessingBreed.equals(breed3)){
                String answer = "CORRECT !";
                result = findViewById(R.id.textView_correct_answer);
                result.setTextColor(Color.GREEN);
                result.setText(answer.toUpperCase());

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }else {
                String answer = "WRONG !";
                result = findViewById(R.id.textView_correct_answer);
                result.setTextColor(Color.RED);
                result.setText(answer.toUpperCase());
                highlightedImage();                          //To show the correct image

                if (isTimerSwitchOn) {
                    countDownTimer.cancel();                   //To pause the timer
                }
            }
        }else {                                               //With this user can't do multiple attempts
            Toast.makeText(getApplicationContext(), "Press the Next button to Continue",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SearchTheBreed extends AppCompatActivity {

    boolean iteration = false;
    private int numberOfDogImagesOfOneBreed;

    DogBreeds dogBreeds = new DogBreeds();
    public ArrayList<String> dogBreedArray = new ArrayList<>();

    boolean ifSubmitted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_the_breed);
    }

    //To start the slide show by clicking the submit button
    public void start(View view){

        EditText breed = findViewById(R.id.editText_breed);
        String userEnteredBread = breed.getText().toString();  //To get the user entered breed from the edit text

        this.dogBreedArray = dogBreeds.getDogBreadArray();
        this.numberOfDogImagesOfOneBreed = dogBreeds.getNumberOfDogImagesOfOneBreed();

        if (dogBreedArray.indexOf(userEnteredBread)!=-1){      //To check the user enters breed available

            ifSubmitted = true;
            Toast.makeText(getApplicationContext(), "Started",
                    Toast.LENGTH_SHORT).show();

            final ImageView image_breed1 = findViewById(R.id.imageView_dog);
            iteration = true;

            String imageName = userEnteredBread + "_" +  getRandomInteger(1, numberOfDogImagesOfOneBreed);
            image_breed1.setImageDrawable( getResources().getDrawable(getImageID(imageName, "drawable", getApplicationContext())));
            delay(5,image_breed1,userEnteredBread);     //To start the slide show with time period
        }else {
            Toast.makeText(getApplicationContext(), "Entered breed is not available.Enter again",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //To get a random integer between the number of images from one breed
    public static int getRandomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


    //To get the all the images of the user entered breed
    public void delay(int secs, final ImageView image_breed, final String breed){
        this.numberOfDogImagesOfOneBreed = dogBreeds.getNumberOfDogImagesOfOneBreed();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String imageName = breed + "_" + getRandomInteger(1, numberOfDogImagesOfOneBreed);;
                if(iteration){
                    image_breed.setImageDrawable( getResources().getDrawable(getImageID(imageName, "drawable", getApplicationContext())));
                    image_breed.animate();
                    delay(5,image_breed,breed);
                }
            }
        }, secs * 1000);   // afterDelay will be executed after (secs*1000) milliseconds.
    }

    //To stop the slide show by clicking stop button
    public void stop(View view){
        if (ifSubmitted){
            Toast.makeText(getApplicationContext(), "Stopped",
                    Toast.LENGTH_SHORT).show();
            iteration = false;
            ifSubmitted = false;
        } else {
            Toast.makeText(getApplicationContext(), "Not submitted a breed",
                    Toast.LENGTH_SHORT).show();
        }
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
}

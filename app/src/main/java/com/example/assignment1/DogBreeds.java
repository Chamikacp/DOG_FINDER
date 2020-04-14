package com.example.assignment1;

import java.util.ArrayList;

public class DogBreeds {

    //ArrayList for the dog breeds
    public ArrayList<String> dogBreedArray = new ArrayList<>();

    //Number of images for a one breed
    private int numberOfDogImagesOfOneBreed = 15;

    //To add the breeds names to the ArrayList
    public DogBreeds(){
        this.dogBreedArray.add("airedale");
        this.dogBreedArray.add("basset");
        this.dogBreedArray.add("beagle");
        this.dogBreedArray.add("boxer");
        this.dogBreedArray.add("briard");
        this.dogBreedArray.add("cardigan");
        this.dogBreedArray.add("collie");
        this.dogBreedArray.add("doberman");
        this.dogBreedArray.add("husky");
        this.dogBreedArray.add("komondor");
        this.dogBreedArray.add("labrador");
        this.dogBreedArray.add("leonberg");
        this.dogBreedArray.add("pomeranian");
        this.dogBreedArray.add("pug");
        this.dogBreedArray.add("rottweiler");
    }

    public ArrayList<String> getDogBreadArray() {
        return dogBreedArray;
    }

    public int getNumberOfDogImagesOfOneBreed() {
        return numberOfDogImagesOfOneBreed;
    }
}

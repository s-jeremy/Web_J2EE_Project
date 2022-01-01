package com.example.web_j2ee_project.panier;

public class Helper {
    public static String getSomeWords(String description){
        String[] words = description.split(" ");

        if(words.length>15){
            String resultat = "";

            for(int i=0;i<10;i++){
                resultat = resultat + words[i] + " ";
            }
            return(resultat + "...");
        }
        else{
            return(description + "...");
        }
    }
}

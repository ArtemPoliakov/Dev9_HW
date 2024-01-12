package com.homework.artem_http_things;

import com.homework.exceptions.ImageNotFoundException;

import java.util.Scanner;

public class HttpImageStatusCli {
    public void askStatus(){
        System.out.println("Enter HTTP status code:");
        int code;
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                code = Integer.parseInt(scanner.nextLine());
                break;
            } catch(NumberFormatException nfe){
                System.out.println("Enter a valid number please!");
            }
        }
        try{
            new HttpStatusImageDownloader().downloadStatusImage(code);
            System.out.println("A cool cat image has been downloaded!");
        } catch(ImageNotFoundException e){
            System.out.printf("There is no image for HTTP status %d :(", code);
        }
    }
}

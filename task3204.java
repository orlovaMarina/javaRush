package com.javarush.task.task32.task3204;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Генератор паролей (8 символов, 1 цифра, 1 заглавная, 1 строчная)
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        boolean passwordNeeded = true;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String numbers = "0123456789";
        String lettersBig = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lettersSmall = "abcdefghijklmnopqrstuvwxyz";
        String str = null;


        while(passwordNeeded){
            List<Integer> indexes = getIndexes();
            StringBuilder sb = new StringBuilder();


            for(int i = 0; i < indexes.size(); i++){
                if(indexes.get(i) < numbers.length()) sb.append(numbers.charAt(indexes.get(i)));
                else {
                    if(i % 2 == 0) sb.append(lettersBig.charAt(indexes.get(i)));
                    else sb.append(lettersSmall.charAt(indexes.get(i)));
                }
            }
            str = new String(sb);
            if(isCorrect(str)) passwordNeeded = false;


        }

        try{
            outputStream.write(str.getBytes());
        }catch(IOException exc){
        }

        return outputStream;
    }

    public static boolean isCorrect(String password){
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){8,}");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public static List<Integer> getIndexes(){
        Random rnd = new Random();
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < 8; ){
            indexes.add(rnd.nextInt(26));
            i++;
        }
        Collections.shuffle(indexes);
        return indexes;
    }
}

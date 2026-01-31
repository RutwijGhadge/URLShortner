package com.example.urlShortner.Common;

public class ShorteningUtil {

    // A-Z a-z 0-9 Characters
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final int length = ALPHABET.length();

    /*
        @param num Base10 number
        @return Base62 encoded string representation of Long num
     */
    public static String idToStr(Long num){
        StringBuilder stringBuilder=new StringBuilder();
        while(num > 0){
            stringBuilder.insert(0,ALPHABET.charAt((int)(num%length)));
            num=num/length;
        }
        return stringBuilder.toString();
    }

    /*
        @param str Base62 encoded String
        @return encoded Base10 number of type Long
     */
    public static Long strTOId(String str){
        Long num = 0L;
        for(int i=0;i<str.length();i++){
            num = (num*length) + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }
}

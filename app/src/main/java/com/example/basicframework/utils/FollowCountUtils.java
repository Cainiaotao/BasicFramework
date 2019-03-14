package com.example.basicframework.utils;

public class FollowCountUtils {

    public static String getResultString(int total){
        String result = "";
        if (total >= 1000 && total< 10000){
            String first =  String.valueOf(total).substring(0,1);
            String secend =  String.valueOf(total).substring(1,2);
            result = first+"."+secend+"k";
        }else  if (total >= 10000){
            String first =  String.valueOf(total).substring(0,1);
            String secend =  String.valueOf(total).substring(1,2);
            result = first+"."+secend+"w";
        }else {
            result = String.valueOf(total);
        }
        return result;
    }
}

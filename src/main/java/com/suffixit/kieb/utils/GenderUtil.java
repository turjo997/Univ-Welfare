package com.suffixit.kieb.utils;

public class GenderUtil {

    public static String mapGender(String genderCode) {

        if (genderCode == null) {
            return null;
        }

        switch (genderCode) {
            case "0":
                return "Male";
            case "1":
                return "Female";
            case "2":
                return "Others";
            default:
                return "Unknown";
        }
    }

}

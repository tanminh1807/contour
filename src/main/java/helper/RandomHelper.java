package helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomHelper {

    public static final String EMAIL_SUFFIX = "@mailinator.com";
    public static final String INTERNET_PROTOCOL = "https://";
    public static final String TLD = ".com";

    public static String randomNumber(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random randomGenerator = new Random();
        while (stringBuilder.length() < length) {
            int number = randomGenerator.nextInt(10);
            stringBuilder.append(number);
        }
        return stringBuilder.toString();
    }

    public static String randomAlphaNumericString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        try {
            return random.nextInt(max - min + 1) + min;
        } catch (Exception ex) {
            return min;
        }
    }

    public static String randomWebsite() {
        return INTERNET_PROTOCOL + randomAlphaNumericString(10) + TLD;
    }

    public static String randomEmailWithFullName(String firstName, String lastName) {
        return firstName + "_" + lastName + EMAIL_SUFFIX;
    }
}
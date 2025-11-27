package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * The Main method for this assignment.
     * You can optionally run this to interactively try the three methods.
     * @param args parameters are unused
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    // Method 1 for checking if a string matches a regex: using Pattern.matches
    /**
     * Returns whether a given string is non-empty, contains one lower case letter,
     * at least one upper case letter, at least one digit, and meets the minimum length.
     * @param str the string to check for the properties in
     * @param minLength the minimum length required for the password
     * @return whether the string satisfies the password requirements
     */
    public static boolean checkForPassword(String str, int minLength) {
        // Fix: Check for null first
        if (str == null) {
            return false;
        }
        
        // Lookahead explanation:
        // (?=.*[a-z]) -> Ensure at least one lowercase letter exists
        // (?=.*[A-Z]) -> Ensure at least one uppercase letter exists
        // (?=.*\d)    -> Ensure at least one digit exists
        // .{minLength,} -> Ensure the total length is at least minLength
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{" + minLength + ",}";
        
        return Pattern.matches(regex, str);
    }

    // Method 2 for checking if a string conforms to a regex: using Matcher.find
    /**
     * Returns a list of email addresses that occur in a given string.
     * @param str the string to look for email addresses in
     * @return a list containing the email addresses in the string.
     */
    public static List<String> extractEmails(String str) {
        // Fix: Check for null first
        if (str == null) {
            return new ArrayList<>();
        }

        // [^\\s@]+       -> Match one or more characters that are NOT whitespace or @
        // @              -> Match the @ symbol
        // (?:mail\\.)?   -> Optionally match "mail." (non-capturing group)
        // utoronto\\.ca  -> Match "utoronto.ca"
        final Pattern pattern = Pattern.compile("[^\\s@]+@(?:mail\\.)?utoronto\\.ca");
        final Matcher matcher = pattern.matcher(str);
        final List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    // Method 3 for checking if a string conforms to a regex: using String.matches
    /**
     * Checks whether a given string contains the same capital letter twice.
     * @param str the string to look for doubles in
     * @return whether str contains the same capital letter twice.
     */
    public static boolean checkForDoubles(String str) {
        // Fix: Check for null first
        if (str == null) {
            return false;
        }

        // .* -> Match anything before
        // ([A-Z])  -> Capture a single capital letter (Group 1)
        // .* -> Match anything in between
        // \\1      -> Match the exact character captured in Group 1 again
        // .* -> Match anything after
        return str.matches(".*([A-Z]).*\\1.*");
    }
}

package Utilities;

public class CommonTools {
    public String removeSpace(String input) {
        return input.trim();
    }

    public boolean inputIsEmpty(String input) {
        return (input.equals("")) ? true : false;
    }
}

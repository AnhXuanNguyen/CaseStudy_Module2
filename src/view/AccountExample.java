package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountExample {
    private static final String ACCOUNT_CUSTOMER = "^[A-Za-z-0-9]+$";
    public AccountExample(){}
    public boolean validate(String regex){
        Pattern pattern = Pattern.compile(ACCOUNT_CUSTOMER);
        Matcher matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}

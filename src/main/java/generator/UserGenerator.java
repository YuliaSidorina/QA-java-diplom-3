package generator;

public class UserGenerator {

    public static String DEFAULT_NAME = "Vsevolod";
    public static String DEFAULT_EMAIL = "stdr@test.ru";

    public static String WORKING_EMAIL = "sevamukh@mail.ru";
    public static String DEFAULT_PASSWORD = "pwd123";
    public static String SHORT_PASSWORD = "123";

    public static String getNewRandomEmail() {
        return Math.random() + DEFAULT_EMAIL;
    }
}
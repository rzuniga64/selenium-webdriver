package webdriver.driver_manager;

public class foo {
    private static foo ourInstance = new foo();

    public static foo getInstance() {
        return ourInstance;
    }

    private foo() {
    }
}

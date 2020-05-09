package interfacetest;

public interface TestInterface {
    private String hello() {
        return "hello";
    }

    void say(final String message);

    default void say() {
        say(hello());
    }
}

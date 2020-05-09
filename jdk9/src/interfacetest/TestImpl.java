package interfacetest;

public class TestImpl implements TestInterface{

    public static void main(String[] args) {
        TestImpl test = new TestImpl();
        test.say();
    }

    @Override
    public void say(String message) {
        System.out.println(message);
    }
}

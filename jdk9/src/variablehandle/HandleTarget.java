package variablehandle;

public class HandleTarget {
    public int publicInt = 1;
    private int privateInt = 2;

    @Override
    public String toString() {
        return "HandleTarget{" +
                "publicInt=" + publicInt +
                ", privateInt=" + privateInt +
                '}';
    }
}

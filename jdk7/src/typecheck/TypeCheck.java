package typecheck;

import java.util.ArrayList;
import java.util.List;

public class TypeCheck {
    public static void main(String[] args) {
        List l = new ArrayList<Number>();
        List<String> ls = l;       // unchecked warning
        l.add(0, new Integer(42)); // another unchecked warning
        String s = ls.get(0);      // ClassCastException is thrown
    }
}

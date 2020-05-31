package optional;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Optional.ofNullable(null).orElseThrow();
        Optional.ofNullable(null).orElseThrow(NullPointerException::new);
    }
}

package optional;

import java.util.Optional;

public class Test {
    public static void main(String[] args) throws Exception {
        String test = "test";
        String s = Optional.ofNullable(test)
                .map(i -> i.split(""))
                .map(i -> i[1])
//                .orElse("error")
                .orElseThrow(Exception::new);
    }
}

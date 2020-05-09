package methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Iterator;
import java.util.List;

public class IteratedLoopTest {
    public static void main(String[] args) throws Throwable {
        testIteratedLoop();
    }

    static int body(final int sum, final String value) {
        return sum + value.length();
    }

    public static void testIteratedLoop() throws Throwable {
        final MethodHandle iterator = MethodHandles.constant(
                Iterator.class,
                List.of("a", "bc", "def").iterator());
        final MethodHandle init = MethodHandles.zero(int.class);
        final MethodHandle body = MethodHandles
                .lookup()
                .findStatic(
                        IteratedLoopTest.class,
                        "body",
                        MethodType.methodType(
                                int.class,
                                int.class,
                                String.class));
        final MethodHandle iteratedLoop = MethodHandles
                .iteratedLoop(iterator, init, body);
        System.out.println(iteratedLoop.invoke());
    }
}

package variablehandle;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class VarHandleTest {

    public static void main(String[] args) throws Exception{
        VarHandle publicHandle = MethodHandles.lookup()
                .findVarHandle(HandleTarget.class, "publicInt", int.class);
        HandleTarget handleTarget = new HandleTarget();
        System.out.println(publicHandle.get(handleTarget));
        publicHandle.set(handleTarget, 10);

        VarHandle privateHandle = MethodHandles.privateLookupIn(HandleTarget.class, MethodHandles.lookup())
                .findVarHandle(HandleTarget.class, "privateInt", int.class);
        System.out.println(privateHandle.get(handleTarget));
        privateHandle.set(handleTarget, 20);

        System.out.println(handleTarget);


    }
}

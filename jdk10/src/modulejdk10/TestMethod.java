package modulejdk10;

import outofmodule.TestOutOfModule;

import java.util.ArrayList;
import java.util.List;

public class TestMethod {
    public static List<TestBean> getList(){
        List<TestBean> list = new ArrayList<>();
        list.add(new TestBean());
        return list;
    }

    public static void publicMethod(){
        System.out.println("调用了public方法");
        TestOutOfModule.outOfModuleMethod();
    }

    protected static void protectedMethod(){
        System.out.println("调用了protected方法");
    }

    private static void privateMethod(){
        System.out.println("调用了private方法");
    }
}

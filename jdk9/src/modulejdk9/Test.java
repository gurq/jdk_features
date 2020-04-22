package modulejdk9;

import modulejdk10.TestBean;
import modulejdk10.TestMethod;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        testBean.setId(1);
        System.out.println(testBean.toString());
        List<TestBean> list = TestMethod.getList();
        System.out.println(list.toString());
        TestMethod.publicMethod();
    }
}

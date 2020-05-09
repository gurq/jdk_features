package process;

public class Test {
    public static void main(String[] args) throws Exception{
        ProcessHandle current = ProcessHandle.current();
        System.out.println(current.pid());
        System.out.println(current.info());
        ProcessHandle.allProcesses().forEach(i-> System.out.println(i.pid()+"---"+i.info()));
        current.destroy();
        Thread.sleep(1000000);
    }
}

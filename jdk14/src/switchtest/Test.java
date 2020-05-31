package switchtest;

import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) {
        System.out.println(newSwitch("4"));
    }

    private static String newSwitch(String event){
        var text = """
                你看，Java还能
                这样
                定义String哦
                """;
        var log = switch (event) {
            case "1" -> "User has triggered the play button";
            case "2","3" -> "User needs a break";
            default -> {
                LocalDateTime now = LocalDateTime.now();
                yield "Unknown event " + event + " logged on " + now;
            }
        };
        return log;
    }
}

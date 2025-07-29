import com.example.service.MostActiveCookie;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class MostActiveCookieTest {
    public static void main(String[] args) {
        writeCsvData();
    }

    public static void writeCsvData(){
        String csvContent =
                "cookie,timestamp\n" +
                        "AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00\n" +
                        "SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00\n" +
                        "5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00\n" +
                        "AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00\n" +
                        "SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00\n" +
                        "4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00\n" +
                        "fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00\n" +
                        "4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00\n";

        try (FileWriter writer = new FileWriter("cookie_log.csv")) {
            writer.write(csvContent);
            System.out.println("success write！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@Test
    public void testParseCommand() throws Exception {
        // 构造命令行：java -jar [jar] -f [file] -d [date]
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-jar",
                "target/cookie-analyzer-1.0-SNAPSHOT-jar-with-dependencies.jar",
                "-f", "cookie_log.csv",
                "-d", "2018-12-09"
        );
        ProcessBuilder pb1 = new ProcessBuilder(
                "java",
                "-jar",
                "target/cookie-analyzer-1.0-SNAPSHOT-jar-with-dependencies.jar",
                "-f", "cookie_log.csv",
                "-d", ""
        );

        // 启动进程
        Process process = pb.start();

        // 读取标准输出
        String output = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        ).lines().collect(Collectors.joining("\n"));

        // 等待进程结束
        int exitCode = process.waitFor();

        System.out.println(output);
    }

//    @Test
    public void TestCookie() throws IOException {
        List<String> cookie =  MostActiveCookie.process("cookie_log.csv","2018-12-09");
        System.out.println(cookie);
        List<String> cookie1 =  MostActiveCookie.process("cookie_log.csv","2018-12-08");
        System.out.println(cookie1);
        List<String> cookie2 =  MostActiveCookie.process("cookie_log.csv","2018-12-07");
        System.out.println(cookie2);
        List<String> cookie3 =  MostActiveCookie.process("cookie_log.csv","2018-12-06");
        System.out.println(cookie3);

    }




}

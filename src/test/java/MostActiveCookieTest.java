import com.example.service.MostActiveCookie;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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


    public void TestCookie() throws IOException {
        List<String> cookie =  MostActiveCookie.process("cookie_log.csv","2018-12-06");

        System.out.println(cookie);
    }
}

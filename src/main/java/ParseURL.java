import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ParseURL {

    final String link =
            "https://api.vk.com/method/users.search?count=150&city=953&country=15&age_from=14&age_to=21&fields=relation&sex=1&access_token=";

    public String parse() {
        StringBuilder content = new StringBuilder();

        //953 - Balti
        //country id = 15
        // final String ACCESS_TOKEN = "5e54954f0482784f8299d866c2e1307e12a435afebe0f6a948314815491c71f9e38136b62438983c7a694";

        try {
            URL url = new URL(
                    link + Main.ACCESS_TOKEN + "&v=5.130"
            );
            URLConnection parserConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(parserConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error" + e);
        }

        return content.toString();
    }
}

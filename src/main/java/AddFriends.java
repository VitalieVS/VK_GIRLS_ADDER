import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AddFriends {
    public void addFriends(ArrayList<Long> list, String ACCESS_TOKEN) throws InterruptedException {
        StringBuilder content = new StringBuilder();
        for (Long value: list) {
            TimeUnit.SECONDS.sleep(5);
            try {
                URL url = new URL(
                        "https://api.vk.com/method/friends.add?&user_id="
                                + value + "&access_token="
                                + ACCESS_TOKEN
                                + "&v=5.130"
                );
                URLConnection urlConn = url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                bufferedReader.close();
                System.out.println(content);
                System.out.println("ID: " + value + "added!");
            } catch (IOException e) {
                System.out.println("Error:" + e);
            }
        }
    }
}

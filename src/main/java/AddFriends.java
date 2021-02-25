import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AddFriends {
    CaptchaHandler captchaHandler = new CaptchaHandler();
    URL url;
    URLConnection urlConn;

    public void addFriends(ArrayList<Long> list) throws InterruptedException {
        StringBuilder content = new StringBuilder();
        for (Long value : list) {
            TimeUnit.SECONDS.sleep(5);
            try {
                url = new URL(
                        "https://api.vk.com/method/friends.add?&user_id="
                                + value + "&access_token="
                                + Main.ACCESS_TOKEN
                                + "&v=5.130"
                );
                urlConn = url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                bufferedReader.close();

                if (content.toString().contains("captcha")) {
                    System.out.println(content.toString());
                    JSONObject captchaLink = new JSONObject(content.toString());
                    String link = captchaLink.getJSONObject("error").getString("captcha_img");
                    Long sid = captchaLink.getJSONObject("error").getLong("captcha_sid");

                    System.out.println(captchaLink);
                    captchaHandler.createCaptcha(link);
                    sendCaptcha(value, sid);
                } else {
                    System.out.println("ID: " + value + " added!");
                }
            } catch (IOException e) {
                System.out.println("Error:" + e);
            }
        }
    }

    public void sendCaptcha(Long value, Long CAPTCHA_SID) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String readCaptcha;
            System.out.println("Please give captcha below");
            readCaptcha = br.readLine();

            url = new URL(
                    "https://api.vk.com/method/friends.add?&user_id="
                            + value + "&access_token="
                            + Main.ACCESS_TOKEN
                            + "&captcha_sid=" + CAPTCHA_SID
                            + "&captcha_key=" +
                            readCaptcha + "&v=5.130"
            );
            urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }

            System.out.println(content.toString());
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error:" + e);
        }
    }

}

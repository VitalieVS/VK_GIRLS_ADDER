import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CreateList {
    final String ACCESS_TOKEN;

    CreateList(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public ArrayList<Long> createList(String data) {
        ArrayList<Long> resultList = new ArrayList<>();
        if (!data.isEmpty()) {
            JSONObject obj = new JSONObject(data);
            JSONArray keys = obj.getJSONObject("response").getJSONArray("items");

            for (int i = 0; i < keys.length(); i++) {
                JSONObject dataObject = keys.getJSONObject(i);

                if (dataObject.has("relation")) {
                    int relationStatus = dataObject.getInt("relation");

                    if (relationStatus == 1 ||
                            relationStatus == 5 ||
                            relationStatus == 6 ||
                            relationStatus == 0) {

                        resultList.add(dataObject.getLong("id"));
                    }
                }

                if (dataObject.getBoolean("is_closed")) {
                    resultList.add(dataObject.getLong("id"));
                }

            }
        }
        return resultList;
    }

    public String getData() {
        StringBuilder content = new StringBuilder();
        //953 - Balti
        //country id = 15
        // final String ACCESS_TOKEN = "5e54954f0482784f8299d866c2e1307e12a435afebe0f6a948314815491c71f9e38136b62438983c7a694";

        try {
            URL url = new URL(
                    "https://api.vk.com/method/users.search?count=6&city=953&country=15&age_from=14&age_to=21&fields=relation&sex=1&access_token="
                            + ACCESS_TOKEN + "&v=5.130");
            URLConnection urlConn = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
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

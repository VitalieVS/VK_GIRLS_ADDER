import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreateList {
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
        ParseURL parser = new ParseURL(
                "5e54954f0482784f8299d866c2e1307e12a435afebe0f6a948314815491c71f9e38136b62438983c7a694"
        );

        return parser.parse(
                "https://api.vk.com/method/users.search?count=150&city=953&country=15&age_from=14&age_to=21&fields=relation&sex=1&access_token=");
    }
}

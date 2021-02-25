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
                "24723cc38f918b011d76e705109ded4110acdb469d512e95335d4f29606920cae89314e3248928d3a5dc2"
        );

        return parser.parse(
                "https://api.vk.com/method/users.search?count=150&city=953&country=15&age_from=14&age_to=21&fields=relation&sex=1&access_token=");
    }
}

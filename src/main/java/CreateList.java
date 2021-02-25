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
        ParseURL parser = new ParseURL();

        return parser.parse();
    }
}

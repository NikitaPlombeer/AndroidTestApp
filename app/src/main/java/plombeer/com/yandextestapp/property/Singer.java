package plombeer.com.yandextestapp.property;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import plombeer.com.yandextestapp.api.FillType;

/**
 * Created by Никита on 05.04.2016.
 */
public class Singer {

    public static final String SMALL_COVER = "small";
    public static final String BIG_COVER = "big";

    private JSONObject jsonObject;

    public Singer(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getId() {
        try {
            return jsonObject.getString(FillType.id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getName() {
        try {
            return jsonObject.getString(FillType.name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getTracks() {
        try {
            return jsonObject.getString(FillType.tracks);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getAlbums() {
        try {
            return jsonObject.getString(FillType.albums);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getLink() {
        try {
            return jsonObject.getString(FillType.link);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDescription() {
        try {
            return jsonObject.getString(FillType.description);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getCover(String type){
        try {
            return jsonObject.getJSONObject(FillType.cover).getString(type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getGenres() {
        try {
            JSONArray array = jsonObject.getJSONArray(FillType.genres);
            String end = "";
            for (int i = 0; i < array.length() - 1; i++) {
                end = end.concat(array.getString(i)).concat(", ");
            }
            if(array.length() != 0){
                end = end.concat(array.getString(array.length() - 1));
            }
            return end;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

}

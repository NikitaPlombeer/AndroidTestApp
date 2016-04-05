package plombeer.com.yandextestapp.property;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Никита on 05.04.2016.
 */
public class SingerManager {

    private static SingerManager instance;
    private HashMap<String, Singer> singerHashMap;
    private ArrayList<Singer> singers;

    private SingerManager(JSONArray jsonArray) {
        singerHashMap = new HashMap<>();
        singers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Singer singer = new Singer(jsonArray.getJSONObject(i));
                singers.add(singer);
                singerHashMap.put(singer.getId(), singer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static SingerManager newInstance(JSONArray jsonArray) {
        instance = new SingerManager(jsonArray);
        return instance;
    }

    public Singer getSinger(String id){
        return singerHashMap.get(id);
    }
    
    public static SingerManager getInstance() {
        return instance;
    }

    public ArrayList<Singer> getSingers() {
        return singers;
    }
}

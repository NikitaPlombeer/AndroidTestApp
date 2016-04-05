package plombeer.com.yandextestapp.api;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONArray;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

/**
 * Created by Никита on 05.04.2016.
 */
public class API {
    public static final String JSON_URL = "http://download.cdn.yandex.net/mobilization-2016/artists.json";
    private static final String TAG = "YandexTestApp";

    public static Response get(String url) throws IOException {
        Log.i("R", url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = getOkHttpClient().newCall(request).execute();
        return response;
    }

    private static OkHttpClient okHttpClient;
    public static OkHttpClient getOkHttpClient() {
        if(okHttpClient == null) {
            Log.i(TAG, "OkHttpClient create");

            okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(90, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(90, TimeUnit.SECONDS);

            SSLContext sslContext;
            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, null, null);
            } catch (Exception e) {
                throw new AssertionError(); // The system has no TLS. Just give up.
            }
            okHttpClient.setSslSocketFactory(sslContext.getSocketFactory());
        }
        return okHttpClient;
    }

    public static JSONArray getSingers() throws IOException, JSONException {
        Response response = get(JSON_URL);
        JSONArray jsonObject = new JSONArray(response.body().string());
        response.body().close();
        return jsonObject;
    }
}

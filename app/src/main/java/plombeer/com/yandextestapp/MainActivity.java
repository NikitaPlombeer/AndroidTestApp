package plombeer.com.yandextestapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import plombeer.com.yandextestapp.api.FillType;
import plombeer.com.yandextestapp.property.SingerManager;
import plombeer.com.yandextestapp.recycleview.DividerItemDecoration;
import plombeer.com.yandextestapp.recycleview.RecyclerItemClickListener;
import plombeer.com.yandextestapp.recycleview.SingersRecycleViewAdapter;
import plombeer.com.yandextestapp.api.API;
import plombeer.com.yandextestapp.api.MyApp;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView rvMain;

    public class DownloadSingersAsyncTask extends AsyncTask<Void, Void, JSONArray>{

        @Override
        protected JSONArray doInBackground(Void... params) {
            try {
                //Загружаем певцов в JSONArray
                return API.getSingers();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if(jsonArray == null){
                Toast.makeText(MyApp.getContext(), "Возникли какие-то проблемы", Toast.LENGTH_SHORT).show();
            } else{
                //Загружаем в Singleton вернувшийся список Певцов
                SingerManager.newInstance(jsonArray);

                SingersRecycleViewAdapter adapter = new SingersRecycleViewAdapter(SingerManager.getInstance().getSingers());
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyApp.getContext());
                rvMain.setAdapter(adapter);
                rvMain.setLayoutManager(layoutManager);
                rvMain.addItemDecoration(
                        new DividerItemDecoration(MyApp.getContext(), R.drawable.devider));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.singers);

        rvMain = (RecyclerView)findViewById(R.id.rvMain);

        //Обработка нажатий на Item в RecycleView
        rvMain.addOnItemTouchListener(new RecyclerItemClickListener(MyApp.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MyApp.getContext(), SingerPreviewActivity.class);

                //Передаем id певца, на которого мы нажали
                intent.putExtra(FillType.id, SingerManager.getInstance().getSingers().get(position).getId());
                startActivity(intent);
            }
        }));

        new DownloadSingersAsyncTask().execute();

    }

}

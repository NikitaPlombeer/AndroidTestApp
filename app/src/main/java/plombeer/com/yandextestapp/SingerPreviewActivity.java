package plombeer.com.yandextestapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import plombeer.com.yandextestapp.api.FillType;
import plombeer.com.yandextestapp.property.Singer;
import plombeer.com.yandextestapp.property.SingerManager;

public class SingerPreviewActivity extends AppCompatActivity {

    public Singer currentSinger;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_preview);

        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

        //Берем по id текущего певца из SingleManager
        currentSinger = SingerManager.getInstance().getSinger(getIntent().getStringExtra(FillType.id));

        //Отображаем данные на экране
        changeData();
    }

    public void changeData(){
        if(currentSinger != null){
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            TextView genresTextView = (TextView)findViewById(R.id.genresTextView);
            TextView infoTextView = (TextView)findViewById(R.id.infoTextView);
            TextView descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
            TextView linkTextView = (TextView)findViewById(R.id.linkTextView);

            getSupportActionBar().setTitle(currentSinger.getName());

            //Загружаем большую картинку
            UrlImageViewHelper.setUrlDrawable(imageView, currentSinger.getCover(Singer.BIG_COVER), R.drawable.default_image);

            genresTextView.setText(currentSinger.getGenres());
            infoTextView.setText(currentSinger.getAlbums().concat(" альбомов • ").concat(currentSinger.getTracks()).concat(" песен"));
            descriptionTextView.setText(currentSinger.getDescription());
            linkTextView.setText(currentSinger.getLink());
        }
    }
}

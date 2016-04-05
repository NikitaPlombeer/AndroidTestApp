package plombeer.com.yandextestapp.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;

import plombeer.com.yandextestapp.R;
import plombeer.com.yandextestapp.property.Singer;

/**
 * Created by Никита on 05.04.2016.
 */
public class SingersRecycleViewAdapter extends RecyclerView.Adapter<SingersRecycleViewAdapter.ViewHolder>{

    ArrayList<Singer> singers;

    public SingersRecycleViewAdapter(ArrayList<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singer_fragment, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Singer singer = singers.get(position);
        holder.nameTextView.setText(singer.getName());
        holder.genresTextView.setText(singer.getGenres());
        holder.infoTextView.setText(singer.getAlbums().concat(" альбомов, ").concat(singer.getTracks()).concat(" песен"));
        UrlImageViewHelper.setUrlDrawable(holder.imageView, singer.getCover(Singer.SMALL_COVER), R.drawable.default_image);
    }

    @Override
    public int getItemCount() {
        return singers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView nameTextView;
        TextView genresTextView;
        TextView infoTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);
            genresTextView = (TextView)itemView.findViewById(R.id.genresTextView);
            infoTextView = (TextView)itemView.findViewById(R.id.infoTextView);
        }
    }
}

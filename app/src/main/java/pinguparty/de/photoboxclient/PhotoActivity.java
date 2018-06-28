package pinguparty.de.photoboxclient;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import pinguparty.de.photoboxclient.Model.Photo;

public class PhotoActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "PhotoActivity.PHOTO";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imageView =(ImageView) findViewById(R.id.image);
        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);

        Glide.with(this)
                .asBitmap()
                .load(photo.getUrl())
                .apply(new RequestOptions()
                    .error(R.drawable.ic_cloud_off_red)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                        onPalette(Palette.from(resource).generate());
                        imageView.setImageBitmap(resource);
                        return false;
                    }

                    /*@Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource){
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache) {

                        onPalette(Palette.from(resource).generate());
                        imageView.setImageBitmap(resource);

                        return false;
                    }*/

                    public void onPalette(Palette palette) {
                        if (null != palette) {
                            ViewGroup parent = (ViewGroup) imageView.getParent().getParent();
                            parent.setBackgroundColor(palette.getDarkVibrantColor(Color.GRAY));
                        }
                    }

                })
                .into(imageView);
    }
}

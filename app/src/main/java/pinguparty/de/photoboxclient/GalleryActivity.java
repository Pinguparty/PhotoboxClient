package pinguparty.de.photoboxclient;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import pinguparty.de.photoboxclient.Model.Photo;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_images);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ImageGalleryAdapter adapter = new ImageGalleryAdapter(this,Photo.getPhotos());
        recyclerView.setAdapter(adapter);
    }

    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {

        @NonNull
        @Override
        public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View photoView = inflater.inflate(R.layout.images_view,parent,false);

            ImageGalleryAdapter.MyViewHolder viewHolder = new ImageGalleryAdapter.MyViewHolder(photoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ImageGalleryAdapter.MyViewHolder holder, int position) {
            Photo photo = photos[position];
            ImageView imageView = holder.photoImageView;

            Glide.with(context)
                    .load(photo.getUrl())
                    .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_cloud_off_red)
                        .centerCrop()
                        .dontAnimate()
                        .dontTransform())
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (photos.length);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView photoImageView;

            public MyViewHolder (View itemView) {
                super(itemView);
                photoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION) {
                    Photo photo = photos[pos];

                    Intent intent = new Intent(context,PhotoActivity.class);
                    intent.putExtra(PhotoActivity.EXTRA_PHOTO,photo);
                    startActivity(intent);
                }
            }
        }
        private Photo[] photos;
        private Context context;

        public ImageGalleryAdapter(Context context, Photo[] photos){
            this.context = context;
            this.photos = photos;
        }
    }
}

package tv.kinochi.kinochi.view.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import tv.kinochi.kinochi.R;

public class PostersAdapter extends RecyclerView.Adapter<PostersAdapter.ViewHolder> {

    private List<String> posterUrlList;


    public PostersAdapter(List<String> posterUrlList) {
        this.posterUrlList = posterUrlList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_poster_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View itemView = holder.itemView;
        itemView.setEnabled(true);

        String urlPoster = posterUrlList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(urlPoster)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {

                        if (isFirstResource) {
                            holder.imageView.setVisibility(View.VISIBLE);
                            holder.imageView.setImageDrawable(resource);
                            return true;
                        } else return false;
                    }

                }).into(holder.imageView);

    }

    public void setPosterUrlList(List<String> posterUrlList) {
        this.posterUrlList = posterUrlList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posterUrlList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgPoster);
        }
    }
}

package tv.kinochi.kinochi.view.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.presenter.FilmsPresenter;
import tv.kinochi.kinochi.presenter.vo.ItemFilmVO;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder>  {

    public static final int VIEW_TYPE_VERTICAL = 0;
    public static final int VIEW_TYPE_HORIZONTAL = 1;

    private List<ItemFilmVO> films;
    private FilmsPresenter filmsPresenter;
    private String viewType;



    public FilmsAdapter(List<ItemFilmVO> films, FilmsPresenter filmsPresenter, String viewType) {
        this.films = films;
        this.filmsPresenter = filmsPresenter;
        this.viewType = viewType;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_VERTICAL:
                return new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.model_film_poster_vertical, parent, false));
            case VIEW_TYPE_HORIZONTAL:
                return new ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.model_film_poster_horizontal, parent, false));
            default:
                return null;
        }



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemFilmVO itemFilmVO = films.get(position);

        View itemView = holder.itemView;
        itemView.setEnabled(true);


        Glide.with(holder.itemView.getContext())
                .load(itemFilmVO.getPosterUrl())
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
                            holder.imgPoster.setVisibility(View.VISIBLE);
                            holder.imgPoster.setImageDrawable(resource);
                            return true;
                        } else return false;
                    }

                }).into(holder.imgPoster);

        holder.tvNameRu.setText(itemFilmVO.getNameRu());
        holder.tvQuality.setText(itemFilmVO.getQuality());

        itemView.setOnClickListener(view -> filmsPresenter.openFilmCard(itemFilmVO.getUid()));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void addFilms(List<ItemFilmVO> films) {
        this.films.addAll(films);
        notifyDataSetChanged();
    }

    public void removeAll() {
        if (getItemCount() != 0) {
            films = new ArrayList<>();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (viewType) {
            case "vertical":
                return VIEW_TYPE_VERTICAL;
            case "horizontal":
                return VIEW_TYPE_HORIZONTAL;
            default:
                return VIEW_TYPE_VERTICAL;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameRu;
        TextView tvQuality;
        ImageView imgPoster;

        ViewHolder(View itemView) {
            super(itemView);

            tvNameRu = itemView.findViewById(R.id.tvNameRu);
            tvQuality = itemView.findViewById(R.id.tvQuality);
            imgPoster = itemView.findViewById(R.id.imgPoster);
        }
    }
}

package tv.kinochi.kinochi.view.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rx.Observable;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.presenter.StreamingVideoPresenter;
import tv.kinochi.kinochi.presenter.vo.VideoVO;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private List<VideoVO> videoVOS;
    private StreamingVideoPresenter presenter;

    public VideosAdapter(List<VideoVO> videoVOS, StreamingVideoPresenter presenter) {
        this.videoVOS = videoVOS;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View seasonView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_videos, parent, false);
        return new ViewHolder(seasonView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VideoVO videoVO = videoVOS.get(position);

        View itemView = holder.itemView;
        itemView.setEnabled(true);


        holder.tvLabel.setText(videoVO.getLabel());
        if (videoVO.getDetail() != null) {
            holder.tvDetail.setVisibility(View.VISIBLE);
            holder.tvDetail.setText(videoVO.getDetail());
        } else holder.tvDetail.setVisibility(View.GONE);


            if (videoVO.isChecked()) {
                holder.clVideo.setBackgroundColor(itemView.getResources()
                        .getColor(R.color.colorAccent));
            } else holder.clVideo.setBackgroundColor(itemView.getResources()
                    .getColor(R.color.backgroundItemFilter));

            itemView.setOnClickListener(v -> {

                Observable.from(videoVOS).forEach(seasonVO1 -> seasonVO1.setChecked(false));

                videoVO.setChecked(true);
                notifyDataSetChanged();
                presenter.addVideo(videoVO.getVideoVOList());
            });

    }

    public int getCheckedPosition() {
        for (int i = 0; i < videoVOS.size(); i++) {
            if (videoVOS.get(i).isChecked()) return i;
        }
        return 0;
    }


    @Override
    public int getItemCount() {
        return videoVOS.size();
    }

    @Override
    public int getItemViewType(int position) {
        return videoVOS.get(position).getViewType();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout clVideo;
        TextView tvLabel;
        TextView tvDetail;

        ViewHolder(View itemView) {
            super(itemView);

            tvLabel = itemView.findViewById(R.id.tvLabel);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            clVideo = itemView.findViewById(R.id.clVideo);
        }
    }
}

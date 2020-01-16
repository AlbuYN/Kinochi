package tv.kinochi.kinochi.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.presenter.StreamingVideoPresenter;
import tv.kinochi.kinochi.presenter.vo.VideoBlockVO;
import tv.kinochi.kinochi.presenter.vo.VideoVO;

public class BlockVideosAdapter extends RecyclerView.Adapter<BlockVideosAdapter.ViewHolder> {


    private List<VideoBlockVO> videoBlockVOS;
    private StreamingVideoPresenter presenter;

    public BlockVideosAdapter(List<VideoBlockVO> videoBlockVOS, StreamingVideoPresenter presenter) {
        this.videoBlockVOS = videoBlockVOS;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_block_films,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VideoBlockVO videoBlockVO = videoBlockVOS.get(position);
        View itemView = holder.itemView;

        itemView.setEnabled(true);

        holder.tvNameBlock.setText(videoBlockVO.getTitle());

        List<VideoVO> videoVOList = videoBlockVO.getVideoVOS();

        VideosAdapter videosAdapter = new VideosAdapter(videoVOList, presenter);


        holder.rvBlockFilms.setHasFixedSize(true);


        holder.rvBlockFilms.setLayoutManager(new LinearLayoutManager(presenter
                .getContext(), LinearLayoutManager.HORIZONTAL, false));


        holder.rvBlockFilms.setAdapter(videosAdapter);
        holder.rvBlockFilms.setNestedScrollingEnabled(false);

        holder.rvBlockFilms.smoothScrollToPosition(videosAdapter.getCheckedPosition());


    }

    public void addVideoBlock(VideoBlockVO video) {

        for (int i = 0; i < videoBlockVOS.size(); i++) {
            if (videoBlockVOS.get(i).getTitle().equals(video.getTitle())){
                videoBlockVOS.get(i).setVideoVOS(video.getVideoVOS());
                notifyItemChanged(i);
                return;
            }
        }
        videoBlockVOS.add(video);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return videoBlockVOS.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameBlock;
        RecyclerView rvBlockFilms;

        ViewHolder(View itemView) {
            super(itemView);

            tvNameBlock = itemView.findViewById(R.id.tvNameBlock);
            rvBlockFilms = itemView.findViewById(R.id.rvBlockFilms);
        }
    }
}

package tv.kinochi.kinochi.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.presenter.StreamingVideoPresenter;
import tv.kinochi.kinochi.presenter.vo.LinksListVO;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private List<LinksListVO> seriesVOList;
    private StreamingVideoPresenter presenter;


    public SeriesAdapter(List<LinksListVO> seriesVOList, StreamingVideoPresenter presenter) {
        this.seriesVOList = seriesVOList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_series, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LinksListVO seriesVO = seriesVOList.get(position);
        View itemView = holder.itemView;
        itemView.setEnabled(true);

        holder.tvSeries.setText(seriesVO.getLabel());
        itemView.setOnClickListener(v -> presenter.play(seriesVO.getSource()));
    }

    public void setSeriesVOList(List<LinksListVO> seriesVOList) {
        this.seriesVOList = seriesVOList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return seriesVOList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSeries;

        ViewHolder(View itemView) {
            super(itemView);
            tvSeries = itemView.findViewById(R.id.tvSeries);
        }
    }
}

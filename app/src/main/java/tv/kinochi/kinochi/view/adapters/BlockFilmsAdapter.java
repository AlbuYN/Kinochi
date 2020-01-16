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
import tv.kinochi.kinochi.presenter.FilmsPresenterList;
import tv.kinochi.kinochi.presenter.vo.BlockFilmsVO;
import tv.kinochi.kinochi.presenter.vo.ItemFilmVO;

public class BlockFilmsAdapter extends RecyclerView.Adapter<BlockFilmsAdapter.ViewHolder> {

    private List<BlockFilmsVO> blockFilmsVOList;
    private FilmsPresenterList presenter;

    public BlockFilmsAdapter(List<BlockFilmsVO> blockFilmsVOList, FilmsPresenterList presenter) {
        this.blockFilmsVOList = blockFilmsVOList;
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

        BlockFilmsVO blockFilmsVO = blockFilmsVOList.get(position);
        View itemView = holder.itemView;

        itemView.setEnabled(true);

        holder.tvNameBlock.setText(blockFilmsVO.getName());

        List<ItemFilmVO> itemFilmVOList = blockFilmsVO.getItemFilmVOList();
        FilmsAdapter filmsAdapter = new FilmsAdapter(itemFilmVOList, presenter,
                blockFilmsVO.getViewType());

        holder.rvBlockFilms.setHasFixedSize(true);


        holder.rvBlockFilms.setLayoutManager(new LinearLayoutManager(presenter
                .getContext(), LinearLayoutManager.HORIZONTAL, false));


        holder.rvBlockFilms.setAdapter(filmsAdapter);
        holder.rvBlockFilms.setNestedScrollingEnabled(false);

    }

    @Override
    public int getItemCount() {
        return blockFilmsVOList.size();
    }


    public void setBlockFilmsVOList(List<BlockFilmsVO> blockFilmsVOList) {
        this.blockFilmsVOList = blockFilmsVOList;
        notifyDataSetChanged();
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

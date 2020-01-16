package tv.kinochi.kinochi.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.presenter.FilterFilmsPresenter;
import tv.kinochi.kinochi.presenter.vo.BlockFiltersVO;
import tv.kinochi.kinochi.presenter.vo.ItemCategoryVO;

public class BlockFiltersAdapter extends RecyclerView.Adapter<BlockFiltersAdapter.ViewHolder> {

    private List<BlockFiltersVO> blockFiltersVOList;
    private FilterFilmsPresenter presenter;


    public BlockFiltersAdapter(List<BlockFiltersVO> blockFiltersVOList,
                               FilterFilmsPresenter presenter) {
        this.blockFiltersVOList = blockFiltersVOList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_block_filters,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BlockFiltersVO blockFiltersVO = blockFiltersVOList.get(position);
        View itemView = holder.itemView;

        itemView.setEnabled(true);

        holder.tvNameBlock.setText(blockFiltersVO.getName());

        List<ItemCategoryVO> categoriesList = blockFiltersVO.getFilterVOList();

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesList, presenter);
        holder.rvFilters.setHasFixedSize(true);


        /*
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);
        */

        //layoutManager = new GridLayoutManager(getActivity(), metricsB.widthPixels/213);

        holder.rvFilters.setLayoutManager(new GridLayoutManager(itemView.getContext(), 3));


        holder.rvFilters.setAdapter(categoriesAdapter);
        holder.rvFilters.setNestedScrollingEnabled(false);

    }

    @Override
    public int getItemCount() {
        return blockFiltersVOList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameBlock;
        RecyclerView rvFilters;


        ViewHolder(View itemView) {
            super(itemView);

            tvNameBlock = itemView.findViewById(R.id.tvNameBlock);
            rvFilters = itemView.findViewById(R.id.rvFilters);
        }
    }


    public void setBlockFiltersVOList(List<BlockFiltersVO> blockFiltersVOList) {
        this.blockFiltersVOList = blockFiltersVOList;
        notifyDataSetChanged();
    }
}

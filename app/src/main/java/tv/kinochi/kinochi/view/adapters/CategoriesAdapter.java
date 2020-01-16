package tv.kinochi.kinochi.view.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.presenter.CategoryPresenter;
import tv.kinochi.kinochi.presenter.FilterFilmsPresenter;
import tv.kinochi.kinochi.presenter.vo.ItemCategoryVO;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private List<ItemCategoryVO> categoriesList;
    private CategoryPresenter presenter;

    public CategoriesAdapter(List<ItemCategoryVO> categoriesList, CategoryPresenter presenter) {
        this.categoriesList = categoriesList;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_film_category, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ItemCategoryVO itemCategoryVO = categoriesList.get(position);
        View itemView = holder.itemView;

        itemView.setEnabled(true);
        holder.tvNameFilter.setText(itemCategoryVO.getName());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameFilter;

        ViewHolder(View itemView) {
            super(itemView);

            tvNameFilter = itemView.findViewById(R.id.tvNameFilter);
        }
    }


    public void setCategoriesList(List<ItemCategoryVO> categoriesList) {
        this.categoriesList = categoriesList;
        notifyDataSetChanged();
    }
}

package tv.kinochi.kinochi.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.mappers.BlockFiltersMapper;
import tv.kinochi.kinochi.presenter.vo.BlockFiltersVO;
import tv.kinochi.kinochi.view.fragments.NavigationView;

public class FilterFilmsPresenter extends BasePresenter implements CategoryPresenter {

    private final static String BUNDLE_BLOCK_FILTERS_LIST_KEY = "BUNDLE_BLOCK_FILTERS_LIST_KEY";

    @Inject
    BlockFiltersMapper blockFiltersMapper;

    private NavigationView view;
    private List<BlockFiltersVO> blockFiltersVOList;

    public void onCreate(NavigationView view) {
        App.getComponent().inject(this);
        this.view = view;
    }


    public void onCreateView(Bundle saveInstanceState) {

        if (saveInstanceState != null) {
            blockFiltersVOList = (List<BlockFiltersVO>)saveInstanceState
                    .getSerializable(BUNDLE_BLOCK_FILTERS_LIST_KEY);
        }

        if (!isBlockFiltersListEmpty()) view.showBlockFilters(blockFiltersVOList);
        else loadFilters();
    }

    public void loadFilters() {
        Subscription subscription = model.getFilters()
                .map(blockFiltersMapper)
                .subscribe(this::onLoadFiltersSuccess, this::onFailed);
        addSubscription(subscription);
    }

    private void onLoadFiltersSuccess(List<BlockFiltersVO> blockFiltersVOList) {
        if (!blockFiltersVOList.isEmpty()) {
            this.blockFiltersVOList = blockFiltersVOList;
            view.showBlockFilters(blockFiltersVOList);
        }

    }

    private void onFailed(Throwable throwable) {
        errorProcessing.onError(throwable, view);
    }

    boolean isBlockFiltersListEmpty() {
        return blockFiltersVOList == null || blockFiltersVOList.isEmpty();
    }


    public void onSaveInstanceState(Bundle outState){

        if(!isBlockFiltersListEmpty()){
            outState.putSerializable(BUNDLE_BLOCK_FILTERS_LIST_KEY,new ArrayList<>(blockFiltersVOList));
        }
    }


    @Override
    public void loadFilmsOfCategory() {

    }
}

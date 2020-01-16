package tv.kinochi.kinochi.view.fragments;

import java.util.List;

import tv.kinochi.kinochi.presenter.vo.BlockFiltersVO;

public interface NavigationView extends MyView {

    void showBlockFilters(List<BlockFiltersVO> blockFiltersVOList);

}

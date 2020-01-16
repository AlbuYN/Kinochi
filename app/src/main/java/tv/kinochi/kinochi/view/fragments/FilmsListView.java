package tv.kinochi.kinochi.view.fragments;

import java.util.List;

import tv.kinochi.kinochi.presenter.vo.BlockFilmsVO;

public interface FilmsListView extends MyView {
    void showFilmsList(List<BlockFilmsVO> filmVOList);
    void openFilmCard(String uid);
}

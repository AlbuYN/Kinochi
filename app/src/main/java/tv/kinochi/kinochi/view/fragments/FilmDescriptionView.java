package tv.kinochi.kinochi.view.fragments;

import tv.kinochi.kinochi.presenter.vo.FilmCardVO;

public interface FilmDescriptionView extends MyView {
    void showFilmCard(FilmCardVO filmCardVO);
}

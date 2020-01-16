package tv.kinochi.kinochi.presenter;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscription;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.mappers.FilmDescriptionMapper;
import tv.kinochi.kinochi.presenter.vo.FilmCardVO;
import tv.kinochi.kinochi.view.fragments.FilmDescriptionView;

public class FilmDescriptionPresenter extends BasePresenter implements FilmsPresenter, CategoryPresenter {

    private static final String BUNDLE_FILM_CARD_KEY = "BUNDLE_FILM_CARD_KEY";

    @Inject
    FilmDescriptionMapper mapper;

    private FilmCardVO filmCardVO;
    private FilmDescriptionView view;


    public FilmDescriptionPresenter() {
    }

    public void onCreate(FilmDescriptionView view) {
        App.getComponent().inject(this);
        this.view = view;
    }


    public void onCreateView(Bundle saveInstanceState) {

        if (saveInstanceState != null) {
            filmCardVO = (FilmCardVO) saveInstanceState.getSerializable(BUNDLE_FILM_CARD_KEY);
        }

        if (!isFilmCardEmpty()) view.showFilmCard(filmCardVO);
    }


    public boolean isFilmCardEmpty() {
        return filmCardVO == null;
    }


    public void loadFilmCard(String uid) {
        Subscription subscription = model.getFilmCard(uid)
                .map(mapper)
                .subscribe(this::onLoadFilmCardSuccess, this::onFailed);
        addSubscription(subscription);
    }


    private void onLoadFilmCardSuccess(FilmCardVO filmCardVO) {
        this.filmCardVO = filmCardVO;
        view.showFilmCard(filmCardVO);
    }

    private void onFailed(Throwable e) {
        errorProcessing.onError(e, view);
    }


    public void onSaveInstanceState(Bundle outState){

        if(filmCardVO != null){
            outState.putSerializable(BUNDLE_FILM_CARD_KEY, filmCardVO);
        }
    }


    @Override
    public void openFilmCard(String uid) {

    }

    @Override
    public void loadFilmsOfCategory() {

    }
}

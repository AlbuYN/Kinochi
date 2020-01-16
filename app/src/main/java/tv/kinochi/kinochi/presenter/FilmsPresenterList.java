package tv.kinochi.kinochi.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.mappers.FilmsListMapper;
import tv.kinochi.kinochi.presenter.vo.BlockFilmsVO;
import tv.kinochi.kinochi.view.fragments.FilmsListView;

public class FilmsPresenterList extends BasePresenter implements FilmsPresenter {

    private static final String BUNDLE_FILMS_LIST_KEY = "BUNDLE_FILMS_LIST_KEY";


    @Inject
    FilmsListMapper mapper;

    private FilmsListView view;
    private List<BlockFilmsVO> filmVOList = new ArrayList<>();


    public FilmsPresenterList() {}

    public void onCreate(FilmsListView view) {
        App.getComponent().inject(this);
        this.view = view;
    }


    public void onCreateView(Bundle saveInstanceState) {

        if (saveInstanceState != null) {
            filmVOList = (List<BlockFilmsVO>)saveInstanceState.getSerializable(BUNDLE_FILMS_LIST_KEY);
        }

        if (!isFilmsListEmpty()) view.showFilmsList(filmVOList);
        else loadFilms(0);
    }


    public void loadFilms(int page) {
        Subscription subscription = model.getFilms(page)
                .map(mapper)
                .subscribe(this::onLoadFilmsSuccess, this::onFailed);
        addSubscription(subscription);
    }

    private void onLoadFilmsSuccess(List<BlockFilmsVO> filmVOList) {
        if (filmVOList.size() > 0) {
            this.filmVOList = filmVOList;
            view.showFilmsList(filmVOList);
        }
    }

    private void onFailed(Throwable throwable) {
        errorProcessing.onError(throwable, view);
    }

    private boolean isFilmsListEmpty() {
        return filmVOList == null || filmVOList.isEmpty();
    }

    public void onSaveInstanceState(Bundle outState){

        if(!isFilmsListEmpty()){
            outState.putSerializable(BUNDLE_FILMS_LIST_KEY,new ArrayList<>(filmVOList));
        }
    }




    @Override
    public void openFilmCard(String uid) {
        view.openFilmCard(uid);
    }
}

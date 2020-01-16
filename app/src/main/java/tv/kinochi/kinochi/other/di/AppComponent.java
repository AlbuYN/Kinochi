package tv.kinochi.kinochi.other.di;

import javax.inject.Singleton;

import dagger.Component;
import tv.kinochi.kinochi.model.ModelImp;
import tv.kinochi.kinochi.presenter.BasePresenter;
import tv.kinochi.kinochi.presenter.FilmDescriptionPresenter;
import tv.kinochi.kinochi.presenter.FilmsPresenterList;
import tv.kinochi.kinochi.presenter.FilterFilmsPresenter;
import tv.kinochi.kinochi.presenter.StreamingVideoPresenter;
import tv.kinochi.kinochi.view.fragments.FilmDescriptionFragment;
import tv.kinochi.kinochi.view.fragments.FilmsListFragment;
import tv.kinochi.kinochi.view.fragments.NavigationViewFragment;
import tv.kinochi.kinochi.view.fragments.StreamingVideoFragment;

@Singleton
@Component(modules = {AppModule.class, ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImp modelImp);
    void inject(FilmsPresenterList filmsPresenterList);
    void inject(FilterFilmsPresenter filterFilmsPresenter);
    void inject(FilmDescriptionPresenter filmDescriptionPresenter);
    void inject(BasePresenter basePresenter);
    void inject(StreamingVideoPresenter streamingVideoPresenter);
    void inject(FilmsListFragment filmsListFragment);
    void inject(NavigationViewFragment navigationViewFragment);
    void inject(FilmDescriptionFragment filmDescriptionFragment);
    void inject(StreamingVideoFragment streamingVideoFragment);
}

package tv.kinochi.kinochi.other.di;

import dagger.Module;
import dagger.Provides;
import tv.kinochi.kinochi.presenter.FilmDescriptionPresenter;
import tv.kinochi.kinochi.presenter.FilmsPresenterList;
import tv.kinochi.kinochi.presenter.FilterFilmsPresenter;
import tv.kinochi.kinochi.presenter.StreamingVideoPresenter;

@Module
public class ViewModule {

    @Provides
    FilmsPresenterList filmsPresenter() {return new FilmsPresenterList();}

    @Provides
    FilterFilmsPresenter  filterFilmsPresenter() {return new FilterFilmsPresenter();}

    @Provides
    FilmDescriptionPresenter filmDescriptionPresenter() {return new FilmDescriptionPresenter();}

    @Provides
    StreamingVideoPresenter mediaPresenter() {return new StreamingVideoPresenter();}
}

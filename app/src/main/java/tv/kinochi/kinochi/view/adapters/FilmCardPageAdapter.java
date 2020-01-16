package tv.kinochi.kinochi.view.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import tv.kinochi.kinochi.view.activities.FilmCardView;
import tv.kinochi.kinochi.view.fragments.FilmDescriptionFragment;
import tv.kinochi.kinochi.view.fragments.StreamingVideoFragment;

public class FilmCardPageAdapter extends FragmentStatePagerAdapter {

    @Inject
    Context context;


    //Для получения уж существующего фрагмента tablelayout
    public static final int FILM_DESCRIPTION_FRAGMENT_POSITION = 0;
    public static final int VIDEO_LIST_FRAGMENT_POSITION = 1;

    private int numberOfPages;

    //для того чтобы объекты не создавались при каждом вызове метода getItem()
    private FilmDescriptionFragment filmDescriptionFragment;
    private StreamingVideoFragment streamingVideoFragment;

    private FragmentManager fm;
    private FilmCardView filmCardView;
    private String uid;


    public FilmCardPageAdapter(FragmentManager fm, int numberOfPages, FilmCardView filmCardView, String uid) {
        super(fm);
        this.numberOfPages = numberOfPages;
        this.fm = fm;
        this.filmCardView = filmCardView;
        this.uid = uid;
        filmDescriptionFragment = FilmDescriptionFragment.newInstance(uid);
        streamingVideoFragment = new StreamingVideoFragment();

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return filmDescriptionFragment;
            case 1:
                return streamingVideoFragment;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);

        Bundle bundle = (Bundle) state;
        bundle.setClassLoader(loader);

        Iterable<String> keys = bundle.keySet();
        for (String key : keys) {
            if (key.startsWith("f")) {
                Fragment f = fm.getFragment(bundle, key);
                if (f.getClass() == FilmDescriptionFragment.class) {
                    filmDescriptionFragment = (FilmDescriptionFragment) f;
                    filmCardView.setFilmDescriptionFragment(filmDescriptionFragment);
                } else if (f.getClass() == StreamingVideoFragment.class) {
                    streamingVideoFragment = (StreamingVideoFragment) f;
                    filmCardView.setStreamingVideoFragment(streamingVideoFragment);
                }
            }
        }
    }
}

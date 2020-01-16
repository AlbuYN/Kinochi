package tv.kinochi.kinochi.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.other.ui.CustomViewPager;
import tv.kinochi.kinochi.view.adapters.FilmCardPageAdapter;
import tv.kinochi.kinochi.view.fragments.FilmDescriptionFragment;
import tv.kinochi.kinochi.view.fragments.StreamingVideoFragment;

public class FilmCardActivity extends AppCompatActivity implements FilmCardView, FilmCardActivityCallback {


    @BindView(R.id.pagerCard)
    CustomViewPager pagerCard;

    private FragmentManager fragmentManager;
    private FilmCardPageAdapter filmCardPageAdapter;

    private FilmDescriptionFragment filmDescriptionFragment;
    private StreamingVideoFragment streamingVideoFragment;
    private android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_card);
        ButterKnife.bind(this);



        String uid = getIntent().getStringExtra("uid");

        fragmentManager = getSupportFragmentManager();



        filmCardPageAdapter = new FilmCardPageAdapter(fragmentManager,
                2, this, uid);
        pagerCard.setAdapter(filmCardPageAdapter);
        //pagerCard.setTouchEventEnabled(false);


        //pagerCard.setOffscreenPageLimit(2);

        filmDescriptionFragment = (FilmDescriptionFragment) filmCardPageAdapter
                .getItem(FilmCardPageAdapter.FILM_DESCRIPTION_FRAGMENT_POSITION);
        streamingVideoFragment = (StreamingVideoFragment) filmCardPageAdapter
                .getItem(FilmCardPageAdapter.VIDEO_LIST_FRAGMENT_POSITION);

        pagerCard.setOffscreenPageLimit(5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setFilmDescriptionFragment(FilmDescriptionFragment filmDescriptionFragment) {
        this.filmDescriptionFragment = filmDescriptionFragment;
    }

    @Override
    public void setStreamingVideoFragment(StreamingVideoFragment streamingVideoFragment) {
        this.streamingVideoFragment = streamingVideoFragment;
    }

    @Override
    public void onPlay() {
        pagerCard.setCurrentItem(1);
    }
}

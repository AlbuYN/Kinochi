package tv.kinochi.kinochi.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.BasePresenter;
import tv.kinochi.kinochi.presenter.FilmDescriptionPresenter;
import tv.kinochi.kinochi.presenter.vo.FilmCardVO;
import tv.kinochi.kinochi.view.activities.FilmCardActivityCallback;
import tv.kinochi.kinochi.view.adapters.CategoriesAdapter;
import tv.kinochi.kinochi.view.adapters.FilmsAdapter;
import tv.kinochi.kinochi.view.adapters.PostersAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmDescriptionFragment extends BaseFragment implements FilmDescriptionView {

    @Inject
    FilmDescriptionPresenter presenter;


    @BindView(R.id.tvIMDB)
    TextView tvIMDB;
    @BindView(R.id.tvRtKP)
    TextView tvRtKP;
    @BindView(R.id.tvKinochiRatingPositive)
    TextView tvKinochiRatingPositive;
    @BindView(R.id.tvKinochiRatingNegative)
    TextView tvKinochiRatingNegative;
    @BindView(R.id.tvUpdateDate)
    TextView tvUpdateDate;
    @BindView(R.id.tvQuality)
    TextView tvQuality;
    @BindView(R.id.tvNameEn)
    TextView tvNameEn;
    @BindView(R.id.tvYearAndCounty)
    TextView tvYearAndCounty;
    @BindView(R.id.rvCategories)
    RecyclerView rvCategories;
    @BindView(R.id.rvPosters)
    RecyclerView rvPosters;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.rvRelated)
    RecyclerView rvRelated;
    @BindView(R.id.fbPlay)
    FloatingActionButton fbPlay;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FilmsAdapter filmsAdapter;
    private CategoriesAdapter categoriesAdapter;
    private PostersAdapter postersAdapter;
    private android.support.v7.app.ActionBar actionBar;
    private FilmCardActivityCallback activityCallback;


    private String uid;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallback = (FilmCardActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass() + " must implement "
                    + FilmCardActivityCallback.class);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        App.getComponent().inject(this);
        presenter.onCreate(this);
    }


    public static FilmDescriptionFragment newInstance(String uid) {

        Bundle args = new Bundle();
        args.putString("uid", uid);

        FilmDescriptionFragment filmDescriptionFragment = new FilmDescriptionFragment();
        filmDescriptionFragment.setArguments(args);

        return filmDescriptionFragment;
    }


    public FilmDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.card_film, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*
        switch (item.getItemId()) {
            case R.id.action_find:
                break;
            case R.id.action_find:
                break;
            default:
                break;
        }
        */
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_film_description, container, false);
        ButterKnife.bind(this, view);


        ((AppCompatActivity)Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Веном");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        assert getArguments() != null;


        uid = getArguments().getString("uid");

        rvRelated.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        filmsAdapter = new FilmsAdapter(new ArrayList<>(), presenter, "vertical");
        rvRelated.setAdapter(filmsAdapter);


        rvCategories.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        categoriesAdapter = new CategoriesAdapter(new ArrayList<>(), presenter);
        rvCategories.setAdapter(categoriesAdapter);


        rvPosters.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        postersAdapter = new PostersAdapter(new ArrayList<>());
        rvPosters.setAdapter(postersAdapter);

        presenter.onCreateView(savedInstanceState);

        if (presenter.isFilmCardEmpty()) {
            presenter.loadFilmCard(uid);
        }

        fbPlay.setOnClickListener(v -> activityCallback.onPlay());

        return view;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void showFilmCard(FilmCardVO filmCardVO) {


        tvIMDB.setText(String.valueOf(filmCardVO.getRatingImdb()));
        tvRtKP.setText(String.valueOf(filmCardVO.getRatingKinopoisk()));
        tvKinochiRatingPositive.setText(String.valueOf(filmCardVO.getRatingKinochiPositive()));
        tvKinochiRatingNegative.setText(String.valueOf(filmCardVO.getRatingKinochiNegative()));
        tvUpdateDate.setText(filmCardVO.getUpdateDatetime());
        tvQuality.setText(filmCardVO.getQuality());
        tvNameEn.setText(filmCardVO.getNameEn());
        tvYearAndCounty.setText(filmCardVO.getReleaseDateAndcountry());

        setTextViewHTML(tvDescription, filmCardVO.getDescription());


        checkFilmsAdapter();
        filmsAdapter.addFilms(filmCardVO.getFilmsRelated());
        checkCategoriesAdapter();
        categoriesAdapter.setCategoriesList(filmCardVO.getCategories());
        checkPostersAdapter();
        postersAdapter.setPosterUrlList(filmCardVO.getStills());



        //rvPosters;

    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder, final URLSpan span)
    {
        Log.d("myLog", "span = " + span.getURL());
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(@NonNull View view) {
                if (span.getURL().equals("#google")) {
                    Log.d("myLog", "google");
                    Toast.makeText(getContext(), "google", Toast.LENGTH_SHORT).show();
                } else if (span.getURL().equals("http://yandex.ru")) {
                    Log.d("myLog", "yandex");
                    Toast.makeText(getContext(), "yandex", Toast.LENGTH_SHORT).show();
                }
            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setTextViewHTML(TextView text, String html)
    {
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(), URLSpan.class);
        for(URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void checkPostersAdapter() {
        if (postersAdapter == null) {
            postersAdapter = new PostersAdapter(new ArrayList<>());
        }
    }

    private void checkFilmsAdapter() {
        if (filmsAdapter == null) {
            filmsAdapter = new FilmsAdapter(new ArrayList<>(), presenter, "horizontal");
        }
    }

    private void checkCategoriesAdapter() {
        if (categoriesAdapter == null) {
            categoriesAdapter = new CategoriesAdapter(new ArrayList<>(), presenter);
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}

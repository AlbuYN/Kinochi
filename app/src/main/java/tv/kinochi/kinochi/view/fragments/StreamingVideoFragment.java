package tv.kinochi.kinochi.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import tv.kinochi.kinochi.R;
import tv.kinochi.kinochi.other.App;
import tv.kinochi.kinochi.presenter.BasePresenter;
import tv.kinochi.kinochi.presenter.StreamingVideoPresenter;
import tv.kinochi.kinochi.presenter.vo.LinksListVO;
import tv.kinochi.kinochi.presenter.vo.VideoBlockVO;

import tv.kinochi.kinochi.presenter.vo.VideoVO;
import tv.kinochi.kinochi.view.adapters.BlockVideosAdapter;
import tv.kinochi.kinochi.view.adapters.SeriesAdapter;
import tv.kinochi.kinochi.view.dislogs.DialogSelectQualityVideo;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamingVideoFragment extends BaseFragment implements StreamingVideoView {

    @Inject
    StreamingVideoPresenter presenter;

    @BindView(R.id.rvVideos)
    RecyclerView rvVideos;
    @BindView(R.id.rvSeries)
    RecyclerView rvSeries;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private BlockVideosAdapter blockVideosAdapter;
    private SeriesAdapter seriesAdapter;


    public StreamingVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        App.getComponent().inject(this);
        presenter.onCreate(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ButterKnife.bind(this, view);

        ((AppCompatActivity)Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);


        rvVideos.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        blockVideosAdapter = new BlockVideosAdapter(new ArrayList<>(), presenter);
        rvVideos.setAdapter(blockVideosAdapter);


        rvSeries.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        seriesAdapter = new SeriesAdapter(new ArrayList<>(), presenter);
        rvSeries.setAdapter(seriesAdapter);

        presenter.onCreateView(savedInstanceState);

        //если это фильм а не сериал то просто скрываем rvSeasons и все

        if (presenter.isVideoEmpty()) {
            presenter.loadVideos("");
        }

        return view;
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
    public BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void showSeries(List<LinksListVO> seriesVOList) {
        checkSeriesAdapter();
        seriesAdapter.setSeriesVOList(seriesVOList);

    }

    @Override
    public void showVideoBlock(VideoBlockVO videoBlockVO) {
        checkBlockVideosAdapter();
        blockVideosAdapter.addVideoBlock(videoBlockVO);
    }

    @Override
    public void play(String source) {
        assert getFragmentManager() != null;
        DialogSelectQualityVideo.newInstance(source).show(getFragmentManager(),
                "SelectQualityVideo");
    }

    @Override
    public void showError(String error) {
        makeToast(error);

    }

    private void checkBlockVideosAdapter() {
        if (blockVideosAdapter == null) {
            blockVideosAdapter = new BlockVideosAdapter(new ArrayList<>(), presenter);
        }
    }


    private void checkSeriesAdapter() {
        if (seriesAdapter == null) {
            seriesAdapter = new SeriesAdapter(new ArrayList<>(), presenter);
        }
    }

    private void makeToast(String message) {
        makeToast(message, Objects.requireNonNull(getActivity()).findViewById(android.R.id.content));
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}

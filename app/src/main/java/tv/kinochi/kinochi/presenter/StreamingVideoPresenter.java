package tv.kinochi.kinochi.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import rx.Observable;
import tv.kinochi.kinochi.other.App;

import tv.kinochi.kinochi.presenter.mappers.VideosMapper;
import tv.kinochi.kinochi.presenter.vo.VideoBlockVO;
import tv.kinochi.kinochi.presenter.vo.VideoVO;
import tv.kinochi.kinochi.view.fragments.StreamingVideoView;

public class StreamingVideoPresenter extends BasePresenter {

    private static final String BUNDLE_VIDEOS_VO_KEY = "BUNDLE_VIDEOS_VO_KEY";


    @Inject
    VideosMapper videosMapper;

    private StreamingVideoView view;

    private List<VideoVO> videoVOS;

    public void onCreate(StreamingVideoView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    public void onCreateView(Bundle saveInstanceState) {

        if (saveInstanceState != null) {
            videoVOS = (List<VideoVO>) saveInstanceState.getSerializable(BUNDLE_VIDEOS_VO_KEY);
        }
        if (!isVideoEmpty())  {
            addVideo(videoVOS);
        }
    }

    public void loadVideos(String uid) {
        addSubscription(model.getStreamingVideos(uid)
                .map(videosMapper)
                .subscribe(this::onLoadVideosSuccess, this::onFailed));
    }

    private void onLoadVideosSuccess(List<VideoVO> videoVOS) {
        this.videoVOS = videoVOS;
        addVideo(videoVOS);
    }

    public void addVideo(List<VideoVO> videoVOS) {
        if (videoVOS != null) {
            addSubscription(Observable.from(videoVOS)
                    .filter(VideoVO::isChecked)
                    .switchIfEmpty(Observable.from(videoVOS)
                            .first()
                            .map(videoVO -> {
                                videoVO.setChecked(true);
                                return videoVO;}))
                    .subscribe(videoVO -> {
                        view.showVideoBlock(new VideoBlockVO(videoVO.getGroupLabel(), videoVOS));
                        if (videoVO.getLinksList() != null) {
                            view.showSeries(videoVO.getLinksList());
                        }
                        addVideo(videoVO.getVideoVOList());
                    }));
        }
    }

    public void play(String source) {
        view.play(source);
    }


    private void onFailed(Throwable throwable) {
        view.showError(throwable.getMessage());
    }

    public boolean isVideoEmpty() {
        return videoVOS == null || videoVOS.isEmpty();
    }


    public void onSaveInstanceState(Bundle outState){
        if (!isVideoEmpty()) {
            outState.putSerializable(BUNDLE_VIDEOS_VO_KEY, new ArrayList<>(videoVOS));
        }
    }



}

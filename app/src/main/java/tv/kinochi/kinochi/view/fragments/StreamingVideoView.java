package tv.kinochi.kinochi.view.fragments;


import java.util.List;

import tv.kinochi.kinochi.presenter.vo.LinksListVO;
import tv.kinochi.kinochi.presenter.vo.VideoBlockVO;
import tv.kinochi.kinochi.presenter.vo.VideoVO;


public interface StreamingVideoView extends MyView {
    void showSeries(List<LinksListVO> series);
    void showVideoBlock(VideoBlockVO videoBlockVO);
    void play(String source);
}

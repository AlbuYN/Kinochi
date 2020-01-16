package tv.kinochi.kinochi.presenter.vo;

import java.util.List;

public class VideoBlockVO {

    private String title;
    private List<VideoVO> videoVOS;

    public VideoBlockVO(String title, List<VideoVO> videoVOS) {
        this.title = title;
        this.videoVOS = videoVOS;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VideoVO> getVideoVOS() {
        return videoVOS;
    }

    public void setVideoVOS(List<VideoVO> videoVOS) {
        this.videoVOS = videoVOS;
    }
}

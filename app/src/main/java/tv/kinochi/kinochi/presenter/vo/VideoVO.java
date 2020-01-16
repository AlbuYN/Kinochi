package tv.kinochi.kinochi.presenter.vo;

import java.io.Serializable;
import java.util.List;

public class VideoVO implements Serializable {



    private String label;
    private String detail;
    private String groupLabel;
    private List<VideoVO> videoVOList = null;
    private List<LinksListVO> linksList = null;
    private boolean checked = false;
    private int viewType;


    public VideoVO(String label, String groupLabel, List<VideoVO> videoVOList,
                   List<LinksListVO> linksList, String detail) {
        this.label = label;
        this.groupLabel = groupLabel;
        this.videoVOList = videoVOList;
        this.linksList = linksList;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    public List<VideoVO> getVideoVOList() {
        return videoVOList;
    }

    public void setVideoVOList(List<VideoVO> videoVOList) {
        this.videoVOList = videoVOList;
    }

    public List<LinksListVO> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<LinksListVO> linksList) {
        this.linksList = linksList;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}

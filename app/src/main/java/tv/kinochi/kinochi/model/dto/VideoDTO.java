package tv.kinochi.kinochi.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoDTO {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("group_label")
    @Expose
    private String groupLabel;
    @SerializedName("childs")
    @Expose
    private List<VideoDTO> videoDTOList = null;
    @SerializedName("links_list")
    @Expose
    private List<LinksListDTO> linksList = null;

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

    public List<VideoDTO> getVideoDTOList() {
        return videoDTOList;
    }

    public void setVideoDTOList(List<VideoDTO> videoDTOList) {
        this.videoDTOList = videoDTOList;
    }

    public List<LinksListDTO> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<LinksListDTO> linksList) {
        this.linksList = linksList;
    }
}

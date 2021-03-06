package tv.kinochi.kinochi.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemFilmDTO {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("poster_url")
    @Expose
    private String posterUrl;
    @SerializedName("quality")
    @Expose
    private String quality;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}

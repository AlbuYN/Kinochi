package tv.kinochi.kinochi.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlockFiltersDTO {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("values")
    @Expose
    private List<ItemCategoryDTO> itemCategoryDTOList = null;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemCategoryDTO> getItemCategoryDTOList() {
        return itemCategoryDTOList;
    }

    public void setItemCategoryDTOList(List<ItemCategoryDTO> itemCategoryDTOList) {
        this.itemCategoryDTOList = itemCategoryDTOList;
    }
}

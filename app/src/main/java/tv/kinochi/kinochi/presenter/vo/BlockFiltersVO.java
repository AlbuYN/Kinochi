package tv.kinochi.kinochi.presenter.vo;

import java.io.Serializable;
import java.util.List;

public class BlockFiltersVO implements Serializable {

    private String uid;
    private String name;
    private List<ItemCategoryVO> filterVOList = null;

    public BlockFiltersVO(String uid, String name, List<ItemCategoryVO> filterVOList) {
        this.uid = uid;
        this.name = name;
        this.filterVOList = filterVOList;
    }

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

    public List<ItemCategoryVO> getFilterVOList() {
        return filterVOList;
    }

    public void setFilterVOList(List<ItemCategoryVO> filterVOList) {
        this.filterVOList = filterVOList;
    }
}

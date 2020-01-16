package tv.kinochi.kinochi.presenter.vo;

import java.io.Serializable;
import java.util.List;

public class BlockFilmsVO implements Serializable {

    private String uid;
    private String name;
    private List<ItemFilmVO> itemFilmVOList = null;
    private String viewType;


    public BlockFilmsVO(String uid, String name, List<ItemFilmVO> itemFilmVOList, String viewType) {
        this.uid = uid;
        this.name = name;
        this.itemFilmVOList = itemFilmVOList;
        this.viewType = viewType;
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

    public List<ItemFilmVO> getItemFilmVOList() {
        return itemFilmVOList;
    }

    public void setItemFilmVOList(List<ItemFilmVO> itemFilmVOList) {
        this.itemFilmVOList = itemFilmVOList;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}

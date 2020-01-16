package tv.kinochi.kinochi.presenter.vo;

import java.io.Serializable;

public class LinksListVO implements Serializable {

    private String label;
    private String description;
    private String source;


    public LinksListVO(String label, String description, String source) {
        this.label = label;
        this.description = description;
        this.source = source;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

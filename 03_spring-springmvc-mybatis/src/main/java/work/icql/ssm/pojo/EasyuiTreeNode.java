package work.icql.ssm.pojo;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/10/31 17:06
 * @Title EasyuiTreeNode
 * @Description EasyuiTreeNode
 */
public class EasyuiTreeNode {
    private long id;
    private String text;
    private String state;
    private boolean checked;
    private EasyuiTreeNodeAttributes attributes;
    private List<EasyuiTreeNode> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public EasyuiTreeNodeAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(EasyuiTreeNodeAttributes attributes) {
        this.attributes = attributes;
    }

    public List<EasyuiTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyuiTreeNode> children) {
        this.children = children;
    }
}

package com.mipo.test.test1;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class FilterTemplateDTO implements Serializable {

    static final long serialVersionUID = 3960729311513163452L;
    /**
     * 选中当前的
     */
    private String id;
    private String optionName;
//    private String optionVal;
    private String parentId;
    // 动态计算 不需要序列化
    private transient boolean hasChild;
    private transient boolean root;
    private Integer sort;
    private List<FilterTemplateDTO> options;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FilterTemplateDTO)) {
            return false;
        }
        FilterTemplateDTO ow = (FilterTemplateDTO) o;
        return this.id.equals(ow.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getOptionName(), getParentId(), isHasChild(), isRoot(), getOptions());
    }

    /**
     * 是否是根节点  根节点是不可以选中的
     *
     * @return
     */
    public boolean isRoot() {
        return "0".equals(this.parentId);
    }

    /**
     * 判断是否有子选项 的规则为options是否有内容
     *
     * @return
     */
    public boolean isHasChild() {
        return !CollectionUtils.isEmpty(options);
    }


}

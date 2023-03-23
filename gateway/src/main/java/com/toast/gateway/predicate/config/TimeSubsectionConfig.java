package com.toast.gateway.predicate.config;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
public class TimeSubsectionConfig {
    private Set<String> section = new LinkedHashSet<>(); // 按照顺序保存不重复的数据
    public void setSection(List<String> section) {
        this.section.addAll(section);
    }
    public Set<String> getSection() {
        return section;
    }
}

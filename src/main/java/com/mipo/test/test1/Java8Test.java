package com.mipo.test.test1;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Classname Java8Test
 * @Description TODO
 * @Date 2020/9/22 10:18
 * @Created by li.yy
 */
public class Java8Test {

    public static void main(String[] args) {
        List list = CollectionUtil.newArrayList();
        FilterTemplateDTO filterTemplateDTO = new FilterTemplateDTO();
        filterTemplateDTO.setId("1").setOptionName("1haha").setParentId("0");
        filterTemplateDTO.setId("2").setOptionName("2haha").setParentId("0");
        filterTemplateDTO.setId("3").setOptionName("3haha").setParentId("0");
        list.add(filterTemplateDTO);
        String str = "1";
        FilterTemplateDTO fe = new FilterTemplateDTO();
        //fe.setId("222");
        FilterTemplateDTO fe1 = new FilterTemplateDTO();
        System.out.println(Optional.ofNullable(fe).isPresent());
        Optional.ofNullable(fe)
                .ifPresent(t ->
                   fe1.setId(fe.getId())
                );

        System.out.println(fe1.getId());
        //buildTree(list);
        //findByid(null);
    }

    private static List<FilterTemplateDTO> buildTree(List<FilterTemplateDTO> filterTemplateList) {
        // 根节点地图  key为节点id
        Map<String, FilterTemplateDTO> rootMap
                = filterTemplateList.stream().filter(e -> "0".equals(e.getParentId()))
                .collect(Collectors.toMap(FilterTemplateDTO::getId, a -> a, (k1, k2) -> k1));
        // 子节点分组 key为父节点id
        Map<String, List<FilterTemplateDTO>> childMap = filterTemplateList.stream()
                .filter(e -> !"0".equals(e.getParentId()))
                .collect(Collectors.groupingBy(FilterTemplateDTO::getParentId));
        // 缓存二级树 key为节点id
        Map<String, FilterTemplateDTO> cacheChild = new HashMap<>();
        // 二级树构建 遍历根节点下节点
        for (Map.Entry<String, FilterTemplateDTO> entry : rootMap.entrySet()) {
            String id = entry.getKey();
            FilterTemplateDTO value = entry.getValue();
            List<FilterTemplateDTO> options = childMap.get(id);
            value.setOptions(options);
            if (!CollectionUtils.isEmpty(options)) {
                Map<String, FilterTemplateDTO> collect = options.stream()
                        .collect(Collectors.toMap(FilterTemplateDTO::getId, a -> a, (k1, k2) -> k1));
                cacheChild.putAll(collect);
            }
            // 剔除二级节点 剩下就是三级节点 此时key 为二级节点的id
            childMap.remove(id);
        }
        // 三级树构建
        for (Map.Entry<String, List<FilterTemplateDTO>> entry : childMap.entrySet()) {
            String id = entry.getKey();
            List<FilterTemplateDTO> options = entry.getValue();
            if (CollectionUtils.isEmpty(options)) {
                continue;
            }
            FilterTemplateDTO filterTemplate = cacheChild.get(id);
            if (filterTemplate != null) {
                filterTemplate.setOptions(options);
            }
        }
        // 排序
        return new ArrayList<>(rootMap.values()).stream()
                .sorted(Comparator.comparing(FilterTemplateDTO::getSort))
                .collect(Collectors.toList());
    }

    private static void findByid(@NonNull String id) {
        System.out.println(id.trim());
    }
}

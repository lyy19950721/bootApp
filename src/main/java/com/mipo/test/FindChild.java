package com.mipo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname FindChild
 * @Description TODO
 * @Date 2020/6/30 16:53
 * @Created by li.yy
 */
public class FindChild {

    public static void main(String[] args) {
        List<categroy> list = new ArrayList<categroy>();
        list.add(new categroy("1", "0", "¸¸1", "0"));
        list.add(new categroy("2", "0", "¸¸2", "0"));
        list.add(new categroy("3", "1", "×Ó1", "1"));
        list.add(new categroy("4", "1", "×Ó2", "1"));
        list.add(new categroy("5", "1", "×Ó3", "1"));
        list.add(new categroy("6", "3", "×Ó1_1", "2"));
        List<categroy> find = find("0", list);
        for (categroy categroy : find) {
            System.out.println(categroy);
        }
    }
    public static List<categroy> find(String level,List<categroy> list) {
        List<categroy> lists = new ArrayList<>();
        for (categroy categroy : list) {
            if(level.equals(categroy.getCategroylevel())) {
                categroy.setChilds(findChild(categroy.getCategroyid(),list));
                lists.add(categroy);
            }
        }
        return lists;
    }
    public static List<categroy> findChild(String id,List<categroy> list){
        List<categroy> lists = new ArrayList<>();
        for (categroy categroy : list) {
            if(id.equals(categroy.getParentCategroyid())) {
                categroy.setChilds(findChild(categroy.getCategroyid(),list));
                lists.add(categroy);
            }
        }
        return lists;
    }
    static class categroy{

        public categroy(String categroyid, String parentCategroyid, String categroyname, String categroylevel) {
            super();
            this.categroyid = categroyid;
            this.parentCategroyid = parentCategroyid;
            this.categroyname = categroyname;
            this.categroylevel = categroylevel;
        }
        String  categroyid;
        String  parentCategroyid;
        String  categroyname;
        String  categroylevel;
        List<categroy> childs;
        public String getCategroyid() {
            return categroyid;
        }
        public void setCategroyid(String categroyid) {
            this.categroyid = categroyid;
        }
        public String getParentCategroyid() {
            return parentCategroyid;
        }
        public void setParentCategroyid(String parentCategroyid) {
            this.parentCategroyid = parentCategroyid;
        }
        public String getCategroyname() {
            return categroyname;
        }
        public void setCategroyname(String categroyname) {
            this.categroyname = categroyname;
        }
        public String getCategroylevel() {
            return categroylevel;
        }
        public void setCategroylevel(String categroylevel) {
            this.categroylevel = categroylevel;
        }
        public List<categroy> getChilds() {
            return childs;
        }
        public void setChilds(List<categroy> childs) {
            this.childs = childs;
        }
        @Override
        public String toString() {
            return "categroy [categroyid=" + categroyid + ", parentCategroyid=" + parentCategroyid + ", categroyname="
                    + categroyname + ", categroylevel=" + categroylevel + ", childs=" + childs + "]";
        }

    }
}

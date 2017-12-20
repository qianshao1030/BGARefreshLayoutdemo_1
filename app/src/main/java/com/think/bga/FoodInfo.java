package com.think.bga;

import java.util.List;

/**
 * Created by think on 2017/12/20.
 */

public class FoodInfo {

    @Override
    public String toString() {
        return "FoodInfo{" +
                "ret=" + ret +
                ", data=" + data +
                '}';
    }

    /**
     * data : [{"collect_num":"1647","food_str":"大虾 葱 生姜 植物油 料酒","id":"8289","num":1647,"pic":"http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg","title":"油焖大虾"}]
     * ret : 1
     */

    private int ret;
    private List<DataBean> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        @Override
        public String toString() {
            return "DataBean{" +
                    "collect_num='" + collect_num + '\'' +
                    ", food_str='" + food_str + '\'' +
                    ", id='" + id + '\'' +
                    ", num=" + num +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }

        /**
         * collect_num : 1647
         * food_str : 大虾 葱 生姜 植物油 料酒
         * id : 8289
         * num : 1647
         * pic : http://www.qubaobei.com/ios/cf/uploadfile/132/9/8289.jpg
         * title : 油焖大虾
         */

        private String collect_num;
        private String food_str;
        private String id;
        private int num;
        private String pic;
        private String title;

        public String getCollect_num() {
            return collect_num;
        }

        public void setCollect_num(String collect_num) {
            this.collect_num = collect_num;
        }

        public String getFood_str() {
            return food_str;
        }

        public void setFood_str(String food_str) {
            this.food_str = food_str;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

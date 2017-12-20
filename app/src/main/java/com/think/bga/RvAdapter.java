package com.think.bga;

import android.support.v7.widget.RecyclerView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by think on 2017/12/20.
 */

public class RvAdapter extends BGARecyclerViewAdapter<FoodInfo.DataBean> {

    public RvAdapter(RecyclerView recyclerView) {
        super(recyclerView,R.layout.item_rv);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, FoodInfo.DataBean model) {
        helper.setText(R.id.tv,model.getTitle())
                .setText(R.id.tv_food,model.getFood_str())
                .setImageResource(R.id.iv,R.mipmap.ic_launcher_round);
    }

}

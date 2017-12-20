package com.think.bga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demonstrate.DemonstrateUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BGARefreshLayout0Activity extends AppCompatActivity {

    protected RecyclerView rv;
    protected BGARefreshLayout bgaRl;
    //    private String url = "http://www.qubaobei.com/ios/cf/dish_list.php";
    private String url = "http://www.qubaobei.com/ios/cf/dish_list.php/?stage_id=1&limit=10&page=1";
    private Gson gson;
    private RvAdapter adapter;
    private OkHttpClient client;

    /*
    *  setUri("http://www.qubaobei.com/ios/cf/dish_list.php");
                addParameter("stage_id", "1");
                addParameter("limit", "10");
                addParameter("page", page);
    *
    *
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_bgarefresh_layout0);
        gson = new Gson();
        client = new OkHttpClient
                .Builder()
                .build();
        initView();
        request();

    }

    private void request() {
        Request request = new Request
                .Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                DemonstrateUtil.showLogResult(e.toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                DemonstrateUtil.showLogResult(string);
                FoodInfo foodInfo = gson.fromJson(string, FoodInfo.class);
                final List<FoodInfo.DataBean> dataBeans = foodInfo.getData();
                for (int i = 0; i < dataBeans.size(); i++) {
                    DemonstrateUtil.showLogResult(dataBeans.get(i).toString());
                }
                BGARefreshLayout0Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(dataBeans);
                    }
                });
            }
        });
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        bgaRl = (BGARefreshLayout) findViewById(R.id.bga_rl);

        bgaRl.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                //刷新进行的操作
                DemonstrateUtil.showToastResult(BGARefreshLayout0Activity.this,"onBGARefreshLayoutBeginRefreshing");
                refreshLayout.endRefreshing();
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                //加载进行的操作.
                return false;
            }
        });

//        bgaRl.setIsShowLoadingMoreView(false);
//        holder.setLoadingMoreText("慢慢来,加载中...");
//        BGAMeiTuanRefreshViewHolder holder = new BGAMeiTuanRefreshViewHolder(this, false);
//        holder.setLoadMoreBackgroundColorRes(R.color.colorAccent);
//        holder.setRefreshViewBackgroundColorRes(R.color.colorPrimary);
//        holder.setPullDownImageResource(R.mipmap.refresh_head_arrow);
//        holder.setChangeToReleaseRefreshAnimResId(R.mipmap.bga_refresh_loading01);
//        holder.setRefreshingAnimResId(R.mipmap.bga_refresh_loading02);
//        bgaRl.setRefreshViewHolder(holder);

//        holder.setPullDownImageResource(R.mipmap.refresh_head_arrow);


//        bgaRl.setCustomHeaderView(view,false);

//        bgaRl.beginRefreshing();
//        bgaRl.beginLoadingMore();

        BGAStickinessRefreshViewHolder stickinessRefreshViewHolder
                = new BGAStickinessRefreshViewHolder(this, true);
        stickinessRefreshViewHolder.setStickinessColor(R.color.colorPrimary);
        stickinessRefreshViewHolder.setRotateImage(R.mipmap.bga_refresh_stickiness);
        bgaRl.setRefreshViewHolder(stickinessRefreshViewHolder);


        adapter = new RvAdapter(rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}

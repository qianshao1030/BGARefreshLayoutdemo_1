package com.think.bga;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demonstrate.DemonstrateUtil;
import com.google.gson.Gson;

import java.util.List;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGAStickinessRefreshViewHolder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BGARefreshLayout2Activity extends AppCompatActivity {

    protected RecyclerView rv;
    protected BGARefreshLayout bgaRl;
    //    private String url = "http://www.qubaobei.com/ios/cf/dish_list.php";
    private String url = "http://www.qubaobei.com/ios/cf/dish_list.php/?stage_id=1&limit=10&page=1";
    private Gson gson;
    private RvAdapter adapter;
    private OkHttpClient client;
    private String baseUrl = "http://www.qubaobei.com/";
    private String urlAll ="http://www.qubaobei.com/ios/cf/dish_list.php/?stage_id=1&limit=10&page=1";

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

        Observable.create(new ObservableOnSubscribe<List<FoodInfo.DataBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<FoodInfo.DataBean>> e) throws Exception {
                //发射数据的逻辑.
                Response<FoodInfo> response = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
                        .create(NetInterface.class)
                        .getDataFromNet2(urlAll)
                        .execute();
                FoodInfo foodInfo = response.body();
                List<FoodInfo.DataBean> dataBeans = foodInfo.getData();
                e.onNext(dataBeans);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FoodInfo.DataBean>>() {
            @Override
            public void accept(List<FoodInfo.DataBean> dataBeans) throws Exception {
                //执行更UI的操作.
                for (FoodInfo.DataBean info : dataBeans){
                    DemonstrateUtil.showLogResult(info.toString());
                }
                adapter.setData(dataBeans);
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
                DemonstrateUtil.showToastResult(BGARefreshLayout2Activity.this,"onBGARefreshLayoutBeginRefreshing");
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

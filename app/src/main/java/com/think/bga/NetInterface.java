package com.think.bga;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

/**
 * Created by think on 2017/12/20.
 */

public interface NetInterface {

    @GET("ios/cf/dish_list.php/")
    Call<FoodInfo> getDataFromNet(@QueryName HashMap<String,String> map);

    @GET()
    Call<FoodInfo> getDataFromNet2(@Url String url);
}

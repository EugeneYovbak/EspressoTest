package com.boost.espressotest.screen.presenter;


import com.boost.espressotest.api.RetrofitModule;
import com.boost.espressotest.base.BasePresenter;
import com.boost.espressotest.model.ApiResponse;
import com.boost.espressotest.model.Product;
import com.boost.espressotest.screen.view.MainView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private Call<ApiResponse<List<Product>>> mProductListCall;
    private MainView mMainView;

    public void getProductList(String query, String where, int page) {
        mProductListCall = RetrofitModule.getApiInterface().loadAllProductsByQuery(query, where, page);
        mProductListCall.enqueue(new Callback<ApiResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Product>>> call, Response<ApiResponse<List<Product>>> response) {

            }

            @Override
            public void onFailure(Call<ApiResponse<List<Product>>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mProductListCall != null) mProductListCall.cancel();
    }
}

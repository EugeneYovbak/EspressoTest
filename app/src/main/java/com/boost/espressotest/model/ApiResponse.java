package com.boost.espressotest.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author PerSpiKyliaTor on 11.01.18.
 */

public class ApiResponse<T> {
    @SerializedName("result")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
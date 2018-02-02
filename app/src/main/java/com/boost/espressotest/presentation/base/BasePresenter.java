package com.boost.espressotest.presentation.base;

public class BasePresenter<T extends BaseView> {

    protected T mView;

    public void onAttach(T view) {
        mView = view;
    }

    public void onDetach() {
        mView = null;
    }
}

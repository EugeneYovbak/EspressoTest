package com.boost.espressotest.presentation.base

open class BasePresenter<T : BaseView> {

    protected var view: T? = null

    fun onAttach(view: T) {
        this.view = view
    }

    open fun onDetach() {
        view = null
    }
}

package com.boost.espressotest.presentation.screen.detail.presenter;


import com.boost.espressotest.domain.ProductRepository;
import com.boost.espressotest.presentation.screen.detail.view.DetailView;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;

@RunWith(JUnit4.class)
public class DetailPresenterTest {

    private DetailPresenter mDetailPresenter;

    @Mock
    private ProductRepository mProductRepository;

    @Mock
    private DetailView mDetailView;


}

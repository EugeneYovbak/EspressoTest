package com.boost.espressotest.screen.second.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.boost.espressotest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author PerSpiKyliaTor on 17.01.18.
 */

public class SecondActivity extends AppCompatActivity {

    public static final String ARG_INPUT = "ARG_INPUT";

    @BindView(R.id.tv_result) TextView mResultTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            String input = getIntent().getExtras().getString(ARG_INPUT);
            mResultTextView.setText(input);
        }
    }
}

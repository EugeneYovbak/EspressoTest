package com.boost.espressotest.screen.main.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.boost.espressotest.R;
import com.boost.espressotest.screen.second.view.SecondActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_input) EditText mInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_example)
    void onNewClick() {
        mInputEditText.setText("Sample Text");
    }

    @OnClick(R.id.btn_switch)
    void onChangeClick() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(SecondActivity.ARG_INPUT, mInputEditText.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.btn_toast)
    void onShowToastClick() {
        Toast.makeText(this, "Sample Toast", Toast.LENGTH_SHORT).show();
    }
}

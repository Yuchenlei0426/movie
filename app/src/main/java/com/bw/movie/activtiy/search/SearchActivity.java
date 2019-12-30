package com.bw.movie.activtiy.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.search.SearchAdper;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findMovieByKeyword.ByKeywordResult;
import com.bw.movie.bean.findMovieByKeyword.ByKeywordShow;
import com.bw.movie.prentent.findMovieByKeyword.MovieByKeywordPreantent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search_word)
    EditText etSearchWord;
    @BindView(R.id.re_search)
    RecyclerView reSearch;

//    private String encode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        etSearchWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“搜索”键*/
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String key = etSearchWord.getText().toString().trim();
                    if (!TextUtils.isEmpty(key)) {
                        MovieByKeywordPreantent movieByKeywordPreantent = new MovieByKeywordPreantent(new SearchCall());
                        movieByKeywordPreantent.getData(key, 1, 10);
                    } else {

                    }

                    return true;
                }
                return false;
            }
        });


    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private class SearchCall implements IBackCall<ByKeywordShow> {
        @Override
        public void onSuccess(ByKeywordShow homeShow) {
            List<ByKeywordResult> result = homeShow.getResult();
            if (result != null) {
                SearchAdper searchAdper = new SearchAdper();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
                reSearch.setLayoutManager(linearLayoutManager);
                searchAdper.addAll(result);
                reSearch.setAdapter(searchAdper);
            }else {
                Toast.makeText(SearchActivity.this, "未查到相关电影", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFail(String mes) {
            Log.e(TAG, "onFail: "+mes);
        }
    }
}

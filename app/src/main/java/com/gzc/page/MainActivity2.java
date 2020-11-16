package com.gzc.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gzc.page.paging.UserPagedListAdapter;
import com.gzc.page.paging.MessageListViewModel;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    private UserPagedListAdapter mPositionalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerView = findViewById(R.id.data_list_view);

        initPositionalAdapter();

        initPositionalObserve();
    }

    private void initPositionalAdapter() {
        mPositionalAdapter = new UserPagedListAdapter(this);
        mRecyclerView.setAdapter(mPositionalAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    private void initPositionalObserve() {
        MessageListViewModel viewModel = new ViewModelProvider(this).get(MessageListViewModel.class);
        viewModel.userPagedList.observe(this, new Observer<PagedList<MessageListBean.DataBean>>() {
            @Override
            public void onChanged(PagedList<MessageListBean.DataBean> userInfoPagedList) {
                mPositionalAdapter.submitList(userInfoPagedList);
            }
        });
    }
}
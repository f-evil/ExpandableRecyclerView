package com.easy.expandablerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.easy.expandablerecyclerview.bean.CarBrandBean;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvP;

    private XAdapter mXAdapter;
    private List<CarBrandBean> mDatas;

    private TextView mTvA;
    private TextView mTvS;
    private TextView mTvQ;
    private TextView mTvT;
    private TextView mTvF;
    private TextView mTvM;
    private TextView mTvZ;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRvP = (RecyclerView) findViewById(R.id.rv_p);
        linearLayoutManager = new LinearLayoutManager(this);
        mRvP.setLayoutManager(linearLayoutManager);
        mXAdapter = new XAdapter(this);

        mRvP.setAdapter(mXAdapter);

        mDatas = new DataSource().getData();
        mXAdapter.setDatas(mDatas);

        mRvP.addItemDecoration(new DividerItemDecoration(this, new DividerItemDecoration.OnGroupListener() {
            @Override
            public String getGroupName(int position) {

                String xx = "";

                try {
                    xx = mXAdapter.getDatas().get(position).getTag();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return xx;
            }
        }));

        mTvA = (TextView) findViewById(R.id.tv_a);
        mTvS = (TextView) findViewById(R.id.tv_s);
        mTvQ = (TextView) findViewById(R.id.tv_q);
        mTvT = (TextView) findViewById(R.id.tv_t);
        mTvF = (TextView) findViewById(R.id.tv_f);
        mTvM = (TextView) findViewById(R.id.tv_m);
        mTvZ = (TextView) findViewById(R.id.tv_z);

        setClick(mTvA);
        setClick(mTvS);
        setClick(mTvQ);
        setClick(mTvT);
        setClick(mTvF);
        setClick(mTvM);
        setClick(mTvZ);
    }

    private void setClick(final TextView tv) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoTag(tv);
            }
        });
    }


    private void gotoTag(TextView tv) {

        String tag = tv.getText().toString();

        int pos = -1;
        List<CarBrandBean> datas = mXAdapter.getDatas();
        for (int i = 0; i < datas.size(); i++) {
            CarBrandBean carBrandBean = datas.get(i);
            if (carBrandBean.getTag().equals(tag)) {
                pos = i;
                break;
            }
        }

        if (pos > 0) {
            linearLayoutManager.scrollToPositionWithOffset(pos, 0);
        }


    }


}

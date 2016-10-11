package com.lanou3g.dllo.athm.controler.fragment;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.lanou3g.dllo.athm.R;
import com.lanou3g.dllo.athm.controler.activity.MeCollectAty;
import com.lanou3g.dllo.athm.controler.adapter.listview_adapter.MeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/9/9.
 * 我
 */
public class MeFragment extends AbsBaseFragment {
    private ListView listView;
    private MeAdapter adapter;
    private List<String> datas;

    @Override
    protected int setLayout() {
        return R.layout.me_fragment;
    }

    @Override
    protected void initView() {
      listView = byview(R.id.me_list_view);
    }

    @Override
    protected void initDatas() {
        adapter = new MeAdapter(context);
        datas = new ArrayList<>();
        datas.add("兑换商城");
        datas.add("我的优惠券");
        datas.add("我的收藏");
        datas.add("浏览历史");
        datas.add("草稿箱");
        datas.add("设置");
        adapter.setDatas(datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent intent = new Intent(context, MeCollectAty.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


}

package com.huamei.facialmaskmarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.huamei.facialmaskmarket.R;
import com.huamei.facialmaskmarket.activity.LoginActivity;
import com.huamei.facialmaskmarket.bean.MyPagerBean;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class MyFragment extends Fragment implements View.OnClickListener{

    private ArrayList<MyPagerBean> list;
    private ListView listView;
    private ImageButton ib_log;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_pager_layout,null);
   initView(view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        list = new ArrayList<>();
        MyPagerBean myDingDan=new MyPagerBean("我的订单",R.mipmap.my_dai_shou_huo);
        MyPagerBean yaoQing=new MyPagerBean("邀请有礼",R.mipmap.my_dai_fa_huo);
        MyPagerBean shulian=new MyPagerBean("刷脸测尺寸",R.mipmap.my_pager_shualian);
        MyPagerBean duihuan=new MyPagerBean("兑换专区",R.mipmap.my_dai_fu_kuai);
        MyPagerBean xianjinquan=new MyPagerBean("我的代金券",R.mipmap.my_tui_kuai);
        MyPagerBean choujiang=new MyPagerBean("我的抽奖单",R.mipmap.my_dai_ping_jia);
        MyPagerBean shoucang=new MyPagerBean("我的收藏",R.mipmap.my_pager_shoucang);
        MyPagerBean lianxi =new MyPagerBean("联系客服",R.mipmap.my_tui_kuai);
        list.add(myDingDan);
        list.add(yaoQing);
        list.add(shulian);
        list.add(duihuan);
        list.add(xianjinquan);
        list.add(choujiang);
        list.add(shoucang);
        list.add(lianxi);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);


    }

    //初始化控件
    private void initView(View view){
        ib_log = (ImageButton) view.findViewById(R.id.my_pager_ib_log);
        TextView tv_dfk = (TextView) view.findViewById(R.id.my_pager_tv_dfk);
        TextView tv_dfh = (TextView) view.findViewById(R.id.my_pager_tv_dfh);
        TextView tv_dsh = (TextView) view.findViewById(R.id.my_pager_tv_dsh);
        TextView tv_dpj = (TextView) view.findViewById(R.id.my_pager_tv_dpj);
        TextView tv_tuk = (TextView) view.findViewById(R.id.my_pager_tv_tuk);
        listView = (ListView) view.findViewById(R.id.my_pager_list);
        ib_log.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_pager_ib_log:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }


    //适配器
     private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(getActivity(),R.layout.my_pager_list_item_lay,null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.my_pager_lv_it_image);
            TextView textView = (TextView) convertView.findViewById(R.id.my_pager_lv_it_title);
            imageView.setImageResource(list.get(position).getPicId());
            textView.setText(list.get(position).getTitle());
            return convertView;
        }
    }

}

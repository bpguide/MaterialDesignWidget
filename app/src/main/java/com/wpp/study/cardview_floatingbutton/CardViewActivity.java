package com.wpp.study.cardview_floatingbutton;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wpp.study.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CardViewActivity extends AppCompatActivity {

    @BindView(R.id.activity_cardview_layout_recyclerview)
    RecyclerView mRecyclerView;
    private Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview_layout);
        unbinder = ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CardViewAdapter(this, getData()));
    }

    private ArrayList<MyData> getData(){
        ArrayList<MyData> datas = new ArrayList<>();

        MyData data = new MyData();
        data.name = "路飞";
        data.sex = "男";
        data.bg_url = "http://image161.poco.cn/mypoco/myphoto/20100508/10/54712954201005081002054000850829937_013.jpg";
        data.head_url = "http://g.hiphotos.baidu.com/zhidao/wh%3D600%2C800/sign=8cd407974a90f60304e5944109229f23/9e3df8dcd100baa1cb47cc1d4710b912c8fc2e28.jpg";
        datas.add(data);

        data = new MyData();
        data.name = "索隆";
        data.sex = "男";
        data.bg_url = "http://img.zcool.cn/community/015ad455434fe70000019ae9f97a7e.jpg";
        data.head_url = "http://www.wangmingdaquan.cc/tx71/160.jpg";
        datas.add(data);

        data = new MyData();
        data.name = "艾斯";
        data.sex = "男";
        data.bg_url = "http://image.sheyuan.com/img/2016/06/13/04/e52aabc46f5e42f8877d17ab4c2119de.jpg";
        data.head_url = "http://img2.imgtn.bdimg.com/it/u=891779053,2266412575&fm=26&gp=0.jpg";
        datas.add(data);

        data = new MyData();
        data.name = "罗";
        data.sex = "男";
        data.bg_url = "http://img4.imgtn.bdimg.com/it/u=3418498991,2004516669&fm=26&gp=0.jpg";
        data.head_url = "http://img2.imgtn.bdimg.com/it/u=2632182871,1164978145&fm=26&gp=0.jpg";
        datas.add(data);

        data = new MyData();
        data.name = "香克斯";
        data.sex = "男";
        data.bg_url = "http://image161.poco.cn/mypoco/myphoto/20100405/17/54693277201004051719011342311778635_008_640.jpg";
        data.head_url = "http://img2.imgtn.bdimg.com/it/u=971675398,1983815098&fm=26&gp=0.jpg";
        datas.add(data);

        data = new MyData();
        data.name = "乔巴";
        data.sex = "男";
        data.bg_url = "https://oi5mmhyk8.qnssl.com/20/1363664730606_w20510.jpg!bigger";
        data.head_url = "http://img1.imgtn.bdimg.com/it/u=4257690447,1884386906&fm=26&gp=0.jpg";
        datas.add(data);

        data = new MyData();
        data.name = "罗宾";
        data.sex = "女";
        data.bg_url = "http://img-cdn.hopetrip.com.hk/news4/8D31BD2C254E614B/9/91E48C726DB931250FB1.jpg";
        data.head_url = "http://img.qq1234.org/uploads/allimg/140701/3_140701150032_13.jpg";
        datas.add(data);

        return datas;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

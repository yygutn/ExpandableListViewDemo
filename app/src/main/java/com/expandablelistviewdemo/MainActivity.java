package com.expandablelistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mTopBar;
    private ExpandableListView mExListView;

    Adapter adapter = null;


    List<GroupItem> mGroupList = null;
    List<List<ChildItem>> mChildList = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        initData();
        adapter = new Adapter(this,mGroupList,mChildList);
        mExListView.setAdapter(adapter);
        //set click delegate
        mExListView.setOnChildClickListener(new onChildItemClickDelegate());
        mExListView.setOnGroupClickListener(new onGroupItemClickDelegate());
    }

    private void initData() {
        mGroupList = new ArrayList<>();
        mGroupList.add(new GroupItem("我的设备"));
        mGroupList.add(new GroupItem("我的好友"));
        mGroupList.add(new GroupItem("大学"));

        mChildList = new ArrayList<>();
        List<ChildItem> child = null;
        ChildItem childItem = null;
        child = new ArrayList<ChildItem>();
        childItem = new ChildItem(R.drawable.contacts, "我的电脑", "[在线]无需数据线，手机轻松传文件到电脑");
        child.add(childItem);
        childItem = new ChildItem(R.drawable.contacts, "我的打印机", "将手机文件或照片发送到电脑连接的打印机");
        child.add(childItem);
        mChildList.add(child);

        child = new ArrayList<ChildItem>();
        childItem = new ChildItem(R.drawable.contacts, "自行车", "[在线]阿萨德飞规划局");
        child.add(childItem);
        childItem = new ChildItem(R.drawable.contacts, "阿什顿", "[在线]分工会尽快");
        child.add(childItem);
        childItem = new ChildItem(R.drawable.contacts, "请问", "[离线请留言]");
        child.add(childItem);
        mChildList.add(child);

        child = new ArrayList<ChildItem>();
        childItem = new ChildItem(R.drawable.contacts, "阿斯顿", "[在线]风格化");
        child.add(childItem);
        childItem = new ChildItem(R.drawable.contacts, "立刻回家", "[在线]体育");
        child.add(childItem);
        childItem = new ChildItem(R.drawable.contacts, "为萨达", "[离线请留言]");
        child.add(childItem);
        mChildList.add(child);
    }

    private void assignViews() {
        mTopBar = (Toolbar) findViewById(R.id.topBar);
        mExListView = (ExpandableListView) findViewById(R.id.exListView);
    }
    class onGroupItemClickDelegate implements ExpandableListView.OnGroupClickListener{

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            Toast.makeText(MainActivity.this,"adapter.getChildrenCount(groupPosition):" +
                    adapter.getChildrenCount(groupPosition), Toast.LENGTH_SHORT).show();
            return false;//返回为1则二级列表不能打开
        }
    }

    class onChildItemClickDelegate implements ExpandableListView.OnChildClickListener{

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            final ChildItem item = adapter.mChildList.get(groupPosition).get(childPosition);
            Toast.makeText(MainActivity.this, "点击了" + item.getName(), Toast.LENGTH_SHORT).show();
            return true;//事件处理完毕，不需要向下传递了，如果需要，返回false
        }
    }
}

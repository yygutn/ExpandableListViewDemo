package com.expandablelistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jumy on 16/1/6.
 * 多级列表适配器
 */
public class Adapter extends BaseExpandableListAdapter {

    private Context mContext = null;
    List<GroupItem> mGroupList = null;
    List<List<ChildItem>> mChildList = null;
    private LayoutInflater mInflater = null;

    public Adapter(Context mContext, List<GroupItem> mGroupList, List<List<ChildItem>> mChildList) {
        this.mContext = mContext;
        this.mGroupList = mGroupList;
        this.mChildList = mChildList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getGroupCount() {
        if (mGroupList == null)
            return 0;
        return mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mChildList.get(groupPosition) == null)
            return 0;
        return mChildList.get(groupPosition).size();
    }

    @Override
    public List<ChildItem> getGroup(int groupPosition) {
        return mChildList.get(groupPosition);
    }

    @Override
    public ChildItem getChild(int groupPosition, int childPosition) {
        return mChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.groupitem, null);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        final GroupItem item = mGroupList.get(groupPosition);
        holder.mGroupCount.setText("[" + mChildList.get(groupPosition).size() + "]");
        holder.mGroupName.setText(item.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.childitem, null);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        final ChildItem item = mChildList.get(groupPosition).get(childPosition);
        holder.mIcon.setBackgroundResource(item.getId());
        holder.mChildName.setText(item.getName());
        holder.mContent.setText(item.getContent());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupViewHolder {
        TextView mGroupName;
        TextView mGroupCount;

        public GroupViewHolder(View view) {
            mGroupName = (TextView) view.findViewById(R.id.group_name);
            mGroupCount = (TextView) view.findViewById(R.id.group_count);
        }
    }

    private class ChildViewHolder {
        ImageView mIcon;
        TextView mChildName;
        TextView mContent;

        public ChildViewHolder(View view) {
            mChildName = (TextView) view.findViewById(R.id.item_name);
            mContent = (TextView) view.findViewById(R.id.item_content);
            mIcon = (ImageView) view.findViewById(R.id.img);
        }
    }
}

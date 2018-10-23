package com.easy.expandablerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easy.expandablerecyclerview.bean.CarBrandBean;

import java.util.List;

/**
 * @author fuyujie
 * @package: com.easy.expandablerecyclerview
 * @fileNmae XAdapter
 * @date 2018/10/22 16:16
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */
public class XAdapter extends RecyclerView.Adapter {

    private static final int GROUP_VIEW = 1;
    private static final int CHILD_VIEW = 2;

    private List<CarBrandBean> datas;

    private Context mContext;

    public XAdapter(Context context) {
        mContext = context;
    }

    public List<CarBrandBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CarBrandBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (datas.get(position).isGroup()) {
            return GROUP_VIEW;
        } else {
            return CHILD_VIEW;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == GROUP_VIEW) {
            return new GroupHolder(getView(viewGroup, R.layout.group_item));
        } else {
            return new ChildHolder(getView(viewGroup, R.layout.child_item));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(null);

        int itemViewType = holder.getItemViewType();

        if (itemViewType == GROUP_VIEW) {
            final GroupHolder groupHolder = (GroupHolder) holder;
            final CarBrandBean carBean = datas.get(position);
            if (carBean.isExpand()) {
                groupHolder.mTextView.setText(carBean.getName() + "--->打开");
            } else {
                groupHolder.mTextView.setText(carBean.getName() + "--->关闭");
            }

            groupHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (carBean.isExpand()) {
                        groupHolder.mTextView.setText(carBean.getName() + "--->关闭");
                        collapse(position, carBean);
                    } else {
                        groupHolder.mTextView.setText(carBean.getName() + "--->打开");
                        expand(position, carBean);
                    }
                }
            });
        } else {
            ChildHolder childHolder = (ChildHolder) holder;
            CarBrandBean carBean = datas.get(position);
            childHolder.mTextView.setText(carBean.getName());
            childHolder.itemView.setOnClickListener(null);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class GroupHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        GroupHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_title);
        }
    }

    class ChildHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        ChildHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_title);
        }
    }

    private View getView(ViewGroup viewGroup, int view) {
        View view1 = LayoutInflater.from(mContext).inflate(view, viewGroup, false);
        return view1;
    }

    public void expand(int position, CarBrandBean brandBean) {
        List<CarBrandBean> series = brandBean.getSeries();
        if (series == null || series.size() == 0) {
            return;
        }
        brandBean.setExpand(true);

        datas.addAll(position + 1, series);

        //无动画
        notifyDataSetChanged();
        //有动画
       // notifyItemChanged(position);
       // notifyItemRangeInserted(position + 1, childSize);

    }

    /**
     * 闭合
     */
    public void collapse(int position, CarBrandBean brandBean) {
        List<CarBrandBean> series = brandBean.getSeries();
        if (series == null || series.size() == 0) {
            return;
        }

        brandBean.setExpand(false);

        //移除子选项,必须从后往前删
        int childSize = series.size();
        for (int i = datas.size() - 1; i >= 0; i--) {
            if (brandBean.getName().equals(datas.get(i).getParent())) {
                    datas.remove(i);
            }
        }

        //无动画
        notifyDataSetChanged();
        //有动画
        //notifyItemChanged(position);
        //notifyItemRangeRemoved(position + 1, childSize);

    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isGroup()) {
                sb.append("------------" + "\n");
            }
            sb.append(datas.get(i).getName() + "\n");
        }

        Log.e("datas", sb.toString());

    }
}

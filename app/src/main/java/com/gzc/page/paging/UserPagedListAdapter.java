package com.gzc.page.paging;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gzc.page.MessageListBean;
import com.gzc.page.R;
import com.gzc.page.utils.DensityUtil;
import com.gzc.page.utils.TimeTools;

import java.text.SimpleDateFormat;

/**
 * author：gzc
 * date：2020/11/16
 * describe：
 */
public class UserPagedListAdapter extends PagedListAdapter<MessageListBean.DataBean, UserPagedListAdapter.UserItemViewHolder> {
    private Context mContext;

    public UserPagedListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mContext = context;
    }


    private static DiffUtil.ItemCallback<MessageListBean.DataBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<MessageListBean.DataBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull MessageListBean.DataBean oldItem, @NonNull MessageListBean.DataBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MessageListBean.DataBean oldItem, @NonNull MessageListBean.DataBean newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_new_notify, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        //TODO
        MessageListBean.DataBean item = getItem(position);
        holder.setData(item,position,mContext);
    }

    static class UserItemViewHolder extends RecyclerView.ViewHolder {
        private TextView messageTitleView;
        private TextView timeView;
        private TextView contentView;
        private View redPointView;


        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);

            messageTitleView = (TextView)itemView. findViewById(R.id.message_title_view);
            timeView = (TextView)itemView. findViewById(R.id.time_view);
            contentView = (TextView)itemView. findViewById(R.id.content_view);
            redPointView = (View)itemView. findViewById(R.id.red_point_view);
        }

        public void setData(MessageListBean.DataBean dataBean,int position,Context context){
            messageTitleView.setText(dataBean.getTitle());
            contentView.setText(dataBean.getContent());
//            timeView.setText(TimeTools.getPublishFormat(dataBean.getCreatetime() * 1000));
//            timeView.setText(TimeTools.getYMD(new Date(dataBean.getCreatetime() * 1000)));
            timeView.setText(TimeTools.formatData(dataBean.getCreatetime() * 1000,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
            if(position==0){
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.topMargin = DensityUtil.dip2px(context,10);
                itemView.setLayoutParams(layoutParams);
            }

            if(dataBean.getIsread()==1){
                messageTitleView.setTextColor(ContextCompat.getColor(context,R.color.color_aaaaaa));
                timeView.setTextColor(ContextCompat.getColor(context,R.color.color_aaaaaa));
                contentView.setTextColor(ContextCompat.getColor(context,R.color.color_aaaaaa));
                redPointView.setVisibility(View.GONE);
            }else{
                messageTitleView.setTextColor(ContextCompat.getColor(context,R.color.color_333333));
                timeView.setTextColor(ContextCompat.getColor(context,R.color.color_aaaaaa));
                contentView.setTextColor(ContextCompat.getColor(context,R.color.color_666666));
                redPointView.setVisibility(View.VISIBLE);
            }
        }
    }
}

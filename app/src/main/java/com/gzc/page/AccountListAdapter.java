package com.gzc.page;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gzc.page.room.Account;

/**
 * User: Administrator
 * Date: 2020-11-15 20:52
 * Describe:
 */
public class AccountListAdapter extends PagedListAdapter<Account, AccountListAdapter.ViewHolder> {

    protected AccountListAdapter() {
        super(new DiffUtil.ItemCallback<Account>() {
            @Override
            public boolean areItemsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Account oldItem, @NonNull Account newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.account_list_item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Account account = getItem(position);
        holder.idView.setText(account.getId()+"");
        holder.nameView.setText(account.getName());
        holder.timeView.setText(account.getFormatCreateTime());
        holder.addressView.setText(account.getAddress());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idView;
        private TextView nameView;
        private TextView timeView;
        private TextView addressView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idView = (TextView) itemView.findViewById(R.id.id_view);
            nameView = (TextView) itemView.findViewById(R.id.name_view);
            timeView = (TextView) itemView.findViewById(R.id.time_view);
            addressView = (TextView) itemView.findViewById(R.id.address_view);
        }
    }
}

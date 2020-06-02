package znyoo.name.baseproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseDataBindingRecyclerViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public static BaseDataBindingRecyclerViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewDataBinding binding = DataBindingUtil.bind(itemView);
        BaseDataBindingRecyclerViewHolder holder = new BaseDataBindingRecyclerViewHolder(binding);
        return holder;
    }

    private BaseDataBindingRecyclerViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    //得到databinding
    public <T extends ViewDataBinding> T getBinding() {
        return (T)binding;
    }

}

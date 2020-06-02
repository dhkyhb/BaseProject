package znyoo.name.baseproject.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private int position;

    private ViewDataBinding binding;

    public void updataPosition(int i) {
        this.position = i;
    }

    public int getPositions() {
        return this.position;
    }

    public View getmConvertView() {
        return mConvertView;
    }

    public static BaseRecyclerViewHolder get(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        ViewDataBinding binding = DataBindingUtil.bind(itemView);
        BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(context, parent, binding);
        return holder;
    }

    public BaseRecyclerViewHolder(Context context, ViewGroup parent, @Nullable ViewDataBinding binding) {
        super(binding.getRoot());
        mContext = context;
        mConvertView = binding.getRoot();
        mViews = new SparseArray<View>();
        this.binding = binding;
    }


    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public BaseRecyclerViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecyclerViewHolder setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    public BaseRecyclerViewHolder setProgress(int viewId, int progress) {
        ProgressBar tv = getView(viewId);
        tv.setProgress(progress);
        return this;
    }

    public BaseRecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }


    /**
     * 关于事件的
     */
    public BaseRecyclerViewHolder setOnClickListener(int viewId,
                                                     View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public BaseRecyclerViewHolder setOnTouchListener(int viewId,
                                                     View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public BaseRecyclerViewHolder setOnLongClickListener(int viewId,
                                                         View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }


    //得到databinding
    public <T extends ViewDataBinding> T getBinding() {
        return (T)binding;
    }

}

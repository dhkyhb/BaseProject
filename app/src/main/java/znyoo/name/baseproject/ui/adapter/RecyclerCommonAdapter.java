package znyoo.name.baseproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*

        RecyclerView rec = findViewById(R.id.rec);
        List<String> strings = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            strings.add("测试 " + i);
        }
        //设置RecyclerView管理器
//        rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        rec.setLayoutManager(new GridLayoutManager(this, 3));
        rec.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        //添加 item 间隔
//        rec.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rec.addItemDecoration(new DividerGridViewItemDecoration(this));


//设置添加或删除item时的动画，这里使用默认动画
        rec.setItemAnimator(new DefaultItemAnimator());


//初始化适配器
        MyRecyclerViewAdapter mAdapter = new MyRecyclerViewAdapter(strings);

        RecyclerCommonAdapter<String> stringRecyclerCommonAdapter = new RecyclerCommonAdapter<String>(this, R.layout.item_normal, strings) {

            @Override
            public void updataUI(RecyclerViewHolder recyclerViewHolder, String s) {
                recyclerViewHolder.setText(R.id.item_tx, "更新" + s);
            }
        };
//设置适配器
        rec.setAdapter(stringRecyclerCommonAdapter);


 */
public abstract class RecyclerCommonAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected OnClickListener itemOnClickListener;
    protected OnLongClickListener itemOnLongClickListener;

    public void setItemOnClickListener(OnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    public void setItemOnLongClickListener(OnLongClickListener itemOnLongClickListener) {
        this.itemOnLongClickListener = itemOnLongClickListener;
    }

    public RecyclerCommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }



    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        BaseRecyclerViewHolder recyclerViewHolder = BaseRecyclerViewHolder.get(this.mContext, viewGroup, this.mLayoutId);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder recyclerViewHolder, final int i) {
        recyclerViewHolder.updataPosition(i);
        final T t = mDatas.get(i);
        recyclerViewHolder.getmConvertView().setOnClickListener(view -> {
            if (RecyclerCommonAdapter.this.itemOnClickListener != null) {
                RecyclerCommonAdapter.this.itemOnClickListener.onClick(view, t, i);
            }
        });
        recyclerViewHolder.getmConvertView().setOnLongClickListener(view -> {
            if (RecyclerCommonAdapter.this.itemOnLongClickListener != null) {
                return RecyclerCommonAdapter.this.itemOnLongClickListener.onLongClick(view, t, i);
            }
            return false;
        });
        updataUI(recyclerViewHolder, mDatas.get(i));
    }

    public abstract void updataUI(BaseRecyclerViewHolder recyclerViewHolder, T t);

    @Override
    public int getItemCount() {
        return getmDatas().size();
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public int getmLayoutId() {
        return mLayoutId;
    }

    public void setmLayoutId(int mLayoutId) {
        this.mLayoutId = mLayoutId;
    }

    public List<T> getmDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public interface OnClickListener<T> {
        void onClick(View view, T t, int i);
    }

    public interface OnLongClickListener<T> {
        boolean onLongClick(View view, T t, int i);
    }

}

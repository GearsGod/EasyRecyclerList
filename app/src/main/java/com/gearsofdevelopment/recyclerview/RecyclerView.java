package com.gearsofdevelopment.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RecyclerView extends ConstraintLayout {

    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private FloatingActionButton button;

    private final androidx.recyclerview.widget.RecyclerView.OnScrollListener onScrollListener = new androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull androidx.recyclerview.widget.RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (button == null) {
                return;
            }
            boolean isVisible = dy == 0 || dy < 0;
            if (!isVisible) {
                if (button.getVisibility() == GONE) {
                    return;
                }
            } else {
                if (button.getVisibility() == VISIBLE) {
                    return;
                }
            }
            button.setVisibility(isVisible ? VISIBLE : GONE);
        }
    };

    public RecyclerView(@NonNull Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_main, this, false);
        recyclerView = view.findViewById(R.id.rv_main);
        button = view.findViewById(R.id.btn);
        recyclerView.addOnScrollListener(onScrollListener);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecyclerView, defStyleAttr, defStyleRes);
        int icon = a.getResourceId(R.styleable.RecyclerView_buttonIcon, R.drawable.ic_baseline_add_24);
        setIcon(icon);
        a.recycle();
        addView(view);
    }

    public void setIcon(@DrawableRes int src) {
        button.setImageResource(src);
    }

    public void setAdapter(Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public static abstract class Adapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView.ViewHolder<?>> {

        private List<?> list;

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setData(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }

        public void update(List<?> list) {
            this.list = list;
            notifyDataSetChanged();
        }

    }

    public abstract static class ViewHolder<T> extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

        public ViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
            ((ViewGroup) itemView).addView(LayoutInflater.from(parent.getContext()).inflate(loadView(), parent, false));
            init(itemView);
        }

        @LayoutRes
        public abstract int loadView();

        public abstract void init(View itemView);

        public abstract void setData(T t);
    }

}

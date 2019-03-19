package com.awakers.stillalive.ui.theme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.databinding.ThemeItem;
import java.util.Vector;

public class ThemeAdapter extends Adapter<ThemeViewHolder> {
    private Context context;
    private Vector<ThemeItemModel> list;

    static final /* synthetic */ void lambda$onBindViewHolder$0$ThemeAdapter(View view) {
    }

    public ThemeAdapter(Context context, Vector<ThemeItemModel> vector) {
        this.context = context;
        this.list = vector;
    }

    @NonNull
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ThemeViewHolder(ThemeItem.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ThemeViewHolder themeViewHolder, int i) {
        themeViewHolder.bind((ThemeItemModel) this.list.get(i));
        themeViewHolder.getItem().setHandler(ThemeAdapter$$Lambda$0.$instance);
    }

    public int getItemCount() {
        return this.list.size();
    }
}

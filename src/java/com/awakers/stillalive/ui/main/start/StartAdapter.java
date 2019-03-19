package com.awakers.stillalive.ui.main.start;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.databinding.StartItem;
import es.dmoral.toasty.Toasty;
import java.util.Vector;

public class StartAdapter extends Adapter<StartViewHolder> {
    private Context context;
    private Vector<StartItemModel> list;

    public StartAdapter(Context context, Vector<StartItemModel> vector) {
        this.context = context;
        this.list = vector;
    }

    @NonNull
    public StartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StartViewHolder(StartItem.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(@NonNull StartViewHolder startViewHolder, int i) {
        startViewHolder.bind((StartItemModel) this.list.get(i));
        startViewHolder.getItem().setHandler(new StartAdapter$$Lambda$0(this, i));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onBindViewHolder$0$StartAdapter(int i, View view) {
        Context context = this.context;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append((String) ((StartItemModel) this.list.get(i)).themeName.get());
        stringBuilder.append(" 테마가 선택 되었습니다.");
        Toasty.success(context, stringBuilder.toString(), 0).show();
        Capsule.get().setTheme((String) ((StartItemModel) this.list.get(i)).themeName.get());
    }

    public int getItemCount() {
        return this.list.size();
    }
}

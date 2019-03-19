package com.awakers.stillalive.ui.main.start;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.awakers.stillalive.databinding.StartItem;

public class StartViewHolder extends ViewHolder {
    private StartItem item;

    public StartViewHolder(StartItem startItem) {
        super(startItem.getRoot());
        this.item = startItem;
    }

    public void bind(StartItemModel startItemModel) {
        this.item.setItemModel(startItemModel);
    }

    public StartItem getItem() {
        return this.item;
    }
}

package com.awakers.stillalive.ui.theme;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.awakers.stillalive.databinding.ThemeItem;

public class ThemeViewHolder extends ViewHolder {
    private ThemeItem item;

    public ThemeViewHolder(ThemeItem themeItem) {
        super(themeItem.getRoot());
        this.item = themeItem;
    }

    public void bind(ThemeItemModel themeItemModel) {
        this.item.setItemModel(themeItemModel);
    }

    public ThemeItem getItem() {
        return this.item;
    }
}

package com.awakers.stillalive.ui.main.setting;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.awakers.stillalive.databinding.SettingItem;

public class SettingViewHolder extends ViewHolder {
    private SettingItem item;

    public SettingViewHolder(SettingItem settingItem) {
        super(settingItem.getRoot());
        this.item = settingItem;
    }

    public void bind(SettingItemModel settingItemModel) {
        this.item.setItemModel(settingItemModel);
    }

    public SettingItem getItem() {
        return this.item;
    }
}

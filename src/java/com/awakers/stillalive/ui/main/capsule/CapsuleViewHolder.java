package com.awakers.stillalive.ui.main.capsule;

import android.support.v7.widget.RecyclerView.ViewHolder;
import com.awakers.stillalive.databinding.CapsuleItem;

public class CapsuleViewHolder extends ViewHolder {
    private CapsuleItem item;

    public CapsuleViewHolder(CapsuleItem capsuleItem) {
        super(capsuleItem.getRoot());
        this.item = capsuleItem;
    }

    public void bind(CapsuleItemModel capsuleItemModel) {
        this.item.setItemModel(capsuleItemModel);
    }

    public CapsuleItem getItem() {
        return this.item;
    }
}

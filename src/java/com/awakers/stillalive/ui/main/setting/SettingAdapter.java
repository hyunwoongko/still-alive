package com.awakers.stillalive.ui.main.setting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.awakers.stillalive.databinding.SettingItem;
import java.util.Vector;

public class SettingAdapter extends Adapter<SettingViewHolder> {
    private Context context;
    private Vector<SettingItemModel> list;
    private SettingViewModel viewModel;

    public SettingAdapter(Context context, Vector<SettingItemModel> vector, SettingViewModel settingViewModel) {
        this.context = context;
        this.list = vector;
        this.viewModel = settingViewModel;
    }

    @NonNull
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SettingViewHolder(SettingItem.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(@NonNull SettingViewHolder settingViewHolder, int i) {
        settingViewHolder.bind((SettingItemModel) this.list.get(i));
        settingViewHolder.getItem().setHandler(new SettingAdapter$$Lambda$0(this, settingViewHolder));
    }

    /* Access modifiers changed, original: final|synthetic */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    public final /* synthetic */ void lambda$onBindViewHolder$0$SettingAdapter(@android.support.annotation.NonNull com.awakers.stillalive.ui.main.setting.SettingViewHolder r2, android.view.View r3) {
        /*
        r1 = this;
        r2 = r2.getItem();
        r2 = r2.getItemModel();
        r2 = r2.settingName;
        r2 = r2.get();
        r2 = java.util.Objects.requireNonNull(r2);
        r2 = (java.lang.String) r2;
        r3 = r2.hashCode();
        r0 = -1858933251; // 0xffffffff9132edfd float:-1.411506E-28 double:NaN;
        if (r3 == r0) goto L_0x004b;
    L_0x001d:
        r0 = -1682125425; // 0xffffffff9bbccd8f float:-3.123483E-22 double:NaN;
        if (r3 == r0) goto L_0x0041;
    L_0x0022:
        r0 = 82800620; // 0x4ef6fec float:5.6291414E-36 double:4.0908942E-316;
        if (r3 == r0) goto L_0x0037;
    L_0x0027:
        r0 = 701843976; // 0x29d54a08 float:9.4719425E-14 double:3.467569973E-315;
        if (r3 == r0) goto L_0x002d;
    L_0x002c:
        goto L_0x0055;
    L_0x002d:
        r3 = "회원 탈퇴";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0055;
    L_0x0035:
        r2 = 1;
        goto L_0x0056;
    L_0x0037:
        r3 = "보유 테마 보기";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0055;
    L_0x003f:
        r2 = 3;
        goto L_0x0056;
    L_0x0041:
        r3 = "어플리케이션 정보";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0055;
    L_0x0049:
        r2 = 2;
        goto L_0x0056;
    L_0x004b:
        r3 = "로그아웃 하기";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0055;
    L_0x0053:
        r2 = 0;
        goto L_0x0056;
    L_0x0055:
        r2 = -1;
    L_0x0056:
        switch(r2) {
            case 0: goto L_0x006c;
            case 1: goto L_0x0066;
            case 2: goto L_0x0060;
            case 3: goto L_0x005a;
            default: goto L_0x0059;
        };
    L_0x0059:
        goto L_0x0071;
    L_0x005a:
        r2 = r1.viewModel;
        r2.showTheme();
        goto L_0x0071;
    L_0x0060:
        r2 = r1.viewModel;
        r2.information();
        goto L_0x0071;
    L_0x0066:
        r2 = r1.viewModel;
        r2.withdrawal();
        goto L_0x0071;
    L_0x006c:
        r2 = r1.viewModel;
        r2.logout();
    L_0x0071:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.awakers.stillalive.ui.main.setting.SettingAdapter.lambda$onBindViewHolder$0$SettingAdapter(com.awakers.stillalive.ui.main.setting.SettingViewHolder, android.view.View):void");
    }

    public int getItemCount() {
        return this.list.size();
    }
}

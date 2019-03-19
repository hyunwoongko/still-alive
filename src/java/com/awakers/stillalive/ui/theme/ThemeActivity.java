package com.awakers.stillalive.ui.theme;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.ThemeDB;
import com.awakers.stillalive.data.repo.UserTheme;
import com.awakers.stillalive.databinding.ThemeView;
import com.awakers.stillalive.ui.main.capsule.CapsuleAdapter;
import com.awakers.stillalive.ui.shop.ShopActivity;
import java.util.Arrays;
import java.util.Vector;
import javax.inject.Inject;

public class ThemeActivity extends BaseActivity implements ThemeNavigator {
    private ThemeView view;
    @Inject
    ThemeViewModel viewModel;

    /* Access modifiers changed, original: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.view = (ThemeView) DataBindingUtil.setContentView(this, R.layout.theme_view);
        DaggerThemeComponent.builder().themeFactory(new ThemeFactory(this)).themeActivity(this).build().inject(this);
        this.view.setViewModel(this.viewModel);
        this.view.recycler.setLayoutManager(new GridLayoutManager(this, 3));
        this.view.recycler.setAdapter(new CapsuleAdapter(this, new Vector()));
        this.viewModel.showUserTheme();
        super.onCreate(bundle);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
        super.onDestroy();
    }

    public synchronized void showUserTheme() {
        api().get("userTheme", UserTheme.class, new ThemeActivity$$Lambda$0(this, new Vector()));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showUserTheme$1$ThemeActivity(Vector vector, BaseRepository baseRepository) {
        vector.clear();
        UserTheme.set((UserTheme) baseRepository);
        if (UserTheme.get().getUserTheme() == null) {
            UserTheme.get().setUserTheme(Arrays.asList(new String[]{"Tree"}));
        }
        for (String str : UserTheme.get().getUserTheme()) {
            api().loadTheme(str, new ThemeActivity$$Lambda$1(this, new ThemeItemModel(), vector, str));
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$null$0$ThemeActivity(ThemeItemModel themeItemModel, Vector vector, String str, BaseRepository baseRepository) {
        ThemeDB.set((ThemeDB) baseRepository);
        themeItemModel.themeName.set(ThemeDB.get().getName());
        themeItemModel.imgSrc.set(ThemeDB.get().getThumb());
        vector.add(themeItemModel);
        if (str.equals(UserTheme.get().getUserTheme().get(UserTheme.get().getUserTheme().size() - 1))) {
            this.view.recycler.setAdapter(new ThemeAdapter(this, vector));
        }
    }

    public void replaceToShop(Boolean bool) {
        startActivity(new Intent(this, ShopActivity.class));
        showAnim(this);
    }
}

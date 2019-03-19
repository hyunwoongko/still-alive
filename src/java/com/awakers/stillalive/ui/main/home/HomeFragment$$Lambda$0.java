package com.awakers.stillalive.ui.main.home;

import com.awakers.stillalive.base.BaseRepository;
import java8.util.function.Consumer;

final /* synthetic */ class HomeFragment$$Lambda$0 implements Consumer {
    private final HomeFragment arg$1;
    private final int arg$2;

    HomeFragment$$Lambda$0(HomeFragment homeFragment, int i) {
        this.arg$1 = homeFragment;
        this.arg$2 = i;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$loadImage$0$HomeFragment(this.arg$2, (BaseRepository) obj);
    }
}

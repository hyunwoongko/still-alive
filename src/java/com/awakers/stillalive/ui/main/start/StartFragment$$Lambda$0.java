package com.awakers.stillalive.ui.main.start;

import com.awakers.stillalive.base.BaseRepository;
import java.util.Vector;
import java8.util.function.Consumer;

final /* synthetic */ class StartFragment$$Lambda$0 implements Consumer {
    private final StartFragment arg$1;
    private final StartItemModel arg$2;
    private final Vector arg$3;
    private final String arg$4;

    StartFragment$$Lambda$0(StartFragment startFragment, StartItemModel startItemModel, Vector vector, String str) {
        this.arg$1 = startFragment;
        this.arg$2 = startItemModel;
        this.arg$3 = vector;
        this.arg$4 = str;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$showUserTheme$0$StartFragment(this.arg$2, this.arg$3, this.arg$4, (BaseRepository) obj);
    }
}

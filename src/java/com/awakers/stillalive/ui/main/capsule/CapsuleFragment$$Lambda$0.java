package com.awakers.stillalive.ui.main.capsule;

import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.vo.Capsule;
import java.util.Vector;
import java8.util.function.Consumer;

final /* synthetic */ class CapsuleFragment$$Lambda$0 implements Consumer {
    private final CapsuleFragment arg$1;
    private final CapsuleItemModel arg$2;
    private final Capsule arg$3;
    private final Vector arg$4;

    CapsuleFragment$$Lambda$0(CapsuleFragment capsuleFragment, CapsuleItemModel capsuleItemModel, Capsule capsule, Vector vector) {
        this.arg$1 = capsuleFragment;
        this.arg$2 = capsuleItemModel;
        this.arg$3 = capsule;
        this.arg$4 = vector;
    }

    public void accept(Object obj) {
        this.arg$1.lambda$showUserCapsule$0$CapsuleFragment(this.arg$2, this.arg$3, this.arg$4, (BaseRepository) obj);
    }
}

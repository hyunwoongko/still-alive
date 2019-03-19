package com.awakers.stillalive.ui.main.capsule;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.databinding.CapsuleItem;
import com.awakers.stillalive.ui.password.PasswordActivity;
import es.dmoral.toasty.Toasty;
import java.util.Vector;
import java8.util.function.Consumer;

public class CapsuleAdapter extends Adapter<CapsuleViewHolder> {
    private Activity activity;
    private Vector<CapsuleItemModel> list;

    public CapsuleAdapter(Activity activity, Vector<CapsuleItemModel> vector) {
        this.activity = activity;
        this.list = vector;
    }

    @NonNull
    public CapsuleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CapsuleViewHolder(CapsuleItem.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    public void onBindViewHolder(@NonNull CapsuleViewHolder capsuleViewHolder, int i) {
        capsuleViewHolder.bind((CapsuleItemModel) this.list.get(i));
        capsuleViewHolder.getItem().setHandler(new CapsuleAdapter$$Lambda$0(this, i, capsuleViewHolder));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onBindViewHolder$0$CapsuleAdapter(final int i, @NonNull final CapsuleViewHolder capsuleViewHolder, View view) {
        AwakersApplication.appComponent.api().get("userCapsule", UserCapsule.class, new Consumer<BaseRepository>() {
            public void accept(BaseRepository baseRepository) {
                UserCapsule.set((UserCapsule) baseRepository);
                if (((Capsule) UserCapsule.get().getUserCapsule().get(i)).isOnGoing()) {
                    Toasty.warning(CapsuleAdapter.this.activity, "아직 완성되지 않은 생존일지입니다.", 0).show();
                    return;
                }
                AwakersApplication.currentCapsuleName = (String) capsuleViewHolder.getItem().getItemModel().capsuleName.get();
                CapsuleAdapter.this.activity.startActivity(new Intent(CapsuleAdapter.this.activity, PasswordActivity.class));
                CapsuleAdapter.this.activity.overridePendingTransition(17432576, 17432577);
            }
        });
    }

    public int getItemCount() {
        return this.list.size();
    }
}

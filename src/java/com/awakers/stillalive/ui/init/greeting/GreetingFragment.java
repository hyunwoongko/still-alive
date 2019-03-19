package com.awakers.stillalive.ui.init.greeting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.awakers.stillalive.R;
import com.awakers.stillalive.base.BaseFragment;
import com.awakers.stillalive.databinding.GreetingView;
import com.awakers.stillalive.ui.main.MainActivity;
import java.util.Objects;
import javax.inject.Inject;

public class GreetingFragment extends BaseFragment implements GreetingNavigator {
    private GreetingView view;
    @Inject
    GreetingViewModel viewModel;

    @Nullable
    public synchronized View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.view = (GreetingView) DataBindingUtil.inflate(layoutInflater, R.layout.greeting_view, viewGroup, false);
        DaggerGreetingComponent.builder().greetingFragment(this).greetingFactory(new GreetingFactory(this)).build().inject(this);
        this.view.setViewModel(this.viewModel);
        this.viewModel.showGreetingObservable();
        return this.view.getRoot();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.viewModel.onCleared();
        this.viewModel = null;
        this.view = null;
    }

    public void replaceToMain(boolean z) {
        ((FragmentActivity) Objects.requireNonNull(getActivity())).startActivity(new Intent(getActivity(), MainActivity.class).setFlags(335577088));
        getActivity().overridePendingTransition(17432576, 17432577);
    }
}

package com.awakers.stillalive.ui.main.setting;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SettingFragment_MembersInjector implements MembersInjector<SettingFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<SettingViewModel> viewModelProvider;

    public SettingFragment_MembersInjector(Provider<SettingViewModel> provider) {
        this.viewModelProvider = provider;
    }

    public static MembersInjector<SettingFragment> create(Provider<SettingViewModel> provider) {
        return new SettingFragment_MembersInjector(provider);
    }

    public void injectMembers(SettingFragment settingFragment) {
        if (settingFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        settingFragment.viewModel = (SettingViewModel) this.viewModelProvider.get();
    }

    public static void injectViewModel(SettingFragment settingFragment, Provider<SettingViewModel> provider) {
        settingFragment.viewModel = (SettingViewModel) provider.get();
    }
}

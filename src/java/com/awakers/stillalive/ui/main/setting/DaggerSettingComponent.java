package com.awakers.stillalive.ui.main.setting;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerSettingComponent implements SettingComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<SettingViewModel> provideViewModelProvider;
    private MembersInjector<SettingFragment> settingFragmentMembersInjector;

    public static final class Builder {
        private SettingFactory settingFactory;
        private SettingFragment settingFragment;

        private Builder() {
        }

        public SettingComponent build() {
            StringBuilder stringBuilder;
            if (this.settingFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SettingFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.settingFragment != null) {
                return new DaggerSettingComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(SettingFragment.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder settingFactory(SettingFactory settingFactory) {
            this.settingFactory = (SettingFactory) Preconditions.checkNotNull(settingFactory);
            return this;
        }

        public Builder settingFragment(SettingFragment settingFragment) {
            this.settingFragment = (SettingFragment) Preconditions.checkNotNull(settingFragment);
            return this;
        }
    }

    private DaggerSettingComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = SettingFactory_ProvideViewModelFactory.create(builder.settingFactory);
        this.settingFragmentMembersInjector = SettingFragment_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(SettingFragment settingFragment) {
        this.settingFragmentMembersInjector.injectMembers(settingFragment);
    }
}

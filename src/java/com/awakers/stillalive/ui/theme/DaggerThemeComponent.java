package com.awakers.stillalive.ui.theme;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerThemeComponent implements ThemeComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Provider<ThemeViewModel> provideViewModelProvider;
    private MembersInjector<ThemeActivity> themeActivityMembersInjector;

    public static final class Builder {
        private ThemeActivity themeActivity;
        private ThemeFactory themeFactory;

        private Builder() {
        }

        public ThemeComponent build() {
            StringBuilder stringBuilder;
            if (this.themeFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(ThemeFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.themeActivity != null) {
                return new DaggerThemeComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(ThemeActivity.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder themeFactory(ThemeFactory themeFactory) {
            this.themeFactory = (ThemeFactory) Preconditions.checkNotNull(themeFactory);
            return this;
        }

        public Builder themeActivity(ThemeActivity themeActivity) {
            this.themeActivity = (ThemeActivity) Preconditions.checkNotNull(themeActivity);
            return this;
        }
    }

    private DaggerThemeComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = ThemeFactory_ProvideViewModelFactory.create(builder.themeFactory);
        this.themeActivityMembersInjector = ThemeActivity_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(ThemeActivity themeActivity) {
        this.themeActivityMembersInjector.injectMembers(themeActivity);
    }
}

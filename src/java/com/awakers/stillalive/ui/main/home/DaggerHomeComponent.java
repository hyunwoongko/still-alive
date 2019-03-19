package com.awakers.stillalive.ui.main.home;

import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerHomeComponent implements HomeComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private MembersInjector<HomeFragment> homeFragmentMembersInjector;
    private Provider<HomeViewModel> provideViewModelProvider;

    public static final class Builder {
        private HomeFactory homeFactory;
        private HomeFragment homeFragment;

        private Builder() {
        }

        public HomeComponent build() {
            StringBuilder stringBuilder;
            if (this.homeFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(HomeFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.homeFragment != null) {
                return new DaggerHomeComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(HomeFragment.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder homeFactory(HomeFactory homeFactory) {
            this.homeFactory = (HomeFactory) Preconditions.checkNotNull(homeFactory);
            return this;
        }

        public Builder homeFragment(HomeFragment homeFragment) {
            this.homeFragment = (HomeFragment) Preconditions.checkNotNull(homeFragment);
            return this;
        }
    }

    private DaggerHomeComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideViewModelProvider = HomeFactory_ProvideViewModelFactory.create(builder.homeFactory);
        this.homeFragmentMembersInjector = HomeFragment_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(HomeFragment homeFragment) {
        this.homeFragmentMembersInjector.injectMembers(homeFragment);
    }
}

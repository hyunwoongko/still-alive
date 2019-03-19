package com.awakers.stillalive.ui.init.greeting;

import com.awakers.stillalive.data.vo.Greeting;
import dagger.MembersInjector;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerGreetingComponent implements GreetingComponent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private MembersInjector<GreetingFragment> greetingFragmentMembersInjector;
    private Provider<Greeting> provideGreetingProvider;
    private Provider<GreetingViewModel> provideViewModelProvider;

    public static final class Builder {
        private GreetingFactory greetingFactory;
        private GreetingFragment greetingFragment;

        private Builder() {
        }

        public GreetingComponent build() {
            StringBuilder stringBuilder;
            if (this.greetingFactory == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(GreetingFactory.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            } else if (this.greetingFragment != null) {
                return new DaggerGreetingComponent(this);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(GreetingFragment.class.getCanonicalName());
                stringBuilder.append(" must be set");
                throw new IllegalStateException(stringBuilder.toString());
            }
        }

        public Builder greetingFactory(GreetingFactory greetingFactory) {
            this.greetingFactory = (GreetingFactory) Preconditions.checkNotNull(greetingFactory);
            return this;
        }

        public Builder greetingFragment(GreetingFragment greetingFragment) {
            this.greetingFragment = (GreetingFragment) Preconditions.checkNotNull(greetingFragment);
            return this;
        }
    }

    private DaggerGreetingComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideGreetingProvider = GreetingFactory_ProvideGreetingFactory.create(builder.greetingFactory);
        this.provideViewModelProvider = GreetingFactory_ProvideViewModelFactory.create(builder.greetingFactory, this.provideGreetingProvider);
        this.greetingFragmentMembersInjector = GreetingFragment_MembersInjector.create(this.provideViewModelProvider);
    }

    public void inject(GreetingFragment greetingFragment) {
        this.greetingFragmentMembersInjector.injectMembers(greetingFragment);
    }
}

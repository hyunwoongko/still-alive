package com.awakers.stillalive.ui.init.greeting;

import com.awakers.stillalive.data.vo.Greeting;
import dagger.Module;
import dagger.Provides;
import java.lang.ref.WeakReference;
import rx.subscriptions.CompositeSubscription;

@Module
public class GreetingFactory {
    private GreetingNavigator navigator;

    public GreetingFactory(GreetingNavigator greetingNavigator) {
        this.navigator = greetingNavigator;
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public GreetingViewModel provideViewModel(Greeting greeting) {
        return new GreetingViewModel(new WeakReference(this.navigator), new CompositeSubscription(), greeting);
    }

    /* Access modifiers changed, original: 0000 */
    @Provides
    public Greeting provideGreeting() {
        return new Greeting();
    }
}

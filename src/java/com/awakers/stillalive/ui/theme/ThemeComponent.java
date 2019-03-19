package com.awakers.stillalive.ui.theme;

import dagger.Component;

@Component(dependencies = {ThemeActivity.class}, modules = {ThemeFactory.class})
public interface ThemeComponent {
    void inject(ThemeActivity themeActivity);
}

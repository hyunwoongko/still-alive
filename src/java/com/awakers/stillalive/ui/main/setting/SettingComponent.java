package com.awakers.stillalive.ui.main.setting;

import dagger.Component;

@Component(dependencies = {SettingFragment.class}, modules = {SettingFactory.class})
public interface SettingComponent {
    void inject(SettingFragment settingFragment);
}

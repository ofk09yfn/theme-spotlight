/*
 * Copyright (C) 2014 Klinker Apps, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.klinker.android.theme_spotlight.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import com.klinker.android.theme_spotlight.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
public class SettingsActivityTest {

    @Test
    public void testOnCreate() throws PackageManager.NameNotFoundException {
        Bundle bundle = mock(Bundle.class);
        SettingsActivity spy = Mockito.spy(new SettingsActivity());
        doNothing().when(spy).superOnCreate(bundle);
        doNothing().when(spy).addPreferencesFromResource(R.xml.preferences);
        doNothing().when(spy).initAboutPreference();

        spy.onCreate(bundle);

        verify(spy).addPreferencesFromResource(R.xml.preferences);
        verify(spy).initAboutPreference();
    }

    @Test
    public void testChangelogItem() throws PackageManager.NameNotFoundException {
        Preference preference = mock(Preference.class);
        SettingsActivity spy = Mockito.spy(new SettingsActivity());
        when(spy.getVersionNumber()).thenReturn("");
        when(spy.getString(R.string.version_number)).thenReturn("");
        doReturn(preference).when(spy).getAboutPreference();
        doNothing().when(preference).setSummary(anyString());

        spy.initAboutPreference();

        verify(preference).setSummary("");
        verify(preference).setOnPreferenceClickListener(any(Preference.OnPreferenceClickListener.class));
    }
}
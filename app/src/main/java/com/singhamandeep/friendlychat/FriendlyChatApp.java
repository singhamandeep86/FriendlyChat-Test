/*
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.singhamandeep.friendlychat;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 * This is a subclass of {@link Application} used to provide shared objects for this app, such as
 * the {@link Tracker}.
 */
public class FriendlyChatApp extends Application {

  private static GoogleAnalytics sAnalytics;
  private static Tracker sDefaultTracker;
  private static Tracker sAlternateTracker;

  @Override
  public void onCreate() {
    super.onCreate();

    sAnalytics = GoogleAnalytics.getInstance(this);
    sAnalytics.enableAutoActivityReports(this);

  }

  /**
   * Gets the app {@link Tracker} for this {@link Application}.
   * @return tracker
   */
  synchronized public Tracker getAppTracker() {
    // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
    if (sDefaultTracker == null) {
        sDefaultTracker = sAnalytics.newTracker(R.xml.app_tracker);
    }

    return sDefaultTracker;
  }

  /**
   * Gets the web {@link Tracker} for this {@link Application}.
   * @return tracker
   */
  synchronized public Tracker getWebTracker() {
    // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
    if (sAlternateTracker == null) {
        sAlternateTracker = sAnalytics.newTracker(R.xml.web_tracker);
    }

    return sAlternateTracker;
  }
}

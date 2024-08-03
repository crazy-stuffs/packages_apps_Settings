/*
 * Copyright (C) 2019-2022 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;

public class LineageVersionDetailPreferenceController extends BasePreferenceController {
    @VisibleForTesting
    static final String KEY_LINEAGE_VERSION_PROP = "ro.modversion";
    static final String EVEREST_BUILDTYPE_PROPERTY = "ro.everest.buildtype";
    static final String EVEREST_EDITION_PROPERTY = "ro.everest.edition";

    public LineageVersionDetailPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(SystemProperties.get(KEY_LINEAGE_VERSION_PROP))
                ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }

    @Override
    public CharSequence getSummary() {
        String everestVersion = SystemProperties.get(KEY_LINEAGE_VERSION_PROP);
        String everestBuildType = SystemProperties.get(EVEREST_BUILDTYPE_PROPERTY);
        String everestEdition = SystemProperties.get(EVEREST_EDITION_PROPERTY);

        StringBuilder summary = new StringBuilder();

        if (!TextUtils.isEmpty(everestVersion)) {
            summary.append(everestVersion);
        }

        if (!TextUtils.isEmpty(everestBuildType)) {
            if (summary.length() > 0) summary.append(" | ");
            summary.append(everestBuildType);
        }

        if (!TextUtils.isEmpty(everestEdition)) {
            if (summary.length() > 0) summary.append(" | ");
            summary.append(everestEdition);
        }

        return summary.length() > 0 ? summary.toString() : mContext.getString(R.string.device_info_default);
    }
}

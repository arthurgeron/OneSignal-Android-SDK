/**
 * Modified MIT License
 * <p>
 * Copyright 2018 OneSignal
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * 1. The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * 2. All copies of substantial portions of the Software may only be used in connection
 * with services provided by OneSignal.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.onesignal;

import android.content.Context;

import org.robolectric.annotation.Implements;

import static com.onesignal.ShadowPushRegistratorFCM.regId;

@Implements(PushRegistratorADM.class)
public class ShadowPushRegistratorADM {

    private static boolean skipComplete;

    private static PushRegistrator.RegisteredHandler lastCallback;

    public static void resetStatics() {
        skipComplete = false;

        lastCallback = null;
    }

    public void registerForPush(final Context context, String noKeyNeeded, final PushRegistrator.RegisteredHandler callback) {
        lastCallback = callback;

        if (!skipComplete)
            fireCallback(regId);
    }

    public static void fireCallback(String id) {
        if (lastCallback == null)
            return;

        lastCallback.complete(id, 1);
    }
}

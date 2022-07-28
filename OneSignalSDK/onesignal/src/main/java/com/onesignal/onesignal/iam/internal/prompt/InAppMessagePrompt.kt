package com.onesignal.onesignal.iam.internal.prompt

internal abstract class InAppMessagePrompt() {
    private var prompted = false
    abstract fun handlePrompt(callback: OSPromptActionCompletionCallback?)
    abstract val promptKey: String
    fun hasPrompted(): Boolean {
        return prompted
    }

    fun setPrompted(prompted: Boolean) {
        this.prompted = prompted
    }

    override fun toString(): String {
        return "OSInAppMessagePrompt{" +
                "key=" + promptKey +
                " prompted=" + prompted +
                '}'
    }

    /*
    * End OneSignalOutcome module
    */
    internal interface OSPromptActionCompletionCallback {
        fun onCompleted(result: PromptActionResult?)
    }

    internal enum class PromptActionResult {
        PERMISSION_GRANTED, PERMISSION_DENIED, LOCATION_PERMISSIONS_MISSING_MANIFEST, ERROR
    }
}
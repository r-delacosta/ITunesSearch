package com.rdelacosta.itunessearch.utils

import timber.log.Timber

class ClickableDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        with(element) {
            return "($fileName:$lineNumber) $methodName()"
        }
    }
}
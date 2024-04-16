package com.reva.revashoppy.common

import android.text.InputFilter
import android.text.Spanned

class DecimalInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val newText = dest.toString().substring(0, dstart) + source?.subSequence(start, end) + dest.toString().substring(dend)
        return if (newText.isEmpty()) {
            null
        } else {
            // Check if the input starts with '0' and it's not followed by a '.'
            val startsWithZero = newText.startsWith("0") && !newText.startsWith("0.")
            // Regex pattern to allow up to 4 digits before the decimal point and up to 2 digits after it
            val regexPattern = "^\\d{0,4}(\\.\\d{0,2})?\$".toRegex()
            if (startsWithZero || !regexPattern.matches(newText)) {
                "" // Empty string means reject the input
            } else {
                null // Accept the input
            }
        }
    }
}

package com.reva.revashoppy.common


import android.text.InputFilter
import android.text.Spanned

class NumberInputFilter : InputFilter {
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
            // Check if the input starts with '0' or if it's not a valid number or if it's longer than 3 digits
            val startsWithZero = newText.startsWith("0")
            val isValidNumber = newText.length <= 3 && newText.toIntOrNull() != null
            if (startsWithZero || !isValidNumber) {
                "" // Empty string means reject the input
            } else {
                null // Accept the input
            }
        }
    }
}

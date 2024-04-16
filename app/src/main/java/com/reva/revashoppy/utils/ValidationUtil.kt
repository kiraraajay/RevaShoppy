package com.reva.revashoppy.utils

import java.util.Calendar
import java.util.regex.Pattern

object ValidationUtils {

    fun isEmpty(string: String?): Boolean {
        return string.isNullOrEmpty()
    }

    fun isNotEmpty(string: String?): Boolean {
        return !isEmpty(string)
    }

    fun isEmailValid(email: String?): Boolean {
        if (isEmpty(email)) {
            return false
        }
        val regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}\$"
        return Pattern.compile(regex).matcher(email.toString()).matches()
    }

    fun isPasswordValid(password: String?): Boolean {
        if (isEmpty(password)) {
            return false
        }
        return  password!!.length>6
    }

    fun isPhoneNumberValid(phoneNumber: String?): Boolean {
        if (isEmpty(phoneNumber)) {
            return false
        }
        val regex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s./0-9]*\$"
        return Pattern.compile(regex).matcher(phoneNumber.toString()).matches()
    }

    fun isPostalCodeValid(postalCode: String?): Boolean {
        if (isEmpty(postalCode)) {
            return false
        }
        val regex = "^[0-9]{5}(?:-[0-9]{4})?\$"
        return Pattern.compile(regex).matcher(postalCode.toString()).matches()
    }

    fun isDateValid(date: String?): Boolean {
        if (isEmpty(date)) {
            return false
        }
        val regex = "^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[0-1])/((19|20)\\d\\d)\$"
        return Pattern.compile(regex).matcher(date.toString()).matches()
    }

    fun isCreditCardNumberValid(creditCardNumber: String?): Boolean {
        if (isEmpty(creditCardNumber)) {
            return false
        }
        val regex = "^((4\\d{3})|(5[1-5]\\d{2})|(6011))-?\\d{4}-?\\d{4}-?\\d{4}|3[4,7]\\d{13}$"
        return Pattern.compile(regex).matcher(creditCardNumber.toString()).matches()
    }

    fun isSpinnerSelcted(spinner:String):Boolean{
        if (spinner.equals("Select Login Type")){
            return false
        }
        return true

    }

    fun isSelectMarkType(string: String):Boolean{
        if (string.isNullOrEmpty()){

            return false
        }
        return true
    }

    fun getCurrentMonth(): String {
        // Get current time
        val calendar = Calendar.getInstance()

        // Get the current month
        val currentMonth = calendar.get(Calendar.MONTH)

        // Calendar.MONTH is zero-based, so add 1 to get the actual month
        return String.format("%02d", currentMonth + 1)
    }

    fun getCurrentYear(): String {
        // Get current time
        val calendar = Calendar.getInstance()

        // Get the current year
        val currentYear = calendar.get(Calendar.YEAR)

        return currentYear.toString()
    }

    fun checkNumber(number: String?):Boolean{
        return number!!.isNotEmpty() && number.length==10
    }

    fun validateNumber(number: String): Boolean {
        val regex = Regex("^[^0-5].*")
        return regex.matches(number)
    }


    fun isEmptyOrLength(password: String): Boolean {
        // Check if the password is empty or its length is less than the specified threshold
        return password.isNotEmpty()  && password.length >= 4
    }

    fun removeSpaceInLine(text: String): String {
        val newText = text.replace("\\s+".toRegex(), " ")
        return newText
    }


}
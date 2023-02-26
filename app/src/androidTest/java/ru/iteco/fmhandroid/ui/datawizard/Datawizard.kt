package ru.iteco.fmhandroid.ui.datawizard

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.Assert

class Datawizard() {

    //private lateinit var device: UiDevice;
    private val LAUNCH_TIMEOUT = 5000L

    fun DataWizard() {
    }

//    fun thisDevice(): Datawizard {
//        return dev
//    }
    fun authPageLoginField(): UiObject {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Login")
        )
    }

    fun authPagePasswordField(): UiObject {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Password")
        )
    }

    fun authPageSignInButton(): UiObject {
        return UiObject(UiSelector().text("SIGN IN"))
    }

    fun authPageAuthImage(): UiObject {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button")
        );
    }

}
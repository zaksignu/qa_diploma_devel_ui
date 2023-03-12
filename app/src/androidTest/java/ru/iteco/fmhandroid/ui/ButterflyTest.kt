package ru.iteco.fmhandroid.ui

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.ui.datawizard.Datawizard

@RunWith(AllureAndroidJUnit4::class)
//@RunWith(AndroidJUnit4::class)
class ButterflyTest {
    private lateinit var device: UiDevice;
    private lateinit var dWizard: Datawizard;


    // change this to your app's package name
    private val LAUNCH_TIMEOUT = 5000L

    @Before
    fun startMainActivityFromHomeScreen() {
        dWizard = Datawizard()
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName;
        assertThat(launcherPackage, CoreMatchers.notNullValue())

        device.wait(
            Until.hasObject(By.pkg(launcherPackage)),
            LAUNCH_TIMEOUT
        )
        // Launch the blueprint app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val packageName = context.packageName;
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), LAUNCH_TIMEOUT)
        //
        // Wait for the app to appear

        device.wait(
            Until.hasObject(
                By.pkg(packageName)
                    .depth(0)
            ),
            LAUNCH_TIMEOUT
        )


    }

    //Тест 1.9
    @Test
    fun butterflyPageExpand() {
        dWizard.mainButterflyPic(device)!!.click()
        val shortNewsTabHeight = dWizard.butterflyCard(device)!!.bounds.height()
        dWizard.butterflyCard(device)!!.click()
        val longNewsTabHeight = dWizard.butterflyCard(device)!!.bounds.height()

        assertTrue(longNewsTabHeight > shortNewsTabHeight)

    }

    //Тест 1.8
    @Test
    fun butterflyPageCard() {
        dWizard.mainButterflyPic(device)!!.click()

        assertTrue(dWizard.butterflyCard(device)!!.isClickable)


    }

}
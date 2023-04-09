package ru.iteco.fmhandroid.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.ui.datawizard.Datawizard



@RunWith(AndroidJUnit4::class)
class ClaimsCardTest {

    private lateinit var device: UiDevice;
    private lateinit var  dWizard : Datawizard;


        @Before
        fun startMainActivityFromHomeScreen() {
            // Initialize UiDevice instance
            device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            // Start from the home screen
            device.pressHome()

            // Wait for launcher
            val launcherPackage = device.launcherPackageName;
            assertThat(launcherPackage, CoreMatchers.notNullValue())

            device.wait(
                Until.hasObject(By.pkg(launcherPackage)),
                dWizard.LAUNCH_TIMEOUT)
            // Launch the blueprint app
            val context = ApplicationProvider.getApplicationContext<Context>()
            val packageName = context.packageName;
            val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances

            context.startActivity(intent)
            device.wait(Until.hasObject(By.pkg(packageName)),dWizard.LAUNCH_TIMEOUT)
            //
            // Wait for the app to appear

            device.wait(
                Until.hasObject(
                    By.pkg(packageName)
                        .depth(0)
                ),
                dWizard.LAUNCH_TIMEOUT
            )

            try {

                dWizard.mainTradeMark(device)!!.isEnabled()

            } catch (e: UiObjectNotFoundException) {
                dWizard.authLoginField().text = dWizard.LOGIN_CORRECT
                dWizard.authPasswordField().text = dWizard.PASS_CORRECT
                dWizard.nameSignIn().click()
                device.wait(
                    Until.findObject(
                        By.res(
                            packageName,
                            "ru.iteco.fmhandroid:id/authorization_image_button" // change to your button id
                        )
                    ),
                    500 /* wait 500ms */
                )
            }
        }



}
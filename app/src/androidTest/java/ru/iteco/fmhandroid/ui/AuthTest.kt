package ru.iteco.fmhandroid.ui

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.ui.datawizard.Datawizard


@RunWith(AndroidJUnit4::class)
class AuthTest {
    private lateinit var device: UiDevice;
    private lateinit var dWizard: Datawizard;


//    private val  LOGIN_CORRECT = "login2"
//    private val  LOGIN_INCORRECT = "login"
//    private val  PASS_CORRECT = "password2"
//    private val  PASS_INCORRECT = "password"

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
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
        // val context = ApplicationProvider.getApplicationContext<Context>()
        // val packageName = context.packageName;
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
        //TODO Добавить обработчик "до"
    }

    @After
    fun finish() {
//TODO Добавить обработчик "после"
        //     val mainLogo = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view"))
        //if (mainLogo.exists()) {
        if (dWizard.mainTradeMark(device)!!.exists()) {
            dWizard.mainUsers(device)!!.click()
            //device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button").instance(0)).click()
            dWizard.mainLogOutLink()!!.click()
            //    UiObject(UiSelector().text("Log out")).click()
        }
    }

    //Тест 1.1.
    @Test
    fun authCorrectAuth() {
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
        assertTrue(dWizard.authPageAuthImage(device).exists());

    }

    //Тест 1.2.
    @Test
    fun authIncorrectLoginAuth() {

        dWizard.authLoginField().text = dWizard.LOGIN_INCORRECT
        dWizard.authPasswordField().text = dWizard.PASS_CORRECT
        dWizard.nameSignIn().click()

        device.waitForIdle(500L);

        assertTrue(dWizard.authLoginField().exists());
    }

    // Тест 1.3.
    @Test
    fun authIncorrectPassAuth() {

        dWizard.authLoginField().text = dWizard.LOGIN_CORRECT
        dWizard.authPasswordField().text = dWizard.PASS_INCORRECT
        dWizard.nameSignIn().click()

        device.waitForIdle(500L);

        assertTrue(dWizard.authLoginField().exists());
    };
}


package ru.iteco.fmhandroid.ui

import android.content.Context
import android.content.Intent
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import okhttp3.internal.wait
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
    private lateinit var  dWizard : Datawizard;
    private lateinit var  dWiz : UiDevice;


    private val  LOGIN_CORRECT = "login2"
    private val  LOGIN_INCORRECT = "login"
    private val  PASS_CORRECT = "password2"
    private val  PASS_INCORRECT = "password"

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
    private val LAUNCH_TIMEOUT = 5000L

    @Before
    fun startMainActivityFromHomeScreen() {
        dWizard  = Datawizard()

        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName;
        assertThat(launcherPackage, CoreMatchers.notNullValue())

        device.wait(
            Until.hasObject(By.pkg(launcherPackage)),
            LAUNCH_TIMEOUT)
        // Launch the blueprint app
       // val context = ApplicationProvider.getApplicationContext<Context>()
       // val packageName = context.packageName;
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)),LAUNCH_TIMEOUT)
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
    @After
    fun finish(){

        val mainLogo = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view"))
        if (mainLogo.exists()) {
            device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button").instance(0)).click()
            UiObject(UiSelector().text("Log out")).click()
        }
    }

    @Test
    fun authCorrectAuth(){
//        val loginField = UiDevice.getInstance(getInstrumentation()).findObject(
//            UiSelector().className(
//                EditText::class.java
//            ).textContains("Login")
//        )
    //   dWizard.authPageLoginField().text = "login2"
       dWizard.authPageLoginField().text = LOGIN_CORRECT
      //  loginField.text = "login2"


//        val passwordField = UiDevice.getInstance(getInstrumentation()).findObject(
//            UiSelector().className(
//                EditText::class.java
//            ).textContains("Password")
//        )
//        passwordField.text = "password2"
//        dWizard.authPagePasswordField().text = "password2"
        dWizard.authPagePasswordField().text = PASS_CORRECT
        dWizard.authPageSignInButton().click()
//        val signInButton = UiObject(UiSelector().text("SIGN IN"))
//        signInButton.click()
        // searching for a UI component with a resource Id btn_goto_second

       device.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "ru.iteco.fmhandroid:id/authorization_image_button" // change to your button id
                )
            ),
            500 /* wait 500ms */
        )

//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button").instance(0)).click()
////            UiObject(UiSelector().text("Log out")).click()


        device.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "ru.iteco.fmhandroid:id/authorization_image_button" // change to your button id
                )
            ),
            500 /* wait 500ms */
        )

//        val authButton = UiDevice.getInstance(getInstrumentation()).findObject(
//            UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button")
//        );
        assertTrue(dWizard.authPageAuthImage().exists());
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button").instance(0)).click()
//            UiObject(UiSelector().text("Log out")).click()
    }

    @Test
    fun authIncorrectLoginAuth(){

//        val loginField = UiDevice.getInstance(getInstrumentation()).findObject(
//            UiSelector().className(
//                EditText::class.java
//            ).textContains("Login")
//        )
//
//        loginField.text = "login"
dWizard.authPageLoginField().text = "login";

//        val passwordField = UiDevice.getInstance(getInstrumentation()).findObject(
//            UiSelector().className(
//                EditText::class.java
//            ).textContains("Password")
//        )
//        passwordField.text = "password2"
dWizard.authPagePasswordField().text = "password2"
        dWizard.authPageSignInButton().click()
//        val signInButton = UiObject(UiSelector().text("SIGN IN"))
//        signInButton.click()

        device.waitForIdle(500L);

        val loginFld = UiDevice.getInstance(getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Login")
        );

    assertTrue(loginFld.exists());
   }

    @Test
    fun authIncorrectPassAuth(){
        val loginField = UiDevice.getInstance(getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Login")
        )
        loginField.text = "login2"


        val passwordField = UiDevice.getInstance(getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Password")
        )
        passwordField.text = "password"

        val signInButton = UiObject(UiSelector().text("SIGN IN"))
        signInButton.click()

        device.waitForIdle(500L);
        val loginFld = UiDevice.getInstance(getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Login")
        );

        assertTrue(loginFld.exists());
    }

}
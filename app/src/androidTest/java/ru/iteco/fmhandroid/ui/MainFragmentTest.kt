package ru.iteco.fmhandroid.ui

import android.content.Context
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainFragmentTest
{
    private lateinit var device: UiDevice;


    @Test
    fun test1(){
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        val launcherPackage = device.launcherPackageName;
        device.wait(Until.hasObject(By.pkg(launcherPackage)), 5000L)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val packageName = context.packageName;
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)),5000L)

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
        passwordField.text = "password2"

        val ok = UiObject(UiSelector().text("SIGN IN"))
        ok.click()
/////////////
        // searching for a UI component with a resource Id btn_goto_second
        val secondActivityButton = device.wait(
            Until.findObject(
                By.res(
                    packageName,
                    "ru.iteco.fmhandroid:id/main_menu_image_button" // change to your button id
                )
            ),
            500 /* wait 500ms */
        )
////////////////////
// Perform a click on the button to load the second activity.
      //  secondActivityButton.click()
// clicks a button with the text ok on the page
// Navigate to some different page with a button with the text ok
// clicks a button with the text ok on the page
// Navigate to some different page with a button with the text ok
        //ok.click()


//    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/login_text_input_layout").instance(0)).click();
 //     device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/login_text_input_layout").textContains("Login")).setText("12");

//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/login_text_input_layout").instance(0)).setText("123")
      // device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/enter_button").instance(0)).click();

      //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/login_text_input_layout").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/password_text_input_layout").instance(0)).text = "123";

//        device.wait(
//            Until.hasObject(By.pkg(launcherPackage).depth(0)), // condition
//           5000L // timeout
//        )


    }



}
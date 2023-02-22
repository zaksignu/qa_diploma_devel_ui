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
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
class ClaimsCardTest {

    private val commentForAdd = "Пчеловод был тут"
        private lateinit var device: UiDevice;

        //  private lateinit var mDevice: UiDevice
        //   private val BASIC_SAMPLE_PACKAGE = "com.example.android......" // change this to your app's package name
        private val LAUNCH_TIMEOUT = 5000L

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
                LAUNCH_TIMEOUT)
            // Launch the blueprint app
            val context = ApplicationProvider.getApplicationContext<Context>()
            val packageName = context.packageName;
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


        @Test
        fun claimsCardAddComment() {
            device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
            device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
            device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/add_comment_image_button").instance(0)).click()
            device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/comment_text_input_layout").instance(0)).text =commentForAdd
             val commentField = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
                UiSelector().className(
                    EditText::class.java
                ).textContains("Comment")
            )
            commentField.text = commentForAdd

            device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button").instance(0)).click()

            val assertField = UiObject(UiSelector().text(commentForAdd))

           assertTrue( assertField.exists())
        }

}
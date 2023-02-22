package ru.iteco.fmhandroid.ui

import android.content.Context
import android.content.Intent
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
class MainMenuTest {
    private lateinit var device: UiDevice;

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
    fun leftMenuNews() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
        val claimsLink = UiObject(UiSelector().text("News"))
        claimsLink.click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
        val mainLink = UiObject(UiSelector().text("Main"))
        mainLink.click()
        val newsBlock = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main").instance(0))
        val claimsBlock = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_claim_include_on_fragment_main").instance(0))
        assertTrue((newsBlock.exists())&&(claimsBlock.exists()))
    }

    @Test
    fun mainMenuNewsExpand() {

        var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0));
        val shortNewsTabHeight =   newsTab.bounds.height()
        newsTab.click()
        val longNewsTabHeight =   newsTab.bounds.height()
        assertTrue(longNewsTabHeight>shortNewsTabHeight)
    }

    @Test
    fun mainMenuButterfly() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
        val claimsLink = UiObject(UiSelector().text("About"))
        claimsLink.click()
        device.waitForIdle(500L);
        val newsFld = UiObject(UiSelector().text("About"));
        assertTrue(newsFld.exists());
    }


    @Test
    fun mainMenuClaimsExpand() {

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/expand_material_button").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
        val claimsCard =    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/executor_name_text_view").instance(0)).exists()
        assertTrue(claimsCard)
    }

}
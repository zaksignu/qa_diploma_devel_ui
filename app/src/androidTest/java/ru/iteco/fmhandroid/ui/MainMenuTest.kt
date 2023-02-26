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
import org.junit.runners.Parameterized
import ru.iteco.fmhandroid.ui.datawizard.Datawizard


@RunWith(AndroidJUnit4::class)
class MainMenuTest {
    private lateinit var device: UiDevice;
    private lateinit var  dWizard : Datawizard;


    private val LAUNCH_TIMEOUT = 5000L
    private val  LOGIN_CORRECT = "login2"
    private val  PASS_CORRECT = "password2"

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        dWizard  = Datawizard()
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

        if (dWizard.authPageAuthImage().exists()){
            dWizard.authLoginField().text = LOGIN_CORRECT
            dWizard.authPasswordField().text = PASS_CORRECT
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

    @Test
    fun leftMenuNews() {
        dWizard.mainMenu(device)!!.click()
        dWizard.nameNews().click()
        dWizard.mainMenu(device)!!.click()
        dWizard.nameMain().click()

//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
//        val claimsLink = UiObject(UiSelector().text("News"))
//        claimsLink.click()
      //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
     //   val mainLink = UiObject(UiSelector().text("Main"))
      //  mainLink.click()
    //    val newsBlock = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main").instance(0))
     //   val claimsBlock = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_claim_include_on_fragment_main").instance(0))
//        assertTrue((newsBlock.exists())&&(claimsBlock.exists()))
        assertTrue(dWizard.mainNewsBlock(device)!!.exists())
    }

    @Test
    fun mainMenuNewsExpand() {
     //   dWizard.mainNewsBlock(device)
     //  var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0));
        val shortNewsTabHeight =   dWizard.mainNewsBlock(device)!!.bounds.height()
    //    newsTab.click()
        dWizard.mainNewsBlock(device)!!.click()
        val longNewsTabHeight =   dWizard.mainNewsBlock(device)!!.bounds.height()
        assertTrue(longNewsTabHeight>shortNewsTabHeight)
    }

    @Test
    fun mainMenuButterfly() {
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
//        val claimsLink = UiObject(UiSelector().text("About"))
//        claimsLink.click()
        dWizard.mainMenu(device)!!.click()
        dWizard.nameAbout().click()
        device.waitForIdle(500L);
      // val newsFld = UiObject(UiSelector().text("About"));
        assertTrue( dWizard.aboutPrivacyBlock(device)!!.exists());
    }


    @Test
    fun mainMenuClaimsClick() {

        //device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/expand_material_button").instance(0)).click()
        dWizard.mainNewsBlockCollapseBtn(device)!!.click()
       // device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
        dWizard.mainClaimCard(device)!!.click()
        //val claimsCard =    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/executor_name_text_view").instance(0)).exists()
        assertTrue(dWizard.claimCardInTitle(device)!!.exists())
    }

}
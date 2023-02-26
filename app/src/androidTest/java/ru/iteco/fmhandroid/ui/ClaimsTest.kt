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
import ru.iteco.fmhandroid.ui.datawizard.Datawizard


@RunWith(AndroidJUnit4::class)
class ClaimsTest {
    private lateinit var device: UiDevice;
    private lateinit var  dWizard : Datawizard;
private val executedCard = "Executed"
private val inProgresCard = "In progress"
private val canceledCard = "Canceled"
private val OPEN_CARD = "Open"
private val blankFilter = "There is nothing here yet"

    //  private lateinit var mDevice: UiDevice
    //   private val BASIC_SAMPLE_PACKAGE = "com.example.android......" // change this to your app's package name
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
    fun leftMenuClaims() {

        dWizard.mainMenu(device)!!.click()
        dWizard.nameClaim().click()
        assertTrue(dWizard.nameClaim().exists())
       // device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
      //  val claimsLink = UiObject(UiSelector().text("Claims"))
      //  claimsLink.click()

    }
    @Test
    fun mainMenuAllClaims() {
        dWizard.mainAllClaims(device)!!.click()
   //     device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
    //    val claimsFld = UiObject(UiSelector().text("Claims"));
        //dWizard.clai
        assertTrue(dWizard.allClaimCardInList(device,0)!!.exists());
    }

    @Test
    fun claimsFilterOpen() {
        dWizard.mainAllClaims(device)!!.click()
    //    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
        dWizard.allClaimFilterBtn(device)!!.click()
    //    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();

    dWizard.filterClaimInProgress(device)!!.click()
     //   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)).click();
    dWizard.filterClaimOkBtn(device)!!.click()
    //    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
     dWizard.allClaimCardInList(device,0)!!.click()
      //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
      //  var typeCard =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0))
        assertEquals(OPEN_CARD,dWizard.claimCardStatus(device)!!.text)

    }
    @Test
    fun claimsFilterBlank() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
    val   blankClaims=   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_retry_material_button").instance(0))
    assertTrue(blankClaims.exists());

    }
    @Test
    fun claimsFilterInProgress() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
       device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
         var typeCard =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0))
        assertEquals(inProgresCard,typeCard.text)

    }

    @Test
    fun claimsFilterExecuted() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_executed").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
        var typeCard =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0))
        assertEquals(executedCard,typeCard.text)
    }

    @Test
    fun claimsFilterCanceled() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_cancelled").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
        var typeCard =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0))
        assertEquals(canceledCard,typeCard.text)
    }



}
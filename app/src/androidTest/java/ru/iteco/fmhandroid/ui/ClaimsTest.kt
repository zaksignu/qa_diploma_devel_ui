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

    // Тест 1.6
    @Test
    fun leftMenuClaims() {

        dWizard.mainMenu(device)!!.click()
        dWizard.nameClaim().click()
        device.waitForIdle(500L);
        assertTrue(dWizard.nameClaim().exists())

    }

    // Тест 2.4
    @Test
    fun mainMenuAllClaims() {
        dWizard.mainAllClaims(device)!!.click()

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
        assertEquals(dWizard.OPEN_CARD,dWizard.claimCardStatus(device)!!.text)

    }
    @Test
    fun claimsFilterBlank() {
        dWizard.mainAllClaims(device)!!.click()

      //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
        dWizard.allClaimFilterBtn(device)!!.click()

      //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();
      //  dWizard.filterClaimInProgress(device)!!.click()
        dWizard.filterClaimOk(device)!!.click()
        dWizard.filterClaimInProgress(device)!!.click()
    //    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)).click();
     //   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)).click();
        dWizard.filterClaimOkBtn(device)!!.click()
      //  dWizard.filterClaimOkBtn(device)!!.click()
    //    dWizard.allClaimCardInList(device,0)!!.click()
        //    device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
       // dWizard.allClaimCardInList(device,0)!!.click()
      //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
   // val   blankClaims=   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_retry_material_button").instance(0))
    assertTrue(dWizard.allClaimBlankResult(device)!!.exists());

    }
    @Test
    fun claimsFilterInProgress() {

        dWizard.mainAllClaims(device)!!.click()
          dWizard.allClaimFilterBtn(device)!!.click()
        dWizard.filterClaimOk(device)!!.click()
         dWizard.filterClaimOkBtn(device)!!.click()
         dWizard.allClaimCardInList(device,0)!!.click()
        assertEquals(dWizard.IN_PROGRESS_CARD,dWizard.claimCardStatus(device)!!.text)



    }

    @Test
    fun claimsFilterExecuted() {
        dWizard.mainAllClaims(device)!!.click()
        dWizard.allClaimFilterBtn(device)!!.click()
         dWizard.filterClaimOk(device)!!.click()
         dWizard.filterClaimInProgress(device)!!.click()
         dWizard.filterClaimExecuted(device)!!.click()
        dWizard.filterClaimOkBtn(device)!!.click()
        dWizard.allClaimCardInList(device,0)!!.click()
        assertEquals(dWizard.EXECUTED_CARD,dWizard.claimCardStatus(device)!!.text)
//
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_executed").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0)).click();
//        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)).click()
//        var typeCard =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0))
//        assertEquals(executedCard,typeCard.text)
    }

    @Test
    fun claimsFilterCanceled() {

        dWizard.mainAllClaims(device)!!.click()
        dWizard.allClaimFilterBtn(device)!!.click()
        dWizard.filterClaimOk(device)!!.click()
        dWizard.filterClaimInProgress(device)!!.click()
        dWizard.filterClaimCancelled(device)!!.click()
        dWizard.filterClaimOkBtn(device)!!.click()
        dWizard.allClaimCardInList(device,0)!!.click()
        assertEquals(dWizard.CANCELED_CARD,dWizard.claimCardStatus(device)!!.text)

    }


    @Test
    fun claimCardMakeCancel() {
//TODO;;;;
        dWizard.mainAllClaims(device)!!.click()
        while(true){

        dWizard.allClaimCardInList(device,0)!!.click()
        dWizard.claimCardStatusProcess(device)!!.click()
            device.waitForIdle(500L)
        dWizard.nameCancel().click()
        dWizard.claimCardReturnBtn(device)!!.click()}

//        dWizard.claimCreateTitle(device)!!.text = dWizard.CLAIM_CREATE_TITLE
//        dWizard.claimCreateExecutor(device)!!.click()
//
//        dWizard.clickIt(device,dWizard.claimCreateExecutor(device)!!.bounds.centerX(),
//            dWizard.claimCreateExecutor(device)!!.bounds.centerY() + dWizard.claimCreateExecutor(device)!!.bounds.height())
//
//        dWizard.claimCreateDate(device)!!.text = dWizard.CLAIM_CREATE_DATE
//        dWizard.claimCreateTime(device)!!.text = dWizard.CLAIM_CREATE_TIME
//        dWizard.claimCreateDescription(device)!!.text = dWizard.CLAIM_CREATE_DESCRIPTION
////

//        assertTrue(dWizard.mainNewsBlock(device)!!.isClickable)
//        val longBlock = dWizard.mainWholeClaimsBlock(device)!!.bounds.height()
//        dWizard.mainBlockCollapseBtn(device,1)!!.click()
//        val shortBlock = dWizard.mainWholeClaimsBlock(device)!!.bounds.height()
//        assertTrue(longBlock>shortBlock)
    }


}
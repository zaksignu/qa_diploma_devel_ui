package ru.iteco.fmhandroid.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.CoreMatchers
import org.json.JSONTokener
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.ui.datawizard.Datawizard
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(AllureAndroidJUnit4::class)
//@RunWith(AndroidJUnit4::class)
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

    // Тест 4.1
    @Test
    fun claimsFilterOpen() {
        dWizard.mainAllClaims(device)!!.click()

        dWizard.allClaimFilterBtn(device)!!.click()
        dWizard.filterClaimInProgress(device)!!.click()
        dWizard.filterClaimOkBtn(device)!!.click()

        dWizard.allClaimCardInList(device,0)!!.click()
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

    // Тест 4.2
    @Test
    fun claimsFilterInProgress() {

        dWizard.mainAllClaims(device)!!.click()
          dWizard.allClaimFilterBtn(device)!!.click()
        dWizard.filterClaimOk(device)!!.click()
         dWizard.filterClaimOkBtn(device)!!.click()
         dWizard.allClaimCardInList(device,0)!!.click()
        assertEquals(dWizard.IN_PROGRESS_CARD,dWizard.claimCardStatus(device)!!.text)



    }
    // Тест 4.3
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

    }
    // Тест 4.4
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


    // Тест 2.4
    @Test
    fun claimsOneCard() {
        dWizard.mainAllClaims(device)!!.click()
        dWizard.allClaimCardInList(device,0)!!.click()
        assertTrue(dWizard.allClaimCardInList(device,0)!!.exists());
    }

    // Тест 4.5
    @Test
    fun claimCardAddCard() {
        dWizard.mainAllClaims(device)!!.click()


//        dWizard.allClaimAddClaim(device)!!.click()
//        val dat
    //        eTime = LocalDateTime.now()
////               .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))
//            .format(DateTimeFormatter.ofPattern("dd.MM.yyy"))
//
//        dWizard.claimCreateTitle(device)!!.text = dWizard.CLAIM_CREATE_TITLE
//        dWizard.claimCreateExecutor(device)!!.click()
//                dWizard.clickIt(device,dWizard.claimCreateExecutor(device)!!.bounds.centerX(),
//            dWizard.claimCreateExecutor(device)!!.bounds.centerY() + dWizard.claimCreateExecutor(device)!!.bounds.height())
//        dWizard.claimCreateDate(device)!!.text = dWizard.CLAIM_CREATE_DATE
//     //  dWizard.claimCreateDate(device)!!.text = dateTime
//        //    dWizard.CLAIM_CREATE_DATE
//        dWizard.claimCreateTime(device)!!.text = dWizard.CLAIM_CREATE_TIME
//        dWizard.claimCreateDescription(device)!!.text = dWizard.CLAIM_CREATE_DESCRIPTION
//dWizard.claimCreateSaveBtn(device)



//            val dateTime = LocalDateTime.now()
//             //   .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))
//                .format(DateTimeFormatter.ofPattern("dd.MM.yyy"))
//        Log.d(ContentValues.TAG, "///////1");
//        Log.d(ContentValues.TAG,dateTime);
         //   println(dateTime) // 01 января 2017 г., 22:27:41


//TODO;;;;
    }



}
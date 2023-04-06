package ru.iteco.fmhandroid.ui

import android.content.Context
import android.content.Intent
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
class MainMenuTest {
    private lateinit var device: UiDevice;
    private lateinit var  dWizard : Datawizard;

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
       // device.waitForIdle(1500L)


    }
// Тест 1.4
    @Test
    fun leftMenuMain() {
        dWizard.mainMenu(device)!!.click()
        dWizard.nameNews().click()
        dWizard.mainMenu(device)!!.click()
        dWizard.nameMain().click()

        assertTrue(dWizard.mainNewsBlock(device)!!.exists())
    }


    // Тест 2.1
    @Test
    fun mainMenuOneNewsExpand() {
        assertTrue(dWizard.mainNewsBlock(device)!!.isClickable)
        val shortNewsTabHeight =   dWizard.mainNewsBlock(device)!!.bounds.height()
        dWizard.mainNewsBlock(device)!!.click()
        val longNewsTabHeight =   dWizard.mainNewsBlock(device)!!.bounds.height()
        assertTrue(longNewsTabHeight>shortNewsTabHeight)
    }

    // Тест 2.2
    @Test
    fun mainMenuNewsBlockExpand() {
        assertTrue(dWizard.mainNewsBlock(device)!!.isClickable)
        val longBlock = dWizard.mainWholeNewsBlock(device)!!.bounds.height()
        dWizard.mainBlockCollapseBtn(device,0)!!.click()
        val shortBlock = dWizard.mainWholeNewsBlock(device)!!.bounds.height()
        assertTrue(longBlock>shortBlock)
    }

    // Тест 2.5
    @Test
    fun mainMenuClaimBlockExpand() {
        assertTrue(dWizard.mainNewsBlock(device)!!.isClickable)
        val longBlock = dWizard.mainWholeClaimsBlock(device)!!.bounds.height()
        dWizard.mainBlockCollapseBtn(device,1)!!.click()
        val shortBlock = dWizard.mainWholeClaimsBlock(device)!!.bounds.height()
        assertTrue(longBlock>shortBlock)
    }

    // Тест 2.6
    @Test
    fun mainMenuClaimAddNew() {
    //TODO AddAssertions
        dWizard.mainClaimAddNew(device)!!.click()
        dWizard.claimCreateTitle(device)!!.text = dWizard.CLAIM_CREATE_TITLE
        dWizard.claimCreateExecutor(device)!!.click()

        dWizard.clickIt(device,dWizard.claimCreateExecutor(device)!!.bounds.centerX(),
            dWizard.claimCreateExecutor(device)!!.bounds.centerY() + dWizard.claimCreateExecutor(device)!!.bounds.height())

        dWizard.claimCreateDate(device)!!.text = dWizard.CLAIM_CREATE_DATE
        dWizard.claimCreateTime(device)!!.text = dWizard.CLAIM_CREATE_TIME
        dWizard.claimCreateDescription(device)!!.text = dWizard.CLAIM_CREATE_DESCRIPTION
        dWizard.claimCreateSaveBtn(device)!!.click()
        dWizard.allClaimCardInList(device,0)!!.click()
      //  assertTrue((dWizard.claimCardInTitle(device)!!.text === dWizard.CLAIM_CREATE_TITLE))
        dWizard.claimCardStatusProcess(device)!!.click()
        device.waitForIdle(500L)
        dWizard.nameThrownOff().click()
        dWizard.claimCardThrowOffComment(device)!!.text = "1"
        dWizard.popUpOkBtn(device)!!.click()

        dWizard.claimCardStatusProcess(device)!!.click()
        device.waitForIdle(500L)
        dWizard.nameCancel().click()
        dWizard.claimCardReturnBtn(device)!!.click()

    }

    // Тест 2.7
    @Test
    fun mainMenuClaimFirstInBlock() {
        dWizard.mainBlockCollapseBtn(device,0)!!.click()
        dWizard.allClaimCardInList(device,0)!!.click()

        assertTrue(dWizard.claimCardInTitle(device)!!.exists())

    }




}
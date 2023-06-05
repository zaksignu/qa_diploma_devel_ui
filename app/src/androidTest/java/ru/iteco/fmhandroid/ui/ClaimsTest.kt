package ru.iteco.fmhandroid.ui


//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.espresso.action.ViewActions.*

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.R
import ru.iteco.fmhandroid.ui.datawizard.Datawizard


@RunWith(AndroidJUnit4::class)
class ClaimsTest {
    private lateinit var device: UiDevice
    private lateinit var dWizard: Datawizard

//    @Rule
//     val  activityTestRule: ActivityTestRule<AppActivity> = ActivityTestRule(AppActivity::class.java)


    @Before
    fun startMainActivityFromHomeScreen() {
        dWizard = Datawizard()
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName
        //assertThat(launcherPackage, CoreMatchers.notNullValue())

        device.wait(
            Until.hasObject(By.pkg(launcherPackage)),
            dWizard.LAUNCH_TIMEOUT
        )
        // Launch the blueprint app
        val context = ApplicationProvider.getApplicationContext<Context>()


        val packageName = context.packageName
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)



        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), dWizard.LAUNCH_TIMEOUT)
        //
        // Wait for the app to appear

        device.wait(
            Until.hasObject(
                By.pkg(packageName)
                    .depth(0)
            ),
            dWizard.LAUNCH_TIMEOUT
        )
        try {

            dWizard.mainTradeMark(device)!!.isEnabled

        } catch (e: UiObjectNotFoundException) {
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
        }


    }

    // Тест 1.6
    @Test
    fun leftMenuClaims() {

        dWizard.mainMenu(device)!!.click()
        dWizard.nameClaim().click()
        device.waitForIdle(500L)
        assertTrue(dWizard.nameClaim().exists())

    }

    // Тест 2.4
    @Test
    fun mainMenuAllClaims() {
        dWizard.mainAllClaims(device)!!.click()

        assertTrue(dWizard.allClaimCardInList(device, 0)!!.exists())
    }

    // Тест 4.1
    @Test
    fun claimsFilterOpen() {
        dWizard.mainAllClaims(device)!!.click()

        dWizard.allClaimFilterBtn(device)!!.click()
        dWizard.filterClaimInProgress(device)!!.click()
        dWizard.filterClaimOkBtn(device)!!.click()

        dWizard.allClaimCardInList(device, 0)!!.click()
        assertEquals(dWizard.OPEN_CARD, dWizard.claimCardStatus(device)!!.text)

    }

    // Тест 4.2
    @Test
    fun claimsFilterInProgress() {

        dWizard.mainAllClaims(device)!!.click()
        dWizard.allClaimFilterBtn(device)!!.click()
        dWizard.filterClaimOk(device)!!.click()
        dWizard.filterClaimOkBtn(device)!!.click()
        dWizard.allClaimCardInList(device, 0)!!.click()
        assertEquals(dWizard.IN_PROGRESS_CARD, dWizard.claimCardStatus(device)!!.text)

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

        dWizard.allClaimCardInList(device, 0)!!.click()
        assertEquals(dWizard.EXECUTED_CARD, dWizard.claimCardStatus(device)!!.text)

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

        dWizard.allClaimCardInList(device, 0)!!.click()
        assertEquals(dWizard.CANCELED_CARD, dWizard.claimCardStatus(device)!!.text)
    }


    // Тест 2.4
    @Test
    fun claimsOneCard() {
        dWizard.mainAllClaims(device)!!.click()
        dWizard.allClaimCardInList(device, 0)!!.click()
        assertTrue(dWizard.allClaimCardInList(device, 0)!!.exists())
    }

    @Test
    fun scrollToItemBelowFold_checkItsText() {
        dWizard.mainAllClaims(device)!!.click()

        onView(ViewMatchers.withId(R.id.description_material_text_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )



    }


    }

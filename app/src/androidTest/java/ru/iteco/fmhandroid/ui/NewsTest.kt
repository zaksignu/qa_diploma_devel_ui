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
class NewsTest {
    private lateinit var device: UiDevice;
    private lateinit var dWizard: Datawizard;

    private val LAUNCH_TIMEOUT = 5000L

    @Before
    fun startMainActivityFromHomeScreen() {
        dWizard = Datawizard()
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName;
        assertThat(launcherPackage, CoreMatchers.notNullValue())

        device.wait(
            Until.hasObject(By.pkg(launcherPackage)),
            LAUNCH_TIMEOUT
        )
        // Launch the blueprint app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val packageName = context.packageName;
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear out any previous instances

        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), LAUNCH_TIMEOUT)
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

    // Тест 1.5
    @Test
    fun leftMenuNews() {
        dWizard.mainMenu(device)!!.click()
        dWizard.nameNews().click()

        device.waitForIdle(500L);
        assertTrue(dWizard.nameNews().exists())

    }

    @Test
    fun allNewsExpand() {

        dWizard.mainAllNews(device)!!.click()
        //     device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click()
        //  dWizard.newsNthCard(device,0)!!.click()
        //   var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0));
        val shortNewsTabHeight = dWizard.newsNthCard(device, 0)!!.bounds.height();
//        Log.d(TAG, "22222222");
//        Log.d(TAG, dWizard.newsNthCard(device,0)!!.bounds.top.toString());
//        Log.d(TAG, dWizard.newsNthCard(device,0)!!.bounds.bottom.toString());
//        Log.d(TAG, dWizard.newsNthCard(device,0)!!.bounds.left.toString());
//        Log.d(TAG, dWizard.newsNthCard(device,0)!!.bounds.right.toString());

        dWizard.newsNthCard(device, 0)!!.click()
        val longNewsTabHeight = dWizard.newsNthCard(device, 0)!!.bounds.height()
//        Log.d(TAG, longNewsTabHeight.toString());
//        Log.d(TAG, "33333333");
        assertTrue(longNewsTabHeight > shortNewsTabHeight)

    }
// Тест 2.3
    @Test
    fun mainMenuAllNews() {
        dWizard.mainAllNews(device)!!.click()
        device.waitForIdle(500L);

        assertTrue(dWizard.nameNews().exists())

    }

    @Test
    fun allNewsArrangeIt() {
        dWizard.mainAllNews(device)!!.click()
        // для полноценной проверки требуется работа с базами данных, что выходит за рамки задания этой курсовой
        //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        //   var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0));
        val firstTabText = dWizard.newsNthCardTitle(device, 0)!!.text
        //   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/sort_news_material_button").instance(0)).click();
        dWizard.newsSortBtn(device)!!.click()
        val secondTabText = dWizard.newsNthCardTitle(device, 0)!!.text
        assertTrue(!(firstTabText === secondTabText))

    }

    @Test
    fun allNewsFilterItAnnoun() {
        dWizard.mainAllNews(device)!!.click()

        // device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();

        dWizard.newsFiltrBtn(device)!!.click()
        //   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button").instance(0)).click();
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        //   device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_ANNOY)
        device.waitForIdle(500L);
        //  device.click(oneX, announY)
//        dWizard.filterNewsFirstDate(device)!!.text = dWizard.FIRST_ANNOY_DATE
  //      device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_start_text_input_edit_text").instance(0)).setText("08.01.2023");
                 dWizard.filterNewsFirstDate(device)!!.text = dWizard.FIRST_ANNOY_DATE
        dWizard.filterNewsLastDate(device)!!.text = dWizard.LAST_ANNOY_DATE
        dWizard.filterNewsOkBtn(device)!!.click()
        //device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button").instance(0)).click();
        //    val filterTxt =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0)).text
        //     assertTrue(filterTxt.contains("Объявление"))
        // dWizard.newsSortBtn(device)
        //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_start_text_input_edit_text").instance(0)).setText("08.01.2023");
        //  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_end_text_input_edit_text").instance(0)).setText("31.01.2023");
        // 12-01
        //  25-02
        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_ANNOY))

    }

    @Test
    fun allNewsFilterItHb() {
        dWizard.mainAllNews(device)!!.click()

        dWizard.newsFiltrBtn(device)!!.click()

        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);

        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_HB)

        dWizard.filterNewsOkBtn(device)!!.click()



        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_HB))



    }


    @Test
    fun allNewsDateFilter() {
        dWizard.mainAllNews(device)!!.click()

        dWizard.newsFiltrBtn(device)!!.click()

        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);

        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_HB)

        dWizard.filterNewsFirstDate(device)!!.text = dWizard.FIRST_TERRY_DATE
        dWizard.filterNewsLastDate(device)!!.text = dWizard.LAST_TERRY_DATE

        dWizard.filterNewsOkBtn(device)!!.click()



        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_HB))


    }


}
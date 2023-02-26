package ru.iteco.fmhandroid.ui

import android.content.ContentValues.TAG
import  ru.iteco.fmhandroid.dao.NewsDao
import  ru.iteco.fmhandroid.db.DbModule

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.dao.DaoModule
import ru.iteco.fmhandroid.ui.datawizard.Datawizard


@RunWith(AndroidJUnit4::class)
class NewsTest {
    private lateinit var device: UiDevice;
    private lateinit var  dWizard : Datawizard;

    private var oneX = 540;

    private var announY = 530;
    private var hbY = 700;

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


    @Test
    fun leftMenuNews() {

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)).click();
        val claimsLink = UiObject(UiSelector().text("News"))
        claimsLink.click()

        device.waitForIdle(500L);
        val newsFld = UiObject(UiSelector().text("News"));

        assertTrue(newsFld.exists());
    }

    @Test
    fun allNewsExpand() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click()
        var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0));
        val shortNewsTabHeight =   newsTab.bounds.height()
        newsTab.click()
        val longNewsTabHeight =   newsTab.bounds.height()
        assertTrue(longNewsTabHeight>shortNewsTabHeight)

    }

    @Test
    fun mainMenuAllNews() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        device.waitForIdle(500L);
        val newsFld = UiObject(UiSelector().text("News"));
        assertTrue(newsFld.exists());

    }

    @Test
    fun allNewsArrangeIt(){
        // для полноценной проверки требуется работа с базами данных, что выходит за рамки задания этой курсовой
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0));
        val hiNewsTabHeight =   newsTab.text
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/sort_news_material_button").instance(0)).click();
        val lowNewsTabHeight =   newsTab.text
        assertTrue(!(hiNewsTabHeight===lowNewsTabHeight))

    }

    @Test
    fun allNewsFilterItAnnoun(){
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneX, announY)
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button").instance(0)).click();
         val filterTxt =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0)).text
        assertTrue(filterTxt.contains("Объявление"))

    }

    @Test
    fun allNewsFilterItHb(){

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneX, hbY)
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button").instance(0)).click();
        val filterTxt =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0)).text
        assertTrue(filterTxt.contains("День рождения"))

    }


    @Test
    fun allNewsDateFilter(){
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneX, hbY)
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_start_text_input_edit_text").instance(0)).setText("08.01.2023");
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_end_text_input_edit_text").instance(0)).setText("31.01.2023");
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button").instance(0)).click();
        val filterTxt =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0)).text
        assertTrue(filterTxt.contains("Terry"))
    }



}
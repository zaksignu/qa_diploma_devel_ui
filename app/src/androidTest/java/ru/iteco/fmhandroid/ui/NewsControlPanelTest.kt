package ru.iteco.fmhandroid.ui

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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AllureAndroidJUnit4::class)
//@RunWith(AndroidJUnit4::class)
class NewsControlPanelTest {
    private lateinit var device: UiDevice;


    private var oneX = 400;
    private var oneXcategory = 300;

    private var announY = 530;
    private var announYcategory = 670;

    private val addNewsTitle = "Бобрятник в Бобруйске"
    private val addNewsTitleNew = "Бобруйск в бобрятнике"
    private val addNewsDate = "12.12.2024"
    private val addNewsDateNew = "12.12.2034"
    private val addNewsTime = "10:45"
    private val addNewsDescription = "Боброводы Бобруйска"
    private val addNewsDescriptioNew = "Бобруйские боброводы"


    private val activeMark = "Active"
    private val notActiveMark = "Not active"

    private val activeLabel= "ACTIVE"
    private val notActiveLabel = "NOT ACTIVE"

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
    fun newsPanelAddNew() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/add_news_image_view").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneXcategory, announYcategory)
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_input_edit_text").instance(0)).setText(addNewsTitle);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_text_input_edit_text").instance(0)).setText(addNewsDate);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_time_text_input_edit_text").instance(0)).setText(addNewsTime);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_description_text_input_edit_text").instance(0)).setText(addNewsDescription);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/switcher").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button").instance(0)).click()
        val news =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0))
        assertEquals(addNewsTitle,news.text.toString());
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/delete_news_item_image_view").instance(0)).click()
        val ok = UiObject(UiSelector().text("OK"))
        ok.click()

    }

    @Test
    fun newsPanelExpand() {
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button").instance(0)).click()
        var newsTab = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0));
        val shortNewsTabHeight =   newsTab.bounds.height()
        newsTab.click()

        val longNewsTabHeight =   newsTab.bounds.height()
        assertTrue(longNewsTabHeight>shortNewsTabHeight)

    }

    @Test
    fun newsPanelDelete() {

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/add_news_image_view").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneXcategory, announYcategory)
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_input_edit_text").instance(0)).setText(addNewsTitle);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_text_input_edit_text").instance(0)).setText(addNewsDate);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_time_text_input_edit_text").instance(0)).setText(addNewsTime);

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_description_text_input_edit_text").instance(0)).setText(addNewsDescription);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/switcher").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button").instance(0)).click()
        val newNews =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0))
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/delete_news_item_image_view").instance(0)).click()

        val okButton = UiObject(UiSelector().text("OK"))

        okButton.click()
        val oldNews =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0))
        assertNotEquals(newNews,oldNews)

    }

    @Test
    fun newsPanelEdit(){
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/add_news_image_view").instance(0)).click()

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneXcategory, announYcategory)

        var newsTitle = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_input_edit_text").instance(0))
        newsTitle.text = addNewsTitle
        var newsDate = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_text_input_edit_text").instance(0))
        newsDate.text = addNewsDate;
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_time_text_input_edit_text").instance(0)).setText(addNewsTime);
        var newsDesc = device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_description_text_input_edit_text").instance(0))
        newsDesc.setText(addNewsDescription);
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button").instance(0)).click()


        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_item_image_view").instance(0)).click()
        newsTitle.text = addNewsTitleNew
        newsDate.text =addNewsDateNew
        newsDesc.text = addNewsDescriptioNew
          device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button").instance(0)).click()
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0)).click();

        assertEquals(addNewsTitleNew, device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(0)).text.toString());
        assertEquals(addNewsDateNew,device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publication_date_text_view").instance(0)).text.toString());
        assertEquals(addNewsDescriptioNew,device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_description_text_view").instance(0)).text.toString());
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/delete_news_item_image_view").instance(0)).click()

        val ok = UiObject(UiSelector().text("OK"))

        ok.click()

    }

    @Test
    fun allNewsFilterItAnnounActive(){


        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button").instance(0)).click()

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button").instance(0)).click();

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneX, announY)

        val chBox = UiObject(UiSelector().text(notActiveMark))
        device.click((chBox.bounds.left+(2*chBox.bounds.height()%2)),(chBox.bounds.top+(chBox.bounds.width()%8)))

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button").instance(0)).click();

        val filterTxt =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_published_text_view").instance(0)).text
        assertTrue(filterTxt.equals(activeLabel))

    }
    @Test
    fun allNewsFilterItAnnounNotActive(){


        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)).click();
        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button").instance(0)).click()

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button").instance(0)).click();

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view").instance(0)).click();
        device.click(oneX, announY)

        val chBox = UiObject(UiSelector().text(activeMark))
        device.click((chBox.bounds.left+(2*chBox.bounds.height()%2)),(chBox.bounds.top+(chBox.bounds.width()%8)))

        device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button").instance(0)).click();

        val filterTxt =  device.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_published_text_view").instance(0)).text
        assertTrue(filterTxt.equals(notActiveLabel))
    }



}
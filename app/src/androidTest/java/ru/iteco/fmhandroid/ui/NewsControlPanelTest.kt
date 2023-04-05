package ru.iteco.fmhandroid.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.ui.datawizard.Datawizard

@RunWith(AllureAndroidJUnit4::class)
//@RunWith(AndroidJUnit4::class)
class NewsControlPanelTest {
    private lateinit var device: UiDevice;
    private lateinit var dWizard: Datawizard;


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


    }

    // Тест 3.17
    @Test
    fun newsPanelArrangeIt() {

        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        // для полноценной проверки требуется работа с базами данных, что выходит за рамки задания этой курсовой

        val firstTabText = dWizard.newsNthCardTitle(device, 0)!!.text
        dWizard.newsSortBtn(device)!!.click()
        val secondTabText = dWizard.newsNthCardTitle(device, 0)!!.text

        assertTrue(!(firstTabText === secondTabText))

    }



    // Тест 3.10
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItAnnoun() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_ANNOY)
        device.waitForIdle(500L);
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.FIRST_ANNOY_DATE
        dWizard.filterNewsLastDate(device)!!.text = dWizard.LAST_ANNOY_DATE
        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_ANNOY))

    }

    // Тест 3.11
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItHB() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_HB)
        device.waitForIdle(500L);

        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_HB))

    }

    // Тест 3.12
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItSal() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_SALAR)
        device.waitForIdle(500L);

        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_SALAR))

    }

    // Тест 3.13
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItUni() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_UNION)
        device.waitForIdle(500L);

        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_UNION))

    }

    // Тест 3.14
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItHol() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_HOL)
        device.waitForIdle(500L);

        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_HOL))

    }


    // Тест 3.15
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItMass() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_MASS)
        device.waitForIdle(500L);

        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_MASS))

    }

    // Тест 3.16
    // для полноценного теста необходима работа с БД
    @Test
    fun newsPanelFilterItGrat() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsCategory(device)!!.click()
        device.waitForIdle(500L);
        dWizard.clickIt(device, dWizard.COORD_X, dWizard.COORD_Y_GRAT)
        device.waitForIdle(500L);

        dWizard.filterNewsOkBtn(device)!!.click()


        assertTrue(dWizard.newsNthCardTitle(device, 0)!!.text.contains(dWizard.CONTENT_GRAT))

    }

    // Тест 3.18
    @Test
    fun newsPanelAddOneNewsAnnoy() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()

        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
            dWizard.addNewsCategory(device)!!.bounds.centerY() +
                    dWizard.addNewsCategory(device)!!.bounds.height())
        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()

        dWizard.mainMenu(device)!!.click()
        dWizard.nameMain().click()

        assertTrue( dWizard.newsNthCardTitle(device,0)!!.text == dWizard.ADD_NEWS_TITLE_ORIG)

        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        dWizard.newsCpDeleteNews(device,0)!!.click()
        dWizard.popUpOkBtn(device)!!.click()

    }
    // Тест 3.19
    @Test
    fun newsPanelDelete() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()

        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
            dWizard.addNewsCategory(device)!!.bounds.centerY() +
                    dWizard.addNewsCategory(device)!!.bounds.height())
        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        dWizard.newsCpDeleteNews(device,0)!!.click()
        dWizard.popUpOkBtn(device)!!.click()
        assertTrue(dWizard.newsCPBlank(device)!!.exists())


    }


    // Тест 3.20
    //TODO доделать ассерт

    @Test
    fun newsPanelEditTitle() {
        //открываем новостную админку
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()
    //Добавляем новость
        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
            dWizard.addNewsCategory(device)!!.bounds.centerY() +
                    dWizard.addNewsCategory(device)!!.bounds.height())
        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()
        var qaqa = dWizard.newsNthCardTitle(device,0)
        device.waitForIdle(500L);
        //Выводим новость через фыильтр по дате
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        device.waitForIdle(500L);

    // Редактируем первую новость
        dWizard.newsCpEditNews(device,0)!!.click()
        dWizard.editNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_EDITED
        dWizard.addNewsSaveBtn(device)!!.click()

        dWizard.newsNthCardTitle(device,0)!!.click()

        device.waitForIdle(500L);

        assertEquals( dWizard.ADD_NEWS_TITLE_EDITED, dWizard.newsNthCardTitle(device,0)!!.text.toString())

        //Удаляем добавленную новость

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        dWizard.newsCpDeleteNews(device,0)!!.click()
        dWizard.popUpOkBtn(device)!!.click()



    }


    // Тест 3.21
    //TODO доделать ассерт.Остальное работает
//
    @Test
    fun newsPanelEditCategory() {

        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()

        //Добавляем новость
        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device, dWizard.addNewsCategory(device)!!.bounds.centerX()-100,
                                dWizard.addNewsCategory(device)!!.bounds.centerY()+
                                        dWizard.COORD_D_Y_ANNOY)

        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()
        device.waitForIdle(500L);

        //Выводим новость через фыильтр по дате
        dWizard.newsFiltrBtn(device)!!.click()

        dWizard.filterNewsCategory(device)!!.click();
        dWizard.clickIt(device, dWizard.addNewsCategory(device)!!.bounds.centerX()-100,
            dWizard.addNewsCategory(device)!!.bounds.centerY()+
                    dWizard.COORD_D_Y_ANNOY)
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        device.waitForIdle(500L);

        // Редактируем  первую новость
        dWizard.newsCpEditNews(device,0)!!.click()

        // Редактируем категорию
        dWizard.filterNewsCategory(device)!!.click();
        dWizard.clickIt(device, dWizard.addNewsCategory(device)!!.bounds.centerX()-100,
            dWizard.addNewsCategory(device)!!.bounds.centerY()+
                    dWizard.COORD_D_Y_HB)

        dWizard.addNewsSaveBtn(device)!!.click()
        device.waitForIdle(500L);

       //фильтруем результат и получаем отредактируемую новость
        dWizard.newsFiltrBtn(device)!!.click()

        dWizard.filterNewsCategory(device)!!.click();
        dWizard.clickIt(device, dWizard.addNewsCategory(device)!!.bounds.centerX()-100,
            dWizard.addNewsCategory(device)!!.bounds.centerY()+
                    dWizard.COORD_D_Y_HB)

        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()

        //Удаляем добавленную новость

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        dWizard.newsCpDeleteNews(device,0)!!.click()
        dWizard.popUpOkBtn(device)!!.click()

    }


    ////

    // Тест 3.22
    //TODO доделать ассерт.Остльное работает

    @Test
    fun newsPanelEditDate() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()

        //Добавляем новость
        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
            dWizard.addNewsCategory(device)!!.bounds.centerY() +
                    dWizard.addNewsCategory(device)!!.bounds.height())


        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()
        device.waitForIdle(500L);

        //Выводим новость через фыильтр по дате
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        device.waitForIdle(500L);
        // Редактируем  первую новость
        dWizard.newsCpEditNews(device,0)!!.click()
        // Редактируем дату
        dWizard.editNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_EDITED

        dWizard.addNewsSaveBtn(device)!!.click()
        device.waitForIdle(500L);

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_EDITED
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_EDITED
        dWizard.filterNewsOkBtn(device)!!.click()


        //Удаляем добавленную новость

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_EDITED
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_EDITED
        dWizard.filterNewsOkBtn(device)!!.click()
        dWizard.newsCpDeleteNews(device,0)!!.click()
        dWizard.popUpOkBtn(device)!!.click()




    }

// Тест 3.23

    @Test
    fun newsPanelEditDescription() {
        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()

        //Добавляем новость
        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
            dWizard.addNewsCategory(device)!!.bounds.centerY() +
                    dWizard.addNewsCategory(device)!!.bounds.height())


        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()

        device.waitForIdle(500L);

        //Выводим новость через фыильтр по дате
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        device.waitForIdle(500L);
        dWizard.newsNthCard(device,0)!!.click()
        // Редактируем  первую новость
        dWizard.newsCpEditNews(device,0)!!.click()
        // Редактируем описание
        dWizard.editNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_EDITED


        dWizard.addNewsSaveBtn(device)!!.click()
        device.waitForIdle(500L);
        dWizard.newsNthCard(device,0)!!.click()

        assertTrue( dWizard.newsCpNthNewsDescription(device,0)!!.text.toString() == dWizard.ADD_NEWS_DESCR_EDITED.toString())


        //Удаляем добавленную новость

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsOkBtn(device)!!.click()
        dWizard.newsCpDeleteNews(device,0)!!.click()
        dWizard.popUpOkBtn(device)!!.click()



    }

    // Тест 3.24
    //TODO: добавить ассерты
    @Test
    fun newsPanelEditStatusActive(){

    dWizard.mainAllNews(device)!!.click()
    dWizard.newsBtnToCP(device)!!.click()
    dWizard.newsCpAddNews(device)!!.click()

    //Добавляем новость
    dWizard.addNewsCategory(device)!!.click()
    dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
        dWizard.addNewsCategory(device)!!.bounds.centerY() +
                dWizard.addNewsCategory(device)!!.bounds.height())


    dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
    dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
    dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
    dWizard.addNewsSaveBtn(device)!!.click()
    device.waitForIdle(500L);

    //Выводим новость через фыильтр по дате
    dWizard.newsFiltrBtn(device)!!.click()
    dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.newsCpFilterInActive(device)!!.click()
    dWizard.filterNewsOkBtn(device)!!.click()
    device.waitForIdle(500L);

    // Редактируем  первую новость
    dWizard.newsCpEditNews(device,0)!!.click()
    // Редактируем статус
    // т.к. при создаании новости по умолчанию у ней статус активен, то надо отредактировать статус в "неактивный" а затем в актиывный

    dWizard.newsCpEditSwitchBox(device)!!.click()
    dWizard.addNewsSaveBtn(device)!!.click()

    dWizard.newsFiltrBtn(device)!!.click()
    dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.newsCpFilterActive(device)!!.click()
    dWizard.filterNewsOkBtn(device)!!.click()

    dWizard.newsCpEditNews(device,0)!!.click()

    dWizard.newsCpEditSwitchBox(device)!!.click()
    dWizard.addNewsSaveBtn(device)!!.click()

    dWizard.newsFiltrBtn(device)!!.click()
    dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
    dWizard.newsCpFilterInActive(device)!!.click()
    dWizard.filterNewsOkBtn(device)!!.click()



    }


//3.25
    //TODO: добавить ассерты
    @Test
    fun newsPanelEditStatusNotActive(){

        dWizard.mainAllNews(device)!!.click()
        dWizard.newsBtnToCP(device)!!.click()
        dWizard.newsCpAddNews(device)!!.click()

        //Добавляем новость
        dWizard.addNewsCategory(device)!!.click()
        dWizard.clickIt(device,dWizard.addNewsCategory(device)!!.bounds.centerX(),
            dWizard.addNewsCategory(device)!!.bounds.centerY() +
                    dWizard.addNewsCategory(device)!!.bounds.height())


        dWizard.addNewsTitle(device)!!.text = dWizard.ADD_NEWS_TITLE_ORIG
        dWizard.addNewsDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.addNewsTime(device)!!.text = dWizard.ADD_NEWS_TIME
        dWizard.addNewsDescription(device)!!.text = dWizard.ADD_NEWS_DESCR_ORIG
        dWizard.addNewsSaveBtn(device)!!.click()
        device.waitForIdle(500L);

        //Выводим новость через фыильтр по дате
        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.newsCpFilterInActive(device)!!.click()
        dWizard.filterNewsOkBtn(device)!!.click()
        device.waitForIdle(500L);

        // Редактируем  первую новость
        dWizard.newsCpEditNews(device,0)!!.click()
        // Редактируем статус
        // т.к. при создаании новости по умолчанию у ней статус активен, то надо отредактировать статус в "неактивный" а затем в актиывный

        dWizard.newsCpEditSwitchBox(device)!!.click()
        dWizard.addNewsSaveBtn(device)!!.click()

        dWizard.newsFiltrBtn(device)!!.click()
        dWizard.filterNewsFirstDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.filterNewsLastDate(device)!!.text = dWizard.ADD_NEWS_DATE_ORIG
        dWizard.newsCpFilterActive(device)!!.click()
        dWizard.filterNewsOkBtn(device)!!.click()



    }


}
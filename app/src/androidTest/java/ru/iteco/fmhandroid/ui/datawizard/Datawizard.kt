package ru.iteco.fmhandroid.ui.datawizard

import android.widget.EditText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*

class Datawizard() {

    val LAUNCH_TIMEOUT = 500L

    val SIGN_IN = "SIGN IN"
    val LOG_OUT = "Log out"
    val NEWS = "News"
    val MAIN = "Main"
    val ABOUT = "About"
    val CLAIMS = "Claims"
    val CANCEL = "Cancel"
    val THROW_OFF = "Throw off"

    val OPEN_CARD = "Open"
    val IN_PROGRESS_CARD = "In progress"
    val EXECUTED_CARD = "Executed"
    val CANCELED_CARD = "Canceled"

    var COORD_X = 540
    var COORD_Y_ANNOY = 500
    var COORD_Y_HB = 700
    var COORD_Y_SALAR = 900
    var COORD_Y_UNION = 1000
    var COORD_Y_HOL = 1100
    var COORD_Y_MASS = 1200
    var COORD_Y_GRAT = 1400

    var COORD_D_Y_ANNOY = 150
    var COORD_D_Y_HB = 300
    var COORD_D_Y_SALAR = 450
    var COORD_D_Y_UNION = 600

    val FIRST_ANNOY_DATE = "12.01.2023"
    val LAST_ANNOY_DATE = "25.01.2023"

    val CONTENT_ANNOY = "Объявление"
    val CONTENT_HB = "День рождения"
    val CONTENT_SALAR = "Зарплата"
    val CONTENT_UNION = "Профсоюз"
    val CONTENT_HOL = "Праздник"
    val CONTENT_MASS = "Массаж"
    val CONTENT_GRAT = "Благодарность"

    val LOGIN_CORRECT = "login2"
    val LOGIN_INCORRECT = "login"
    val PASS_CORRECT = "password2"
    val PASS_INCORRECT = "password"

    val CLAIM_CREATE_TITLE = "Бобриные специалисты"

    val CLAIM_CREATE_DATE = "02.04.2023"
    val CLAIM_CREATE_TIME = "09:23"
    val CLAIM_CREATE_DESCRIPTION = "Бобры - молодцы"

    val ADD_NEWS_TITLE_ORIG = "Пчеловоды пчел"
    val ADD_NEWS_TITLE_EDITED = "Боброводы бобров"
    val ADD_NEWS_DESCR_ORIG = "Пчелы добра"
    val ADD_NEWS_DESCR_EDITED = "Бобры бобра"
    val ADD_NEWS_DATE_ORIG = "01.03.2023"
    val ADD_NEWS_DATE_EDITED = "02.03.2023"
    val ADD_NEWS_TIME = "01:20"


    val ACTIVE_MRK = "Active"
    val NOT_ACTIVE_MRK = "Not active"
    fun DataWizard() {

    }


    fun clickIt(dev: UiDevice, xx: Int, yy: Int): Boolean {
        return dev.click(xx, yy)
    }

// Names for buttons & etc

    fun nameSignIn(): UiObject {
        return UiObject(UiSelector().text(SIGN_IN))
    }

    fun nameLogOut(): UiObject {
        return UiObject(UiSelector().text(LOG_OUT))
    }

    fun nameNews(): UiObject {
        return UiObject(UiSelector().text(NEWS))
    }

    fun nameMain(): UiObject {
        return UiObject(UiSelector().text(MAIN))
    }

    fun nameAbout(): UiObject {
        return UiObject(UiSelector().text(ABOUT))
    }

    fun nameClaim(): UiObject {
        return UiObject(UiSelector().text(CLAIMS))
    }

    fun nameCancel(): UiObject {
        return UiObject(UiSelector().text(CANCEL))
    }

    fun nameOk(): UiObject {
        return UiObject(UiSelector().text(CANCEL))
    }


    fun nameThrownOff(): UiObject {
        return UiObject(UiSelector().text(THROW_OFF))
    }

    // Auth page block

    fun authLoginField(): UiObject {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Login")
        )
    }

    fun authPasswordField(): UiObject {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
            UiSelector().className(
                EditText::class.java
            ).textContains("Password")
        )
    }


    fun authPageAuthImage(dev: UiDevice): UiObject {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button")
        );
    }

    // Main page block

    fun mainTradeMark(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view"))
     }

    fun mainUsers(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button").instance(0)
        )
    }

    fun mainButterflyPic(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/our_mission_image_button").instance(0)
        )
    }

    fun mainLogOutLink(): UiObject? {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            .findObject(UiSelector().text("Log out"))
    }

    fun mainMenu(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0)
        )
    }

    fun mainNewsBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view")
                .instance(0)
        );
    }


    fun mainNewsBlockNewsPlateTitle(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view")
                .instance(i)
        );
    }

    fun mainBlockCollapseBtn(dev: UiDevice, inst: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/expand_material_button").instance(inst)
        );
    }


    fun mainWholeNewsBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main")
                .instance(0)
        );
    }

    fun mainWholeClaimsBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_claim_include_on_fragment_main")
                .instance(0)
        );
    }


    fun mainClaimCard(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0)
        );
    }

    fun mainAllClaims(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0)
        );
    }

    fun mainAllNews(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/all_news_text_view").instance(0)
        );
    }

    //News page block
    fun newsNthCard(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view")
        )
    }

    fun newsNthCardTitle(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_view").instance(i)
        );
    }

    fun newsSortBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/sort_news_material_button").instance(0)
        );
    }

    fun newsFiltrBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_material_button")
                .instance(0)
        );
    }

    fun newsBtnToCP(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_material_button")
                .instance(0)
        );
    }

    //News Control Panel block
    fun newsCpAddNews(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/add_news_image_view")
                .instance(0)
        );
    }

    fun newsCpPublicDate(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publication_date_text_view")
                .instance(0)
        );
    }

    fun newsCpFilterActive(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_active_material_check_box")
                .instance(0)
        );
    }

    fun newsCpFilterInActive(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_news_inactive_material_check_box")
                .instance(0)
        );
    }


    fun newsCpNthNewsDescription(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_description_text_view")
                .instance(i)
        );
    }

    fun newsCpEditNews(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/edit_news_item_image_view")
                .instance(0)
        );
    }

    fun newsCpEditSwitchBox(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/switcher")
                .instance(0)
        );
    }

    fun newsCpDeleteNews(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/delete_news_item_image_view")
                .instance(i)
        );
    }

    fun newsCPBlank(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/control_panel_empty_news_list_text_view")
                .instance(0)
        );
    }

    //Add news page
    fun addNewsCategory(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view")
                .instance(0)
        );
    }

    fun addNewsTitle(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_input_edit_text")
                .instance(0)
        );
    }

    fun addNewsDate(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_text_input_edit_text")
                .instance(0)
        );
    }

    fun addNewsTime(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_time_text_input_edit_text")
                .instance(0)
        );
    }

    fun addNewsDescription(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_description_text_input_edit_text")
                .instance(0)
        );
    }

    fun addNewsSaveBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button")
                .instance(0)
        );
    }


    //Edit News block
    //TODO - its double
    fun editNewsTitle(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_title_text_input_edit_text")
                .instance(0)
        );
    }

    fun editNewsCategory(dev: UiDevice): UiObject? {

        return addNewsCategory(dev)

    }

    fun editNewsDate(dev: UiDevice): UiObject? {
        return addNewsDate(dev)

    }

    fun editNewsDescription(dev: UiDevice): UiObject? {
        return addNewsDescription(dev)

    }

//About page

    fun aboutPrivacyBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/about_privacy_policy_label_text_view")
                .instance(0)
        );
    }

    //Butterfly page
    fun butterflyCard(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/our_mission_item_material_card_view")
                .instance(0)
        );
    }

    // Add Claim page
    fun claimCardInTitle(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/title_label_text_view").instance(0)
        );
    }

    fun claimCardStatus(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0)
        );
    }

    fun claimCardStatusProcess(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/status_processing_image_button")
                .instance(0)
        );
    }


    fun claimCardReturnBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/close_image_button").instance(0)
        );
    }

    fun claimCardThrowOffComment(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/editText").instance(0)
        );
    }


    fun claimCreateTitle(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/title_edit_text")
                .instance(0)
        );
    }

    fun claimCreateExecutor(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/executor_drop_menu_auto_complete_text_view")
                .instance(0)
        );
    }

    fun claimCreateDate(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/date_in_plan_text_input_edit_text")
                .instance(0)
        );
    }

    fun claimCreateTime(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/time_in_plan_text_input_edit_text")
                .instance(0)
        );
    }

    fun claimCreateDescription(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/description_edit_text")
                .instance(0)
        );
    }

    fun claimCreateSaveBtn(dev: UiDevice): UiObject? {
        return addNewsSaveBtn(dev)

    }
    //All claims page

    fun allClaimAddClaim(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/add_new_claim_material_button")
                .instance(0)
        );
    }

    fun allClaimCardInList(dev: UiDevice, inst: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(inst)
        );
    }

    fun allClaimFilterBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0)
        );
    }

    fun allClaimBlankResult(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_retry_material_button")
                .instance(0)
        );
    }

    //Claims filter block
    fun filterClaimOk(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0)
        );
    }

    fun filterClaimInProgress(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0)
        );
    }

    fun filterClaimExecuted(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_executed").instance(0)
        );
    }

    fun filterClaimCancelled(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_cancelled").instance(0)
        );
    }

    fun filterClaimOkBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button")
                .instance(0)
        );
    }

    fun filterNewsCategory(dev: UiDevice): UiObject? {
        return addNewsCategory(dev)

    }

    fun filterNewsOkBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/filter_button")
                .instance(0)
        );
    }

    fun filterNewsFirstDate(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_start_text_input_edit_text")
                .instance(0)
        );
    }

    fun filterNewsLastDate(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_publish_date_end_text_input_edit_text")
                .instance(0)
        );
    }

    fun popUpOkBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("android:id/button1").instance(0)
        );
    }
}
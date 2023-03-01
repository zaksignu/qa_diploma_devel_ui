package ru.iteco.fmhandroid.ui.datawizard

import android.widget.EditText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*

class Datawizard() {

    //private lateinit var device: UiDevice;
    private val LAUNCH_TIMEOUT = 5000L

    val SIGN_IN = "SIGN IN"
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

    var COORD_X = 540;
    var COORD_Y_ANNOY = 500;
    var COORD_Y_HB = 700;

    val FIRST_ANNOY_DATE = "12.01.2023"
    val LAST_ANNOY_DATE = "25.01.2023"
    val FIRST_TERRY_DATE = "08.01.2023"
    val LAST_TERRY_DATE = "31.01.2023"

    val CONTENT_ANNOY = "Объявление"
    val CONTENT_HB = "Terry"

     val  LOGIN_CORRECT = "login2"
     val  LOGIN_INCORRECT = "login"
     val  PASS_CORRECT = "password2"
     val  PASS_INCORRECT = "password"

    val CLAIM_CREATE_TITLE = "Бобриные специалисты"

    val CLAIM_CREATE_DATE = "01.01.2020"
    val CLAIM_CREATE_TIME = "09:23"
    val CLAIM_CREATE_DESCRIPTION = "Бобры - молодцы"
    
    fun DataWizard() {

    }

    //    fun thisDevice(): Datawizard {
//        return dev
//    }
    fun clickIt(dev: UiDevice, xx: Int, yy: Int): Boolean {
        return dev.click(xx, yy)
    }



    fun nameSignIn(): UiObject {
        return UiObject(UiSelector().text(SIGN_IN))
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


    fun nameThrownOff(): UiObject {
        return UiObject(UiSelector().text(THROW_OFF))
    }

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

    fun mainTradeMark(dev: UiDevice): UiObject? {
        return dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view"))
        //return  UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view").instance(0))
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

    fun mainBlockCollapseBtn(dev: UiDevice, inst : Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/expand_material_button").instance(inst)
        );
    }


    fun mainWholeNewsBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_news_include_on_fragment_main").instance(0)
        );
    }

    fun mainWholeClaimsBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/container_list_claim_include_on_fragment_main").instance(0)
        );
    }




    fun mainClaimAddNew(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/add_new_claim_material_button").instance(0)
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

    fun newsNthCard(dev: UiDevice, i: Int): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view")
                .instance(i)
        );
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


    fun aboutPrivacyBlock(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/about_privacy_policy_label_text_view")
                .instance(0)
        );
    }

    fun butterflyCard(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/our_mission_item_material_card_view")
                .instance(0)
        );
    }

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
            UiSelector().resourceId("ru.iteco.fmhandroid:id/status_processing_image_button").instance(0)
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

    fun claimCardThrowOffCommentOkBtn(dev: UiDevice): UiObject? {
        return dev.findObject(
            UiSelector().resourceId("android:id/button1").instance(0)
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

    fun claimCreateTitle(dev: UiDevice): UiObject?{
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/title_edit_text")
                .instance(0)
        );
    }

    fun claimCreateExecutor(dev: UiDevice): UiObject?{
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/executor_drop_menu_auto_complete_text_view")
                .instance(0)
        );
    }

    fun claimCreateDate(dev: UiDevice): UiObject?{
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/date_in_plan_text_input_edit_text")
                .instance(0)
        );
    }

    fun claimCreateTime(dev: UiDevice): UiObject?{
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/time_in_plan_text_input_edit_text")
                .instance(0)
        );
    }

    fun claimCreateDescription(dev: UiDevice): UiObject?{
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/description_edit_text")
                .instance(0)
        );
    }

    fun claimCreateSaveBtn(dev: UiDevice): UiObject?{
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/save_button")
                .instance(0)
        );
    }

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
        return dev.findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_category_text_auto_complete_text_view")
                .instance(0)
        );
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
}
package ru.iteco.fmhandroid.ui.datawizard

import android.widget.EditText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*

class Datawizard() {

    //private lateinit var device: UiDevice;
    private val LAUNCH_TIMEOUT = 5000L

    private val SIGN_IN = "SIGN IN"
    private val NEWS = "News"
    private val MAIN = "Main"
    private val ABOUT = "About"
    private val CLAIMS = "Claims"


    fun DataWizard() {

    }

//    fun thisDevice(): Datawizard {
//        return dev
//    }
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



    fun authPageAuthImage(): UiObject {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(
            UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button")
        );
    }

    fun mainTradeMark(dev : UiDevice): UiObject? {
        return dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view"))
        //return  UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/trademark_image_view").instance(0))
        }
    fun mainUsers(dev : UiDevice): UiObject? {
        return dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/authorization_image_button").instance(0))
      }

    fun mainButterflyPic(dev : UiDevice): UiObject? {
        return dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/our_mission_image_button").instance(0))
    }

    fun mainLogOutLink(): UiObject? {
        return  UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).findObject(UiSelector().text("Log out"))
    }

    fun mainMenu(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/main_menu_image_button").instance(0))
    }

    fun mainNewsBlock(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/news_item_material_card_view").instance(0));

    }

    fun mainNewsBlockCollapseBtn(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/expand_material_button").instance(0));
    }

    fun mainClaimCard(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(0));
    }

    fun mainAllClaims(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/all_claims_text_view").instance(0));
    }

    fun aboutPrivacyBlock(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/about_privacy_policy_label_text_view").instance(0));
    }

    fun butterflyCard(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/our_mission_item_material_card_view").instance(0));
    }

    fun claimCardInTitle(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/title_label_text_view").instance(0));
    }

    fun claimCardStatus(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/status_label_text_view").instance(0));
    }

    fun allClaimCardInList(dev : UiDevice, inst : Int): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_card").instance(inst));
    }
    fun allClaimFilterBtn(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/filters_material_button").instance(0));
    }

    fun filterClaimOk(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_open").instance(0));
    }
    fun filterClaimInProgress(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_in_progress").instance(0));
    }
    fun filterClaimExecuted(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_executed").instance(0));
    }
    fun filterClaimCancelled(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/item_filter_cancelled").instance(0));
    }

    fun filterClaimOkBtn(dev : UiDevice): UiObject? {
        return  dev.findObject(UiSelector().resourceId("ru.iteco.fmhandroid:id/claim_list_filter_ok_material_button").instance(0));
    }
}
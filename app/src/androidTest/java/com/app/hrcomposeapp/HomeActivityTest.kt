package com.app.hrcomposeapp

import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.hrcomposeapp.ui.theme.HRComposeAppTheme
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.viewmodels.HomeViewModel
import com.app.hrcomposeapp.views.AppMainScreen
import com.app.hrcomposeapp.views.HomeActivity
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    private lateinit var navController: TestNavHostController

    @get:Rule
    val composeTestRule = createAndroidComposeRule<HomeActivity>()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity or use createComposeRule()

    @get:Rule
    val composeTestRule1 = createComposeRule()


    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
        }
    }

    fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        assertEquals(expectedRouteName, AppScreens.HomeScreen.route)
    }

    private fun navigateToAddEmployeeScreen() {
        val addFABText = composeTestRule.activity.getString(R.string.desc_add_fab)
        composeTestRule.onNodeWithContentDescription(addFABText).performClick()
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(AppScreens.HomeScreen.route)
    }

    @Test
    fun appNavHost_verifyAppNameShownOnStartHomeScreen() {
        val appName = composeTestRule.activity.getString(R.string.app_name)
        composeTestRule.onNodeWithText(appName).assertExists()
    }

    @Test
    fun appNavHost_clickBackOnAddEmployeeScreen_navigatesToHomeScreen() {
        navigateToAddEmployeeScreen()
        composeTestRule.onNodeWithContentDescription("back")
            .performClick()
        navController.assertCurrentRouteName(AppScreens.HomeScreen.route)
    }

    @Test
    fun open_drawer_test() {
        val navDrawerDesc = "navigation_drawer"
        composeTestRule.onNodeWithContentDescription(navDrawerDesc).performClick()
    }

    @Test
    fun add_fab_test() {
        val addFABText = composeTestRule.activity.getString(R.string.desc_add_fab)
        val addEmployeeText = composeTestRule.activity.getString(R.string.add_employee)
        composeTestRule.onNodeWithContentDescription(addFABText, useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithText(addEmployeeText, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun givenAnEmployeeList_whenWeClickEachItem_thenItemDetailIsDisplayed() {
        /*composeTestRule.onClickableTextItem("Item 1").performClick()
        composeTestRule.onList().assertDoesNotExist()*/
    }

}

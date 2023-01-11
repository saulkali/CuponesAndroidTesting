package com.fackerdev.cupones

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.fackerdev.cupones.modules.mainModule.view.MainActivity
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityCreateTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    /*
    * Crea un nuevo cupon
    * clickea sobre etCoupon y agrega el texto del nuevo cupon
    * despues verifica que el boton crear este visible
    * agregando la descripcion y para finalizar da click al boton de crear
    * */
    @Test
    fun createCouponTest(){
        val etCoupon = onView(withId(R.id.edCupon))
        etCoupon.check(matches(withText("")))
        etCoupon.perform(click())
        etCoupon.perform(replaceText("WELCOM-02"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(isDisplayed()))

        val etDescription = onView(withId(R.id.edDescription))
        etDescription.perform(click())
        etDescription.perform(replaceText("Descripcion de prueba para el cupon"))

        etCoupon.perform(replaceText("WELCOM-01"))
        btnCreate.perform(click())

        val snackbar = onView(withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(matches(withText("Se guardo de manera correcta")))
    }
    /*
    * verifica que el cupon ya exista
    * test: da click en el etCoupon y agrega el texto de un cupon existente
    * y verifica que el boton de crear no este visible
    * */
    @Test
    fun consultCouponExistTest(){
        val etCoupon = onView(withId(R.id.edCupon))
        etCoupon.check(matches(withText("")))
        etCoupon.perform(click())
        etCoupon.perform(replaceText("WELCOM-01"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(not(isDisplayed())))

    }

    /*
    * Comprueba de que no se pueda crear un cupon ya existente o repetido
    *Test: etCupon inicia vacio y se remplaza con un codigo de cupon que ya existe
    * click en btnConsult
    * añade descripcion y edita etCupon
    * Click btnCreate
    * comprueba mensaje snackbar (Error el cupon ya existe)
    */
    @Test
    fun CreateCouponWithOldCodeTest(){
        val etCoupon = onView(withId(R.id.edCupon))
        etCoupon.perform(replaceText("WELCOM-01F"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(isDisplayed()))
        val etDescription = onView(withId(R.id.edDescription))
        etDescription.perform(click())
        etDescription.perform(replaceText("2x2 de descuento de pizzas"))
        etCoupon.perform(replaceText("WELCOM-01"))

        btnCreate.perform(click())
    }
}
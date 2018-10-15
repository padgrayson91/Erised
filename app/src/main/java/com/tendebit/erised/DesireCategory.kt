package com.tendebit.erised

import java.util.*

class DesireCategory(private val images: Array<Int>) {

    fun getRandomImageId() : Int {
        val random = Random()
        return images[random.nextInt(images.size)]
    }

}
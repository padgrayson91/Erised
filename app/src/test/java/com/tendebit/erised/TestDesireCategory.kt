package com.tendebit.erised

import org.junit.Test

class TestDesireCategory {

    @Test
    fun randomValuesInBounds() {
        val testDesireCategory = DesireCategory(Array(1) {1})
        val sample1 = testDesireCategory.getRandomImageId()
        val sample2 = testDesireCategory.getRandomImageId()
        val sample3 = testDesireCategory.getRandomImageId()

        assert (sample1 == sample2 && sample2 == sample3 && sample3 == 1)
    }
}
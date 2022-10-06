/*
 * Copyright (c) 2021 AtLarge Research
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.opendc.simulator.network

import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Test suite for [SimNetworkLink] class.
 */
class SimNetworkLinkTest {
    @Test
    fun testContainsLeft() {
        val left = mockk<SimNetworkPort>()
        val right = mockk<SimNetworkPort>()

        val link = SimNetworkLink(left, right)
        assertTrue(left in link)
    }

    @Test
    fun testContainsRight() {
        val left = mockk<SimNetworkPort>()
        val right = mockk<SimNetworkPort>()

        val link = SimNetworkLink(left, right)
        assertTrue(right in link)
    }

    @Test
    fun testContainsNone() {
        val left = mockk<SimNetworkPort>()
        val right = mockk<SimNetworkPort>()
        val none = mockk<SimNetworkPort>()

        val link = SimNetworkLink(left, right)
        assertFalse(none in link)
    }

    @Test
    fun testOppositeLeft() {
        val left = mockk<SimNetworkPort>()
        val right = mockk<SimNetworkPort>()

        val link = SimNetworkLink(left, right)
        assertEquals(right, link.opposite(left))
    }

    @Test
    fun testOppositeRight() {
        val left = mockk<SimNetworkPort>()
        val right = mockk<SimNetworkPort>()

        val link = SimNetworkLink(left, right)
        assertEquals(left, link.opposite(right))
    }

    @Test
    fun testOppositeNone() {
        val left = mockk<SimNetworkPort>()
        val right = mockk<SimNetworkPort>()
        val none = mockk<SimNetworkPort>()

        val link = SimNetworkLink(left, right)
        assertThrows<IllegalArgumentException> { link.opposite(none) }
    }
}

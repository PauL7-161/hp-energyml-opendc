/*
 * Copyright (c) 2020 AtLarge Research
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

package org.opendc.compute.simulator.allocation

import org.opendc.compute.api.Server
import org.opendc.compute.simulator.HypervisorView

/**
 * The logic for an [AllocationPolicy] that uses a [Comparator] to select the appropriate node.
 */
public interface ComparableAllocationPolicyLogic : AllocationPolicy.Logic {
    /**
     * The comparator to use.
     */
    public val comparator: Comparator<HypervisorView>

    override fun select(
        hypervisors: Set<HypervisorView>,
        server: Server
    ): HypervisorView? {
        return hypervisors.asSequence()
            .filter { hv ->
                val fitsMemory = hv.availableMemory >= (server.flavor.memorySize)
                val fitsCpu = hv.node.flavor.cpuCount >= server.flavor.cpuCount
                fitsMemory && fitsCpu
            }
            .minWithOrNull(comparator.thenBy { it.node.uid })
    }
}

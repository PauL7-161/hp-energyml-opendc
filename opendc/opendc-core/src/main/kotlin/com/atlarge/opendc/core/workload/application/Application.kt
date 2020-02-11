/*
 * MIT License
 *
 * Copyright (c) 2019 atlarge-research
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

package com.atlarge.opendc.core.workload.application

import com.atlarge.odcsim.Behavior
import com.atlarge.odcsim.ProcessContext
import com.atlarge.odcsim.ReceiveRef
import com.atlarge.opendc.core.workload.Workload

/**
 * A generic representation of a workload that can directly be executed by physical or virtual compute resources,
 * such as a web server application.
 */
interface Application : Workload {
    /**
     * The number of processing elements required by the task.
     */
    val cores: Int

    /**
     * Build the runtime [Behavior] of an application, accepting messages of [ProcessMessage].
     *
     * This is a model for the runtime behavior of an application instance (process) that describes how an application
     * instance consumes the allocated resources on a machine.
     */
    suspend operator fun invoke(ctx: ProcessContext, pid: Pid, main: ReceiveRef<ProcessMessage>)
}

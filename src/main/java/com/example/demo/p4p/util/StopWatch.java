/**
 * Copyright (c) 2007 Regents of the University of California.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. The name of the University may not be used to endorse or promote products 
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE REGENTS AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */

package com.example.demo.p4p.util;

/**
 * A stop watch that can be used to measure elapsed 
 * time, in milliseconds.
 */

public class StopWatch {
    public static final int STATE_PAUSED = 0;
    public static final int STATE_RUNNING = 1;
    public static final int STATE_STOPPED = 2;

    private long elapsedTime = 0;
    private long start = 0;
    private int state;
    

    public StopWatch() {
	reset();
    }

        
    public void reset () {
	elapsedTime = 0;
	state = STATE_STOPPED;
    }

    /**
     * Start the watch. Calling start multiple times when the 
     * watch is running has no effect. The watch starts when the
     * first time it is called.
     */
    
    public void start () {
	if(state == STATE_RUNNING) 
	    return;
	start = System.currentTimeMillis();
	state = STATE_RUNNING;
    }

    public void pause () {
	if(state == STATE_RUNNING) {
	    elapsedTime += (System.currentTimeMillis()-start);
	    state = STATE_PAUSED;
	}
    }

    
    public void stop () {
	if(state == STATE_RUNNING) {
	    elapsedTime += (System.currentTimeMillis()-start);
	}
	state = STATE_STOPPED;
    }

    public long getElapsedTime () {
	if(state == STATE_RUNNING) {
	    elapsedTime += (System.currentTimeMillis()-start);
	    start = System.currentTimeMillis();
	}
	return elapsedTime;
    }
}

    

    

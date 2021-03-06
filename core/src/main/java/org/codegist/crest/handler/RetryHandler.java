/*
 * Copyright 2011 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.crest.handler;

import org.codegist.crest.annotate.CRestComponent;
import org.codegist.crest.io.RequestException;


/**
 * <p>RetryHandlers are responsible for deciding whether a request that have failed should be re-tried to not.</p>
 * <p>RetryHandlers are CRest Components.</p>
 * @author laurent.gilles@codegist.org
 * @see org.codegist.crest.annotate.CRestComponent
 */
@CRestComponent
public interface RetryHandler {

    /**
     * Should return whether to retry or not the request.
     * @param exception Failure reason
     * @param attemptNumber current attempt number. Starts at 2, as the first attempt has already been consumed
     * @return whether to retry or not
     * @throws Exception Any exception
     */
    boolean retry(RequestException exception, int attemptNumber) throws Exception;

}

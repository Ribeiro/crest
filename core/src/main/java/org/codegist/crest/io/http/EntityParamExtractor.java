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

package org.codegist.crest.io.http;

import org.codegist.crest.param.EncodedPair;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Used by the {@link org.codegist.crest.io.http.AuthorizationHttpChannel} the extract any relevant entity's parameters to use to authenticate a request
 * @see org.codegist.crest.security.Authorization#authorize(org.codegist.crest.config.MethodType, String, org.codegist.crest.param.EncodedPair...)
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public interface EntityParamExtractor {

    /**
     * Extracts a list of pre-encoded parameters from the HTTP entity to use during the authentification process of a request
     * @param contentType the HTTP request's Content-Type
     * @param charset the HTTP request entity's charset
     * @param entity the HTTP request's entity
     * @return the parameters extracted from the entity to use during the authentification process
     * @throws IOException
     * @see org.codegist.crest.security.Authorization#authorize(org.codegist.crest.config.MethodType, String, org.codegist.crest.param.EncodedPair...)
     */
    List<EncodedPair> extract(String contentType, Charset charset, InputStream entity) throws IOException;

}

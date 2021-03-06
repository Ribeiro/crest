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

package org.codegist.crest.server.stubs.params.headers;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static java.lang.String.format;
import static org.codegist.crest.server.utils.ToStrings.string;

/**
 * @author laurent.gilles@codegist.org
 */
@Produces("text/html;charset=UTF-8")
@Path("params/header/serializer")
public class SerializersStub {


    @GET
    @Path("default")
    public String defaults(
            @HeaderParam("p1") String p1,
            @HeaderParam("p2") List<String> p2,
            @HeaderParam("p3") List<String> p3) {
        return String.format("default() p1=%s p2=%s p3=%s", p1, string(p2), string(p3));
    }

    @GET
    @Path("configured")
    public String configured(
            @HeaderParam("p1") String p1,
            @HeaderParam("p2") List<String> p2,
            @HeaderParam("p3") List<String> p3) {
        return String.format("configured() p1=%s p2=%s p3=%s", p1, string(p2), string(p3));
    }

    @GET
    @Path("null")
    public String nulls(
            @HeaderParam("p1") String p1,
            @HeaderParam("p2") String p2,
            @HeaderParam("p3") String p3) {
        return format("null() p1=%s p2=%s p3=%s", p1, p2, p3);
    }
}

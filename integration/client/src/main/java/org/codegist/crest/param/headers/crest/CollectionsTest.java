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

package org.codegist.crest.param.headers.crest;

import org.codegist.crest.annotate.*;
import org.codegist.crest.param.headers.common.ICollectionsTest;

import java.util.List;
import java.util.Set;

/**
 * @author laurent.gilles@codegist.org
 */
public class CollectionsTest extends ICollectionsTest<CollectionsTest.Collections> {

    public CollectionsTest(CRestHolder crest) {
        super(crest, Collections.class);
    }

    @EndPoint("{crest.server.end-point}")
    @Path("params/header/collection")
    @GET
    public static interface Collections extends ICollectionsTest.ICollections {


        @Path("default")
        String defaults(
                @HeaderParam("p1") String[] p1,
                @HeaderParam("p2") boolean[] p2,
                @HeaderParam("p3") List<Integer> p3,
                @HeaderParam("p4") Set<Long> p4);

        @Path("merging")
        @ListSeparator("(def)")
        String merging(
                @HeaderParam("p1") String[] p1,
                @HeaderParam("p2") @ListSeparator("(p2)") boolean[] p2,
                @HeaderParam("p3") @ListSeparator("(p3)") List<Integer> p3,
                @HeaderParam("p4") @ListSeparator("(p4)") Set<Long> p4);

    }
}

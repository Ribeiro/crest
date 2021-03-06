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

package org.codegist.crest.param.forms.xml.common;

import org.codegist.crest.annotate.*;
import org.codegist.crest.entity.XmlEntityWriter;
import org.codegist.crest.param.common.IDatesTest;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.Date;

/**
 * @author laurent.gilles@codegist.org
 */
public class DatesTest extends IDatesTest<DatesTest.Dates> {

    public DatesTest(CRestHolder crest) {
        super(crest, Dates.class);
    }

    @Parameterized.Parameters
    public static Collection<CRestHolder[]> getData() {
        return crest(byXmlSerializersAndRestServices());
    }

    @EndPoint("{crest.server.end-point}")
    @Path("params/form/xml")
    @POST
    @EntityWriter(XmlEntityWriter.class)
    public static interface Dates extends IDatesTest.IDates {

        String date(
                @FormParam("p1") Date p1,
                @FormParam("p2") Date... p2);

    }


    public void assertDates(String p1, String p21, String p22, String actual) {
        StringBuilder expected = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        expected.append("<form-data>");
        expected.append("<p1>").append(p1).append("</p1>");
        expected.append("<p2>").append(p21).append("</p2>");
        expected.append("<p2>").append(p22).append("</p2>");
        expected.append("</form-data>");
        assertXmlEquals(expected.toString(), actual);
    }
}

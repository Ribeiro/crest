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

package org.codegist.crest.param.paths.common;

import java.util.EnumSet;

import static org.codegist.crest.param.common.ICollectionsTest.Tests.DefaultLists;

/**
 * @author laurent.gilles@codegist.org
 */
public class ICollectionsTest<T extends ICollectionsTest.ICollections> extends org.codegist.crest.param.common.ICollectionsTest<T> {

    public ICollectionsTest(CRestHolder crest, Class<T> clazz) {
        super(crest, clazz);
    }

    @Override
    public EnumSet<Tests> ignores() {
        return EnumSet.of(DefaultLists);
    }

}

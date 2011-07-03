/*
 * Copyright 2010 CodeGist.org
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

package org.codegist.crest.config.annotate;

import org.codegist.crest.annotate.PathParam;
import org.codegist.crest.annotate.PathParams;
import org.codegist.crest.config.InterfaceConfigBuilder;
import org.codegist.crest.config.MethodConfigBuilder;

/**
 * @author laurent.gilles@codegist.org
 */
class PathParamsAnnotationHandler extends NoOpAnnotationHandler<PathParams> {

    private final PathParamAnnotationHandler handler;

    public PathParamsAnnotationHandler(PathParamAnnotationHandler handler) {
        this.handler = handler;
    }

    public PathParamsAnnotationHandler() {
        this(new PathParamAnnotationHandler());
    }

    @Override
    public void handleInterfaceAnnotation(PathParams annotation, InterfaceConfigBuilder builder) {
        for(PathParam paramAnnotation : annotation.value()){
            handler.handleInterfaceAnnotation(paramAnnotation, builder);
        }
    }

    @Override
    public void handleMethodAnnotation(PathParams annotation, MethodConfigBuilder builder) {
        for(PathParam paramAnnotation : annotation.value()){
            handler.handleMethodAnnotation(paramAnnotation, builder);
        }
    }
    
}
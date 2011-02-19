/*
 * Copyright 2010 CodeGist.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 * ===================================================================
 *
 * More information at http://www.codegist.org.
 */

package org.codegist.crest.flickr.handler;

import org.codegist.common.lang.Validate;
import org.codegist.common.reflect.Types;
import org.codegist.crest.CRestException;
import org.codegist.crest.ResponseContext;
import org.codegist.crest.flickr.model.Error;
import org.codegist.crest.flickr.model.Payload;
import org.codegist.crest.flickr.model.Response;
import org.codegist.crest.flickr.model.SimplePayload;
import org.codegist.crest.handler.ResponseHandler;
import org.codegist.crest.serializer.Deserializer;

import java.util.Map;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public class FlickrResponseHandler implements ResponseHandler {

    private final Deserializer deserializer;

    public FlickrResponseHandler(Map<String, Object> properties) {
        this.deserializer = (Deserializer) properties.get(Deserializer.class.getName());
        Validate.notNull(this.deserializer, "No deserializer set, please construct CRest using either JSON or XML expected return type.");
    }

    public final Object handle(ResponseContext context) {
        /* Marshall the response */
        Response res = deserializer.deserialize(context.getResponse().asReader(), Types.newType(Response.class, Types.newType(SimplePayload.class, context.getExpectedGenericType())));
        /* Check for flickr OK status */
        if ("ok".equals(res.getStatus())) {
            /* Get the nested payload and returns it */
            Payload payload = res.getPayload();
            if (payload instanceof SimplePayload) {
                return ((SimplePayload) payload).getValue();
            } else {
                return payload;
            }
        } else {
            if (res.getPayload() instanceof Error) {
                /* Status is not OK, try to get the error cause */
                Error error = ((Error) res.getPayload());
                throw new CRestException(error.getMsg() + " (code=" + error.getCode() + ")");
            } else {
                /* Response format is not the one expected. */
                throw new CRestException("Unkown error");
            }
        }
    }


}

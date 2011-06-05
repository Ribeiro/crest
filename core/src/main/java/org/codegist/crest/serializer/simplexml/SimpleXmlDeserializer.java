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
 *  ==================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.crest.serializer.simplexml;

import org.codegist.common.collect.Maps;
import org.codegist.crest.serializer.Deserializer;
import org.codegist.crest.serializer.DeserializerException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author laurent.gilles@codegist.org
 */
public class SimpleXmlDeserializer implements Deserializer {
    

    public static final String STRICT_PROP =  SimpleXmlDeserializer.class.getName() + "#strict";
    public static final String USER_SERIALIZER_PROP =  SimpleXmlFactory.DESERIALIZER_USER_SERIALIZER_PROP;
    public static final String DATE_FORMAT_PROP =  SimpleXmlFactory.DESERIALIZER_DATE_FORMAT_PROP;
    public static final String BOOLEAN_FORMAT_PROP = SimpleXmlFactory.DESERIALIZER_BOOLEAN_FORMAT_PROP;

    static final boolean DEFAULT_STRICT = true;

    private final boolean strict;
    private final org.simpleframework.xml.Serializer serializer;

    public SimpleXmlDeserializer(Map<String,Object> cfg) {
        Map<String,Object> config = Maps.defaultsIfNull(cfg);

        serializer = SimpleXmlFactory.createDeserializer(config);
        if(config.containsKey(STRICT_PROP)) {
            strict = (Boolean) config.get(STRICT_PROP);
        }else{
            strict = DEFAULT_STRICT;
        }
    }


    public <T> T deserialize(Class<T> type, Type genericType, InputStream stream, Charset charset) throws DeserializerException {
        try {
            return serializer.read(type, new InputStreamReader(stream, charset), strict);
        } catch (Exception e) {
            throw new DeserializerException(e);
        }
    }

}
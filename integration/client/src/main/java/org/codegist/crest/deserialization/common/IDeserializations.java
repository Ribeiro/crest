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

package org.codegist.crest.deserialization.common;

import java.io.InputStream;
import java.io.Reader;

/**
 * @author laurent.gilles@codegist.org
 */
public interface IDeserializations {

    Reader reader(String data);

    InputStream inputStream(String data);

    int[] getInts(int[] values);

    long[] getLongs(long[] values);

    String get();

    void nothing(String value);

    Void nothing2(String value);


    byte getByte(String value);

    byte[] getBytes(String value);

    short getShort(String value);

    int getInt(String value);

    long getLong(String value);

    float getFloat(String value);

    double getDouble(String value);

    char getChar(String value);

    boolean getBoolean(String value);

    Byte getWrappedByte(String value);

    Short getWrappedShort(String value);

    Integer getWrappedInt(String value);

    Long getWrappedLong(String value);

    Float getWrappedFloat(String value);

    Double getWrappedDouble(String value);

    Character getWrappedChar(String value);

    Boolean getWrappedBoolean(String value);
}

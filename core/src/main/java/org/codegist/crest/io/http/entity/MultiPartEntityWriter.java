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

package org.codegist.crest.io.http.entity;

import org.codegist.common.lang.Randoms;
import org.codegist.crest.config.ParamConfig;
import org.codegist.crest.io.http.HttpParam;
import org.codegist.crest.io.http.HttpRequest;
import org.codegist.crest.io.http.Pair;
import org.codegist.crest.util.MultiParts;

import java.io.*;
import java.nio.charset.Charset;

import static org.codegist.common.lang.Strings.defaultIfBlank;
import static org.codegist.common.lang.Strings.isNotBlank;

/**
 * @author laurent.gilles@codegist.org
 */
public class MultiPartEntityWriter implements EntityWriter {

    private static final String MULTIPART = "multipart/form-data; boundary=";
    private static final String BOUNDARY = Randoms.randomAlphaNumeric(24);
    private static final String FULL_BOUNDARY = "--" + BOUNDARY;
    private static final String LRLN = "\r\n";

    public String getContentType(HttpRequest request) {
        return MULTIPART + BOUNDARY;
    }

    public int getContentLength(HttpRequest httpRequest) {
        return -1;
    }

    public void writeTo(HttpRequest request, OutputStream outputStream) throws IOException {

        DataOutputStream out = new DataOutputStream(outputStream);
        if (!request.getFormParams().isEmpty()) {
            Charset charset = request.getCharset();

            for (HttpParam param: request.getFormParams()) {
                ParamConfig pc = param.getConfig();
                Class<?> paramClass = pc.getValueClass();
                String partName = pc.getName();
                String partContentType = MultiParts.getContentType(param);
                String partFileName = MultiParts.getFileName(param);

                String partialPontentDiposition = FULL_BOUNDARY + LRLN + "Content-Disposition: form-data; name=\"" + partName + "\"";

                if(isBinary(paramClass)) {
                    String contentType = defaultIfBlank(partContentType, "application/octet-stream");
                    for(Object value : param.getValue()){
                        String fileName;
                        if (value instanceof File) {
                            fileName = defaultIfBlank(partFileName, ((File) value).getName());
                        }else{
                            fileName = partFileName;
                        }

                        out.writeBytes(partialPontentDiposition);
                        out.writeBytes(isNotBlank(fileName) ? "; filename=\"" + fileName + "\"" + LRLN : LRLN);
                        out.writeBytes("Content-Type: " + contentType + LRLN + LRLN);
                        pc.getSerializer().serialize(value, charset, out);
                        out.writeBytes(LRLN);
                    }
                } else {
                    String contentDisposition = partialPontentDiposition + (isNotBlank(partFileName) ? "; filename=\"" + partFileName + "\"" + LRLN : LRLN);
                    String contentType = "Content-Type: " + defaultIfBlank(partContentType, "text/plain") + "; charset=" + charset.displayName() + LRLN + LRLN;
                    for(Pair pair : pc.getParamProcessor().process(param, charset, false)){
                        out.writeBytes(contentDisposition);
                        out.writeBytes(contentType);
                        out.write(pair.getValue().getBytes(charset));
                        out.writeBytes(LRLN);
                    }
                }
            }
        }
        out.writeBytes(FULL_BOUNDARY + "--" + LRLN);
        out.writeBytes(LRLN);
        out.flush();
    }


    private static boolean isBinary(Class<?> paramClass){
        return (paramClass.isAssignableFrom(File.class) || paramClass.isAssignableFrom(InputStream.class));
    }
}

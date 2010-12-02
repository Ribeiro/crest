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

package org.codegist.crest.twitter.service;

import org.codegist.crest.annotate.ContextPath;
import org.codegist.crest.annotate.EndPoint;
import org.codegist.crest.annotate.HttpMethod;
import org.codegist.crest.annotate.Path;
import org.codegist.crest.twitter.model.Message;

import static org.codegist.crest.HttpMethod.DELETE;
import static org.codegist.crest.HttpMethod.POST;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
@EndPoint("http://api.twitter.com")
@ContextPath("/1/direct_messages")
public interface DirectMessageService {

    @Path(".json?since_id={0}&max_id={1}&count={2}&page={3}")
    Message[] getReceived(long sinceId, long maxId, long count, long page);

    @Path(".json?count={0}&page={1}")
    Message[] getReceived(long count, long page);

    @Path("/sent.json?since_id={0}&max_id={1}&count={2}&page={3}")
    Message[] getSent(long sinceId, long maxId, long count, long page);

    @Path("/sent.json?count={0}&page={1}")
    Message[] getSent(long count, long page);

    @HttpMethod(POST)
    @Path("/new.json?user_id={0}&text={1}")
    Message send(long userId, String msg);

    @HttpMethod(POST)
    @Path("/new.json?user_id={0}&screen_name={1}&text={2}")
    Message send(long userId, String screenName, String msg);

    @HttpMethod(DELETE)
    @Path("/destroy/{0}.json")
    Message destroy(long msgId);

}

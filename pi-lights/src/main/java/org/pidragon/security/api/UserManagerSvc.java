/*
 * Copyright (C) 2016 The ToastHub Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pidragon.security.api;

import org.pidragon.utils.Request;
import org.pidragon.utils.Response;

public interface UserManagerSvc {

	public void process(Request request, Response response);
	public User findUser(String username);
	public User findUserByEmail(String email);
	public void authenticate(Request request, Response response);

}

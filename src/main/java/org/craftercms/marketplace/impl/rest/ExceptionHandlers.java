/*
 * Copyright (C) 2007-2016 Crafter Software Corporation.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.craftercms.marketplace.impl.rest;

import org.craftercms.commons.rest.BaseRestExceptionHandlers;
import org.craftercms.commons.rest.RestServiceUtils;
import org.craftercms.deployer.api.exceptions.PluginAlreadyExistsException;
import org.craftercms.deployer.api.exceptions.PluginNotFoundException;
import org.craftercms.marketplace.api.exceptions.InvalidPluginException;
import org.craftercms.marketplace.api.exceptions.PluginAlreadyExistsException;
import org.craftercms.marketplace.api.exceptions.PluginNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by alfonsovasquez on 12/19/16.
 */
@ControllerAdvice
public class ExceptionHandlers extends BaseRestExceptionHandlers {

    @ExceptionHandler(PluginNotFoundException.class)
    public ResponseEntity<Object> handlePluginNotFoundException(PluginNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Plugin not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(PluginAlreadyExistsException.class)
    public ResponseEntity<Object> handlePluginAlreadyExistsException(PluginAlreadyExistsException ex, WebRequest request) {
        HttpHeaders headers = RestServiceUtils.setLocationHeader(new HttpHeaders(),
                                                                 PluginController.BASE_URL + PluginController.GET_PLUGIN_URL,
                                                                 ex.getId());

        return handleExceptionInternal(ex, "Plugin already exists", headers, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(InvalidPluginException.class)
    public ResponseEntity<Object> handleInvalidPluginException(PluginNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Invalid plugin: " + ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
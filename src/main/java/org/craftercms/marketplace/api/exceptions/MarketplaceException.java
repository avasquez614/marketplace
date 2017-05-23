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
package org.craftercms.marketplace.api.exceptions;

import org.springframework.core.NestedExceptionUtils;

/**
 * Created by alfonsovasquez on 30/11/16.
 */
public class MarketplaceException extends Exception {

    public MarketplaceException() {
    }

    public MarketplaceException(Throwable cause) {
        super(cause);
    }

    public MarketplaceException(String message) {
        super(message);
    }

    public MarketplaceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Return the detail message, including the message from the nested exception if there is one.
     */
    @Override
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
    }

}

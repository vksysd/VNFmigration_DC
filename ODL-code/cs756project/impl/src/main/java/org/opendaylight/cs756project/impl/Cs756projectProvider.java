/*
 * Copyright © 2017 Kurien Zacharia, Vikas Kumar and Bhavesh Singh. IIT Bombay. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.cs756project.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cs756projectProvider {

    private static final Logger LOG = LoggerFactory.getLogger(Cs756projectProvider.class);

    private final DataBroker dataBroker;

    public Cs756projectProvider(final DataBroker dataBroker) {
        this.dataBroker = dataBroker;
    }

    /**
     * Method called when the blueprint container is created.
     */
    public void init() {
        LOG.info("Cs756projectProvider Session Initiated");
    }

    /**
     * Method called when the blueprint container is destroyed.
     */
    public void close() {
        LOG.info("Cs756projectProvider Closed");
    }
}
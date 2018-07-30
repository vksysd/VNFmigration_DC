/*
 * Copyright © 2017 Kurien Zacharia, Vikas Kumar and Bhavesh Singh. IIT Bombay. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.cs756project.cli.impl;

import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.opendaylight.cs756project.cli.api.Cs756projectCliCommands;

public class Cs756projectCliCommandsImpl implements Cs756projectCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(Cs756projectCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public Cs756projectCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("Cs756projectCliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }
}
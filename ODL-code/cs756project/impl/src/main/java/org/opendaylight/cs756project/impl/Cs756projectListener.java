/*
 * Copyright © 2017 Kurien Zacharia, Vikas Kumar and Bhavesh Singh. IIT Bombay. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.cs756project.impl;

import org.opendaylight.controller.md.sal.binding.api.*;
import org.opendaylight.controller.md.sal.common.api.data.LogicalDatastoreType;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cs756project.rev150105.RateMonitor;
import org.opendaylight.yang.gen.v1.urn.opendaylight.params.xml.ns.yang.cs756project.rev150105.rate.monitor.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

public class Cs756projectListener implements ClusteredDataTreeChangeListener<RateMonitor> {
    private static final Logger LOG = LoggerFactory.getLogger(Cs756projectListener.class);

    private final DataBroker broker;

    public Cs756projectListener(final DataBroker db) {
        broker = db;
        registerListener();
    }

    /**
     * register listener to get AccessListEntry add/removed event.
     */
    public void registerListener() {
        final DataTreeIdentifier<RateMonitor> treeId = new DataTreeIdentifier<>(
                LogicalDatastoreType.CONFIGURATION, getWildCardPath());
        broker.registerDataTreeChangeListener(treeId, Cs756projectListener.this);
        LOG.info("CS756 Project listener registration success");
    }


    protected InstanceIdentifier<RateMonitor> getWildCardPath() {
        return InstanceIdentifier.create(RateMonitor.class);
    }


    protected Cs756projectListener getDataTreeChangeListener() {
        return this;
    }

    @Override
    public void onDataTreeChanged(@Nonnull Collection<DataTreeModification<RateMonitor>> changes) {
        LOG.info("CS756 Project listener onDataTreeChanged {} ", changes);
        for (DataTreeModification<RateMonitor> change : changes) {
            DataObjectModification<RateMonitor> mod = change.getRootNode();
            switch (mod.getModificationType()) {
                case DELETE:
                    LOG.info(" Delete after data {} ", mod.getDataAfter());
                    break;
                case SUBTREE_MODIFIED:
                    break;
                case WRITE:
                    if (mod.getDataBefore() == null) {
                        LOG.info(" Write after data {} ", mod.getDataAfter());
                        printData(mod);
                        //dsHandler.installFlow(mod);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled node modification type " + mod.getModificationType());
            }
        }
    }

    private void printData(DataObjectModification<RateMonitor> mod) {
        // TODO -- PreCheck !NULL
        RateMonitor accList = mod.getDataAfter();

        List<ServerInfo> entries = accList.getServerInfo();
        LOG.info(" Printing the AccessList Entries : ");

        for (ServerInfo entry : entries) {
            // AccessListEntry entry = entries.getAccessListEntry() ;
            LOG.info(" Key : " + entry.getKey());
            LOG.info(" P Node ID : " + entry.getPNodeId());
            LOG.info(" P Node IP:  " + entry.getPNodeIp());
            LOG.info(" B Node ID : " + entry.getBNodeId());
            LOG.info(" B Node IP " + entry.getBNodeIp());
            LOG.info(" Threshold : " + entry.getThreshold());
        }

    }
}



/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.rest.impl;

import org.duracloud.id.generator.error.InvalidConfigException;
import org.duracloud.id.generator.ldap.domain.AppConfig;
import org.duracloud.id.generator.rest.AppInit;
import org.duracloud.id.generator.rest.IdGenerator;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
public class AppInitImpl implements AppInit {

    private IdGenerator idGenerator;

    public AppInitImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public void initialize(AppConfig config) {
        if (null == config || null == config.getLdapConfig()) {
            throw new InvalidConfigException("AppConfig invalid!");
        }
        idGenerator.initialize(config.getLdapConfig());
    }
}

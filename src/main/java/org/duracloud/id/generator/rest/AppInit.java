/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.rest;

import org.duracloud.id.generator.ldap.domain.AppConfig;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
@Produces("text/plain")
@Path("/init")
public interface AppInit {

    @POST
    @Consumes("application/json")
    public void initialize(AppConfig config);
}

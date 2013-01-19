/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.rest;

import org.duracloud.id.generator.ldap.domain.AppConfig;
import org.duracloud.id.generator.ldap.domain.LdapConfig;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Andrew Woods
 *         Date: 1/16/13
 */
@Produces("text/plain")
public interface IdGenerator {

    public void initialize(LdapConfig ldapConfig);

    @GET
    @Path("/example")
    @Produces("application/json")
    public AppConfig example();

    @POST
    @Path("/user")
    public int getNewUserId();

    @POST
    @Path("/group")
    public int getNewGroupId();

    @POST
    @Path("/rights")
    public int getNewRightsId();
}

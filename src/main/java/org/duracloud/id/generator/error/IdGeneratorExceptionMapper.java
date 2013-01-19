/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
public class IdGeneratorExceptionMapper implements ExceptionMapper<IdGeneratorException> {
    @Override
    public Response toResponse(IdGeneratorException e) {
        return Response.serverError().entity(e.getMessage()).build();
    }
}

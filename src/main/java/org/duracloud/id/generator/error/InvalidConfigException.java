/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.id.generator.error;

/**
 * @author Andrew Woods
 *         Date: 1/17/13
 */
public class InvalidConfigException extends IdGeneratorException {
    public InvalidConfigException(String msg) {
        super(msg);
    }

    public InvalidConfigException(String msg, Throwable t) {
        super(msg, t);
    }
}

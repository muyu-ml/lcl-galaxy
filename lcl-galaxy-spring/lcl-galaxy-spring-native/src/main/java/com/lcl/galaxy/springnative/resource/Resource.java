package com.lcl.galaxy.springnative.resource;

import java.io.InputStream;

/**
 * 提供对资源的访问
 */
public interface Resource {
    InputStream getResourceAsStream();
}

package com.lcl.galaxy.springmvc.frame.handlermapping;

import javax.servlet.http.HttpServletRequest;

public interface MyHandlerMapping {
    Object getHandler(HttpServletRequest request);
}

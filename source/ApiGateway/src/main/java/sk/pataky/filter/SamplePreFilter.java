package sk.pataky.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class SamplePreFilter extends ZuulFilter {

    private Logger LOGGER = LoggerFactory.getLogger(SamplePreFilter.class);

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        LOGGER.info("Filtering {} request to {}", ctx.getRequest().getMethod(), ctx.getRequest().getServletPath());

        ctx.addZuulRequestHeader("Test", "TestSample");
        return null;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}

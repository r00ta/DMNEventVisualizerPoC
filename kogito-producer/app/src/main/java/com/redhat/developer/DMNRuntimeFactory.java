package com.redhat.developer;

import com.google.common.collect.ImmutableList;
import com.redhat.developer.tracing.TracingEventListenerFactory;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.dmn.DMNKogito;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@ApplicationScoped
public class DMNRuntimeFactory {

    @Inject
    TracingEventListenerFactory listenerFactory;

    private static final List<String> DMN_MODELS = new ImmutableList.Builder<String>()
            .add("/bikes.dmn")
            .add("/Loan Eligibility.dmn")
            .build();

    @Produces
    public DMNRuntime build() {
        DMNRuntime rt = DMNKogito.createGenericDMNRuntime(
                DMN_MODELS.stream()
                        .map(DMNRuntimeFactory.class::getResourceAsStream)
                        .map(InputStreamReader::new)
                        .toArray(Reader[]::new)
        );
        rt.addListener(listenerFactory.build());
        return rt;
    }

}

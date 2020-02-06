package com.redhat.developer.messaging;

import com.redhat.developer.dto.DMNEvent;

public class DMNCloudEventDeserializer extends AbstractCloudEventDeserializer<DMNEvent> {

    public DMNCloudEventDeserializer(){
        super(DMNEvent.class);
    }
}

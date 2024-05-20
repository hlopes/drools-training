package io.github.aasaru.drools.section07;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectEventListener implements RuleRuntimeEventListener {
    @Override
    public void objectInserted(ObjectInsertedEvent event) {
        log.info("==> " + event.getObject() + " inserted");
    }

    @Override
    public void objectUpdated(ObjectUpdatedEvent event) {
        log.info("==> " + event.getObject() + " updated");
    }

    @Override
    public void objectDeleted(ObjectDeletedEvent event) {
        log.info("==> " + event.getOldObject() + " deleted");
    }
}

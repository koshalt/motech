package org.motechproject.event.aggregation.dispatch;

import org.motechproject.event.aggregation.model.Aggregation;
import org.motechproject.event.aggregation.model.AggregationRuleRecord;
import org.motechproject.event.aggregation.model.event.AggregationEvent;
import org.motechproject.event.aggregation.repository.AllAggregatedEvents;
import org.motechproject.event.aggregation.repository.AllAggregationRules;
import org.motechproject.event.listener.EventRelay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDispatcher {

    private final AllAggregatedEvents allAggregatedEvents;
    private final EventRelay eventRelay;
    private AllAggregationRules allAggregationRules;

    @Autowired
    public EventDispatcher(AllAggregatedEvents allAggregatedEvents, EventRelay eventRelay, AllAggregationRules allAggregationRules) {
        this.allAggregatedEvents = allAggregatedEvents;
        this.eventRelay = eventRelay;
        this.allAggregationRules = allAggregationRules;
    }

    public void dispatch(String aggregationRuleName) {
        AggregationRuleRecord aggregationRule = allAggregationRules.findByName(aggregationRuleName);
        List<Aggregation> aggregations = allAggregatedEvents.findAllAggregations(aggregationRule.getName());
        for (Aggregation aggregation : aggregations) {
            eventRelay.sendEventMessage(new AggregationEvent(aggregationRule, aggregation).toMotechEvent());
        }
        allAggregatedEvents.removeByAggregationRule(aggregationRule.getName());
    }
}

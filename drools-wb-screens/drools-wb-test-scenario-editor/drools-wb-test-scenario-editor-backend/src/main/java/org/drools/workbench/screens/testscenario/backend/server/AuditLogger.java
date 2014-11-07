/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.testscenario.backend.server;

import org.kie.api.event.process.*;
import org.kie.api.event.rule.*;
import org.kie.api.runtime.KieSession;

import java.util.HashSet;
import java.util.Set;

public class AuditLogger {

    private final Set<String> logs = new HashSet<String>();
    private final KieSession ksession;


    public AuditLogger(KieSession ksession) {

        this.ksession = ksession;

        addRuleRuntimeEventListener();

        addAgendaEventListener(ksession);

        addProcessEventListener(ksession);

    }

    public Set<String> getLog() {
        return logs;
    }

    private void addProcessEventListener(KieSession ksession) {
        ksession.addEventListener(new ProcessEventListener() {
            @Override
            public void beforeProcessStarted(ProcessStartedEvent processStartedEvent) {
                log(processStartedEvent);
            }

            @Override
            public void afterProcessStarted(ProcessStartedEvent processStartedEvent) {
                log(processStartedEvent);
            }

            @Override
            public void beforeProcessCompleted(ProcessCompletedEvent processCompletedEvent) {
                log(processCompletedEvent);
            }

            @Override
            public void afterProcessCompleted(ProcessCompletedEvent processCompletedEvent) {
                log(processCompletedEvent);
            }

            @Override
            public void beforeNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {
                log(processNodeTriggeredEvent);
            }

            @Override
            public void afterNodeTriggered(ProcessNodeTriggeredEvent processNodeTriggeredEvent) {
                log(processNodeTriggeredEvent);
            }

            @Override
            public void beforeNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
                log(processNodeLeftEvent);
            }

            @Override
            public void afterNodeLeft(ProcessNodeLeftEvent processNodeLeftEvent) {
                log(processNodeLeftEvent);
            }

            @Override
            public void beforeVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
                log(processVariableChangedEvent);
            }

            @Override
            public void afterVariableChanged(ProcessVariableChangedEvent processVariableChangedEvent) {
                log(processVariableChangedEvent);
            }
        });
    }

    private void addAgendaEventListener(KieSession ksession) {
        ksession.addEventListener(new AgendaEventListener() {
            @Override
            public void matchCreated(MatchCreatedEvent matchCreatedEvent) {
                log(matchCreatedEvent);
            }

            @Override
            public void matchCancelled(MatchCancelledEvent matchCancelledEvent) {
                log(matchCancelledEvent);
            }

            @Override
            public void beforeMatchFired(BeforeMatchFiredEvent beforeMatchFiredEvent) {
                log(beforeMatchFiredEvent);
            }

            @Override
            public void afterMatchFired(AfterMatchFiredEvent afterMatchFiredEvent) {
                log(afterMatchFiredEvent);
            }

            @Override
            public void agendaGroupPopped(AgendaGroupPoppedEvent agendaGroupPoppedEvent) {
                log(agendaGroupPoppedEvent);
            }

            @Override
            public void agendaGroupPushed(AgendaGroupPushedEvent agendaGroupPushedEvent) {
                log(agendaGroupPushedEvent);
            }

            @Override
            public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
                log(ruleFlowGroupActivatedEvent);
            }

            @Override
            public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent ruleFlowGroupActivatedEvent) {
                log(ruleFlowGroupActivatedEvent);
            }

            @Override
            public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
                log(ruleFlowGroupDeactivatedEvent);
            }

            @Override
            public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent ruleFlowGroupDeactivatedEvent) {
                log(ruleFlowGroupDeactivatedEvent);
            }
        });
    }

    private void addRuleRuntimeEventListener() {
        ksession.addEventListener(new RuleRuntimeEventListener() {
            @Override
            public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
                log(objectInsertedEvent);
            }

            @Override
            public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
                log(objectUpdatedEvent);
            }

            @Override
            public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
                log(objectDeletedEvent);
            }
        });
    }

    private void log(Object o) {
        logs.add(o.toString());
    }
}
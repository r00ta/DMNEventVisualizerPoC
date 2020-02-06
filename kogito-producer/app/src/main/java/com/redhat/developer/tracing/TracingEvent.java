package com.redhat.developer.tracing;

import org.kie.api.builder.Message.Level;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNMessageType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TracingEvent implements Serializable {

    public static class DecisionResult {

        public static class Message {
            private final String message;
            private final String type;
            private final String description;
            private final String level;
            private final String sourceId;

            public Message(String message, DMNMessageType type, Level level, String sourceId) {
                this.message = message;
                this.type = Optional.ofNullable(type).map(Enum::name).orElse(null);
                this.description = Optional.ofNullable(type).map(DMNMessageType::getDescription).orElse(null);
                this.level = Optional.ofNullable(level).map(Enum::name).orElse(null);
                this.sourceId = sourceId;
            }

            public String getMessage() {
                return message;
            }

            public String getType() {
                return type;
            }

            public String getDescription() {
                return description;
            }

            public String getLevel() {
                return level;
            }

            public String getSourceId() {
                return sourceId;
            }
        }

        private final String id;
        private final String name;
        private final DMNDecisionResult.DecisionEvaluationStatus evaluationStatus;
        private final Object result;
        private final List<Message> messages;

        public DecisionResult(String id, String name, DMNDecisionResult.DecisionEvaluationStatus evaluationStatus, Object result, List<Message> messages) {
            this.id = id;
            this.name = name;
            this.evaluationStatus = evaluationStatus;
            this.result = result;
            this.messages = messages;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public DMNDecisionResult.DecisionEvaluationStatus getEvaluationStatus() {
            return evaluationStatus;
        }

        public Object getResult() {
            return result;
        }

        public List<Message> getMessages() {
            return messages;
        }

    }

    private final String type;
    private final String modelName;
    private final String modelNamespace;
    private final Map<String, Object> context;
    private final List<DecisionResult> results;

    public TracingEvent(String type, String modelName, String modelNamespace, Map<String, Object> context, List<DecisionResult> results) {
        this.type = type;
        this.modelName = modelName;
        this.modelNamespace = modelNamespace;
        this.context = context;
        this.results = results;
    }

    public String getType() {
        return type;
    }

    public String getModelName() {
        return modelName;
    }

    public String getModelNamespace() {
        return modelNamespace;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public List<DecisionResult> getResults() {
        return results;
    }

}

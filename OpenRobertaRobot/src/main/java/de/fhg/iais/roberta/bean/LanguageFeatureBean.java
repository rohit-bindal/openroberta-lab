package de.fhg.iais.roberta.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.fhg.iais.roberta.components.UsedActor;
import de.fhg.iais.roberta.components.UsedSensor;
import de.fhg.iais.roberta.syntax.lang.expr.VarDeclaration;
import de.fhg.iais.roberta.syntax.lang.methods.Method;

public class LanguageFeatureBean {
    public List<String> globalVariables = new ArrayList<>();
    public List<String> declaredVariables = new ArrayList<>();
    public List<VarDeclaration<Void>> visitedVars = new ArrayList<>();
    public List<Method<Void>> userDefinedMethods = new ArrayList<>();
    public Set<String> markedVariablesAsGlobal = new HashSet<>();
    public boolean isProgramEmpty = false;
    public boolean isListsUsed = false;

    public Map<Integer, Boolean> loopsLabelContainer = new HashMap<>();

    private Set<UsedSensor> usedSensors = new LinkedHashSet<>();
    private Set<UsedActor> usedActors = new LinkedHashSet<>();
    private Set<String> usedImages = new HashSet<>();

    public List<VarDeclaration<Void>> getVisitedVars() {
        return this.visitedVars;
    }

    public List<Method<Void>> getUserDefinedMethods() {
        return this.userDefinedMethods;
    }

    public Set<String> getMarkedVariablesAsGlobal() {
        return this.markedVariablesAsGlobal;
    }

    public boolean isProgramEmpty() {
        return this.isProgramEmpty;
    }

    public boolean isListsUsed() {
        return this.isListsUsed;
    }

    public Set<UsedSensor> getUsedSensors() {
        return this.usedSensors;
    }

    public Set<UsedActor> getUsedActors() {
        return this.usedActors;
    }

    public Set<String> getUsedImages() {
        return this.usedImages;
    }

    public boolean isSensorUsed(String type) {
        return this.usedSensors.stream().anyMatch(usedSensor -> usedSensor.getType().equals(type));
    }

    public boolean isActorUsed(String type) {
        return this.usedActors.stream().anyMatch(usedActor -> usedActor.getType().equals(type));
    }

    public Map<Integer, Boolean> getLoopsLabelContainer() {
        return loopsLabelContainer;
    }

    public static class Builder {
        private final LanguageFeatureBean LanguageFeatureBean = new LanguageFeatureBean();

        public LanguageFeatureBean.Builder addGlobalVariable(String globalVariable) {
            this.LanguageFeatureBean.globalVariables.add(globalVariable);
            return this;
        }

        public LanguageFeatureBean.Builder addDeclaredVariable(String declaredVariable) {
            this.LanguageFeatureBean.declaredVariables.add(declaredVariable);
            return this;
        }

        public LanguageFeatureBean.Builder addVisitedVariable(VarDeclaration<Void> visitedVariable) {
            this.LanguageFeatureBean.visitedVars.add(visitedVariable);
            return this;
        }

        public LanguageFeatureBean.Builder addUserDefinedMethod(Method<Void> userDefinedMethod) {
            this.LanguageFeatureBean.userDefinedMethods.add(userDefinedMethod);
            return this;
        }

        public LanguageFeatureBean.Builder addMarkedVariableAsGlobal(String markedVariableAsGlobal) {
            this.LanguageFeatureBean.markedVariablesAsGlobal.add(markedVariableAsGlobal);
            return this;
        }

        public LanguageFeatureBean.Builder setProgramEmpty(boolean isProgramEmpty) {
            this.LanguageFeatureBean.isProgramEmpty = isProgramEmpty;
            return this;
        }

        public LanguageFeatureBean.Builder setListsUsed(boolean isListsUsed) {
            this.LanguageFeatureBean.isListsUsed = isListsUsed;
            return this;
        }

        public LanguageFeatureBean.Builder addUsedSensor(UsedSensor usedSensor) {
            this.LanguageFeatureBean.usedSensors.add(usedSensor);
            return this;
        }

        public LanguageFeatureBean.Builder addUsedActor(UsedActor usedActor) {
            this.LanguageFeatureBean.usedActors.add(usedActor);
            return this;
        }

        public LanguageFeatureBean.Builder addUsedImage(String usedImage) {
            this.LanguageFeatureBean.usedImages.add(usedImage);
            return this;
        }

        public LanguageFeatureBean.Builder putLoopLabel(int loop, boolean isInWait) {
            this.LanguageFeatureBean.loopsLabelContainer.put(loop, isInWait);
            return this;
        }

        @Deprecated
        public boolean containsGlobalVariable(String variableName) {
            return this.LanguageFeatureBean.globalVariables.contains(variableName);
        }

        @Deprecated
        public boolean containsDeclaredVariable(String variableName) {
            return this.LanguageFeatureBean.declaredVariables.contains(variableName);
        }

        public LanguageFeatureBean build() {
            return this.LanguageFeatureBean;
        }
    }
}

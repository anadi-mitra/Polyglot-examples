package org.gammaraylab.helper;

import java.util.List;
import java.util.Objects;

/*this class declares method call and args pass dependency for an object*/
public class MethodDependency extends Dependency {
    //super class of the method
    private final ClassInfo classInfo;
    //object type of the caller
    private final Class<?> receiverType;
    //method signature
    private final MethodInfo methodInfo;
    //TODO: field to store the name of method if the call is a polyglot call
    //which parameters will decide the escape status
    private final List<Integer> paramPositionList;

    public MethodDependency(ClassInfo classInfo, MethodInfo methodInfo, Class<?> receiverType, List<Integer> paramPos) {
        this.classInfo= classInfo;
        this.methodInfo= methodInfo;
        this.receiverType=  receiverType;
        this.paramPositionList= paramPos;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public MethodInfo getMethodInfo() {
        return methodInfo;
    }

    public List<Integer> getParamPositionList() {
        return paramPositionList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MethodDependency that = (MethodDependency) o;
        return Objects.equals(classInfo, that.classInfo) && Objects.equals(receiverType, that.receiverType) && Objects.equals(methodInfo, that.methodInfo) && Objects.equals(paramPositionList, that.paramPositionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classInfo, receiverType, methodInfo, paramPositionList);
    }
}

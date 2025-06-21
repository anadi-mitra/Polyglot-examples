package org.gammaraylab.helper;

import java.util.List;
import java.util.Objects;
/*
    This class defines object dependency on a method call.
    We are only resolving polyglot calls since normal calls will be
    optimized by graal
 */
public final class InvocationDependency extends Dependency {
//    private final MethodInfo methodInfo;
    private final String methodName;
    //parameters which decides the escape status of the desired object
    private final List<Integer> paramPositionList;

    public InvocationDependency(/*ClassInfo classInfo,*/ String methodName , /*Class<?> receiverType,*/ List<Integer> paramPos) {
//        this.classInfo= classInfo;
        this.methodName= methodName;
//        this.receiverType=  receiverType;
        this.paramPositionList= paramPos;
    }

//    public boolean getStatus(){
//
//        return true;
//    }

    private boolean resolveDependency(){
        return true;
    }
//    public ClassInfo getClassInfo() {
//        return classInfo;
//    }

//    public MethodInfo getMethodInfo() {
//        return methodInfo;
//    }

//    public List<Integer> getParamPositionList() {
//        return paramPositionList;
//    }

//    public Class<?> getReceiverType() {
//        return receiverType;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InvocationDependency that = (InvocationDependency) o;
        return /*Objects.equals(classInfo, that.classInfo) && Objects.equals(receiverType, that.receiverType) &&*/ Objects.equals(methodName, that.methodName) && Objects.equals(paramPositionList, that.paramPositionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(/*classInfo, receiverType,*/ methodName, paramPositionList);
    }
}

package org.gammaraylab.helper;

import java.util.List;
import java.util.Objects;

final public class MethodSignature {
    private final String mName;
    private final List<Class<?>> paramTypes;
    private final Class<?> returnType;

    public MethodSignature(String mName, List<Class<?>> paramTypes, Class<?> returnType) {
        this.mName = mName;
        this.paramTypes = paramTypes;
        this.returnType = returnType;
    }

    public String getMethodName() {
        return mName;
    }

    public List<Class<?>> getParamTypes() {
        return paramTypes;
    }

    public int getParamSize(){
        return paramTypes.size();
    }

    public Class<?> getReturnType() {
        return returnType;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(returnType.getSimpleName())
                .append(" ")
                .append(mName)
                .append("(");
        for (int i = 0; i < paramTypes.size(); i++) {
            sb.append(paramTypes.get(i).getSimpleName());
            if (i < paramTypes.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MethodSignature that = (MethodSignature) o;
        return Objects.equals(mName, that.mName) &&
                Objects.equals(paramTypes, that.paramTypes) &&
                Objects.equals(returnType, that.returnType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, paramTypes, returnType);
    }
}

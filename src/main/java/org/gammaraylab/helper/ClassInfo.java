package org.gammaraylab.helper;

import org.gammaraylab.helper.exceptions.MethodNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ClassInfo {
    public Class<?> clazz;
    private Map<MethodSignature, MethodInfo> methodInfo;

    public ClassInfo(Class<?> clazz) {
        this.clazz = clazz;
        this.methodInfo= new HashMap<>();
    }

    public void addMethods(Map<MethodSignature, MethodInfo> newMethodInfo) {
        this.methodInfo.putAll(newMethodInfo);
    }

    public void addSingleMethod(MethodSignature signature, MethodInfo newMethodInfo) {
        this.methodInfo.put(signature,newMethodInfo);
    }

    public MethodInfo getMethodInfo(MethodSignature signature) throws MethodNotFoundException {
        if(methodInfo.containsKey(signature))
            return methodInfo.get(signature);

        throw new MethodNotFoundException("method:\""+signature.getMethodName()+"\" not found!");
    }
}

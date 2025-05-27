package org.gammaraylab.helper;

import java.util.*;

public class MethodInfo {
    //signature of the current method
    final MethodSignature signature;
    //escape status of the parameters
    private final List<EscapeStatus> paramEscapeStatusList;
    //list of all the objects that were created in this method
    private final List<ObjectPosition> objectList;
    //escape status of new objects of the current method
    private final List<EscapeStatus> objectEscapeStatusList;
    //dependencies of new objects
    private final Map<ObjectPosition,List<Dependency>> objectDependencies;

    public MethodInfo(String mName, List<Class<?>> paramTypeList, Class<?> returnType, List<ObjectPosition> objectList) {
        signature= new MethodSignature(mName, paramTypeList, returnType);
        paramEscapeStatusList = new ArrayList<>(Collections.nCopies(paramTypeList.size(),new UnresolvedStatus()));
        this.objectList = objectList;
        objectEscapeStatusList = new ArrayList<>(Collections.nCopies(objectList.size(),new UnresolvedStatus()));
        objectDependencies= new HashMap<>();
    }

    public boolean isEscaping(List<Integer> paramPosList){
        //check escape status of each param on which the object is mapped to
        for(int pos: paramPosList){
            if(paramEscapeStatusList.get(pos) instanceof UnresolvedStatus){
                //TODO: resolve
            }
            else if(paramEscapeStatusList.get(pos).isEscaping()){  //it is resolved
                return true;
            }
        }
        return false;
    }

    public void addObjectDependency(ObjectPosition pos, Dependency dependency){
        if(!objectDependencies.containsKey(pos) || objectDependencies.get(pos)==null)// key was absent or value was null
            objectDependencies.put(pos,new ArrayList<>(Collections.singletonList(dependency)));
        else {  //update the list
            List<Dependency> dependencyList=objectDependencies.get(pos);
            dependencyList.add(dependency);
            objectDependencies.put(pos,dependencyList);

        }
    }

    public String getMethodName(){
        return signature.getMethodName();
    }
}

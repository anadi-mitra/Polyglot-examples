package org.gammaraylab.helper;

/*dependency of an object*/
public abstract class Dependency {

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}

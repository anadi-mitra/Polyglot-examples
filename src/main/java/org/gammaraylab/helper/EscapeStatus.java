package org.gammaraylab.helper;

//abstract class for escape status of objects
public abstract class EscapeStatus {
    public abstract boolean isEscaping();

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract String toString();
}

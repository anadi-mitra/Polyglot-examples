package org.gammaraylab.helper;

import java.util.List;
import java.util.Objects;

public class UnresolvedStatus extends EscapeStatus{
    private boolean status;

    public UnresolvedStatus() {
        status= true;  //conservatively assume that the object is escaping
    }

    public void resolveStatus( List<Dependency> objDependecyList) {
//        TODO: resolve the summaries of all the methods in the list and take intersection
    }

    @Override
    public boolean isEscaping() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnresolvedStatus that = (UnresolvedStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return status+"";
    }
}

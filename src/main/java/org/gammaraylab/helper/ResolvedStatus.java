package org.gammaraylab.helper;

import java.util.Objects;

public final class ResolvedStatus extends EscapeStatus {
    private final boolean status;

    public ResolvedStatus(boolean status) {
        this.status=status;
    }

    public boolean isEscaping() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResolvedStatus that = (ResolvedStatus) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return ""+status;
    }
}

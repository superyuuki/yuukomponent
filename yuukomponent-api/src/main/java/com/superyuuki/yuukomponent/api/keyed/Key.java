package com.superyuuki.yuukomponent.api.keyed;

public record Key<T>(String identifier, Class<T> clazz) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key<?> key = (Key<?>) o;

        if (!identifier.equals(key.identifier)) return false;
        return clazz.equals(key.clazz);
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + clazz.hashCode();
        return result;
    }

}

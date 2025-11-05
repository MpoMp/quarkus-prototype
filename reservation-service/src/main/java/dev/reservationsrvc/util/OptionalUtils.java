package dev.reservationsrvc.util;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

@NullMarked
public final class OptionalUtils {

    /// This is here until Mapstruct understands how to map Optional by itself.
    /// See <a href="https://github.com/mapstruct/mapstruct/issues/674">open issue</a>.
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @Nullable
    public static <T> T fromOptional(Optional<T> optional) {
        return optional.orElse(null);
    }

    /// This is here until Mapstruct understands how to map Optional by itself.
    /// See <a href="https://github.com/mapstruct/mapstruct/issues/674">open issue</a>.
    public static <T> Optional<T> toOptional(@Nullable T value) {
        return Optional.ofNullable(value);
    }

}

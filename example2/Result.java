package org.example.example2;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Simple Railway-Oriented Result type modeling success (Ok) and failure (Err).
 */
public abstract class Result<T, E> {
    private Result() {}

    public static <T, E> Result<T, E> ok(T value) {
        return new Ok<>(value);
    }

    public static <T, E> Result<T, E> err(E error) {
        return new Err<>(error);
    }

    public abstract boolean isOk();
    public abstract boolean isErr();

    public abstract Optional<T> toOptional();

    public <U> Result<U, E> map(Function<? super T, ? extends U> mapper) {
        if (this instanceof Ok<T, E> ok) {
            return new Ok<>(mapper.apply(ok.value));
        }
        @SuppressWarnings("unchecked")
        Result<U, E> self = (Result<U, E>) this;
        return self;
    }

    public <U> Result<U, E> flatMap(Function<? super T, Result<U, E>> binder) {
        if (this instanceof Ok<T, E> ok) {
            return Objects.requireNonNull(binder.apply(ok.value));
        }
        @SuppressWarnings("unchecked")
        Result<U, E> self = (Result<U, E>) this;
        return self;
    }

    public <F> Result<T, F> mapError(Function<? super E, ? extends F> mapper) {
        if (this instanceof Err<T, E> err) {
            return new Err<>(mapper.apply(err.error));
        }
        @SuppressWarnings("unchecked")
        Result<T, F> self = (Result<T, F>) this;
        return self;
    }

    public Result<T, E> tap(Consumer<? super T> sideEffect) {
        if (this instanceof Ok<T, E> ok) {
            sideEffect.accept(ok.value);
        }
        return this;
    }

    public Result<T, E> tapError(Consumer<? super E> sideEffect) {
        if (this instanceof Err<T, E> err) {
            sideEffect.accept(err.error);
        }
        return this;
    }

    public T getOrElse(Function<? super E, ? extends T> fallback) {
        if (this instanceof Ok<T, E> ok) return ok.value;
        Err<T, E> err = (Err<T, E>) this;
        return fallback.apply(err.error);
    }

    public static final class Ok<T, E> extends Result<T, E> {
        private final T value;
        private Ok(T value) { this.value = value; }
        @Override public boolean isOk() { return true; }
        @Override public boolean isErr() { return false; }
        @Override public Optional<T> toOptional() { return Optional.ofNullable(value); }
        @Override public String toString() { return "Ok(" + value + ")"; }
    }

    public static final class Err<T, E> extends Result<T, E> {
        private final E error;
        private Err(E error) { this.error = error; }
        @Override public boolean isOk() { return false; }
        @Override public boolean isErr() { return true; }
        @Override public Optional<T> toOptional() { return Optional.empty(); }
        @Override public String toString() { return "Err(" + error + ")"; }
    }
}


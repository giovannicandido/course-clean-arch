package pro.gsilva.courses.cleanarch.domain.functions;

import pro.gsilva.courses.cleanarch.domain.DomainId;
import pro.gsilva.courses.cleanarch.domain.DomainWithID;

import java.util.function.Consumer;
import java.util.function.Function;

public class NullFunctions {

    /**
     * Applies the provided function to the given object if it is not null.
     *
     * @param object the object to apply the function to
     * @param function the function to apply
     * @param <T> the type of the object
     * @param <R> the return type of the function
     * @return the result of applying the function to the object, or null if the object is null
     */
    public static <T, R> R ofNullable(T object, Function<T, R> function) {
        return object != null ? function.apply(object) : null;
    }

    /**
     * Applies the provided function to the given object if it is not null.
     *
     * @param object the object to apply the function to
     * @param function the function to apply
     * @param defaultValue the default value to return if the object is null
     * @param <T> the type of the object
     * @param <R> the return type of the function
     * @return the result of applying the function to the object, or the defaultValue if the object is null
     */
    public static <T, R> R ofNullable(T object, Function<T, R> function, R defaultValue) {
        return object != null ? function.apply(object) : defaultValue;
    }

    /**
     * Unwraps the ID value from a DomainId object if it is not null.
     * The domain id needs to implement {@link DomainId} interface
     * @param object the DomainId object to unwrap the ID from
     * @param <ID> the type of the ID value
     * @param <T> the type of the DomainId object
     * @return the ID value of the DomainId object, or null if the object is null
     * @see DomainId
     */
    public static <ID, T extends DomainId<ID>> ID unwrapID(T object) {
        return object == null ? null : object.getId();
    }

    /**
     * Checks if the ID value of the given DomainId object is null or not.
     * The domain id needs to implement {@link DomainId} interface
     * @param domainId the DomainId object to check
     * @param <ID> the type of the ID value
     * @param <T> the type of the DomainId object
     * @return true if the ID value is not null, false otherwise
     * @see DomainId
     */
    public static <ID, T extends DomainId<ID>> boolean isIdNull(T domainId) {
        return domainId != null && domainId.getId() != null;
    }

    /**
     * Checks if the ID value of the given Domain object is null or not.
     * The domain needs to implement {@link DomainWithID} interface
     * @param domain The domain to check
     * @param <T> representation of a DomainWithID domain object
     * @return true if the ID value is not null, false otherwise
     * @see pro.gsilva.courses.cleanarch.domain.DomainWithID
     */
    public static <T extends DomainWithID> boolean isIdNull(T domain) {
        return domain != null && !isIdNull(domain.getDomainId());
    }

    /**
     * Unwraps the ID value from a Domain object if it is not null.
     * The domain needs to implement {@link DomainWithID} interface
     * @param <ID> the type of the ID value
     * @param <T> the type of the DomainId object
     * @param domain the DomainId object to unwrap the ID from
     * @return the ID value of the DomainId object, or null if the object is null
     * @see pro.gsilva.courses.cleanarch.domain.DomainWithID
     */
    public static <ID, T extends DomainWithID<ID>> ID unwrapID(T domain) {
        return domain == null ? null : unwrapID(domain.getDomainId());
    }


    /**
     * Executes the provided function on the given object if it is not null.
     *
     * @param object   the object to apply the function to
     * @param function the function to apply
     * @param <T>      the type of the object
     */
    public static <T> void executeIfNotNull(T object, Consumer<T> function) {
        if(object != null) {
            function.accept(object);
        }
    }
}

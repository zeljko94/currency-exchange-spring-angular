package hr.dice.assignments.currency_exchange.dto;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data transfer object (DTO) representing an error response.
 */
public class ErrorDto {

    private LocalDateTime timestamp;
    private String message;
    private String path;
    private Map<String, String> errors;

    /**
     * Constructs a new ErrorDto with the specified timestamp, message, path, and errors.
     *
     * @param timestamp The timestamp when the error occurred.
     * @param message   The error message.
     * @param path      The URL path where the error occurred.
     * @param errors    A map containing detailed error information (field name -> error message).
     */
    public ErrorDto(LocalDateTime timestamp, String message, String path, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    /**
     * Retrieves the timestamp when the error occurred.
     *
     * @return The timestamp when the error occurred.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the error occurred.
     *
     * @param timestamp The timestamp when the error occurred.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Retrieves the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message The error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the URL path where the error occurred.
     *
     * @return The URL path where the error occurred.
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the URL path where the error occurred.
     *
     * @param path The URL path where the error occurred.
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Retrieves the detailed error information as a map.
     *
     * @return A map containing detailed error information (field name -> error message).
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Sets the detailed error information.
     *
     * @param errors A map containing detailed error information (field name -> error message).
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

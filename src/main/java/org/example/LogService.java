package org.example;


import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class LogService {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String _secondaryApiKey = "1b5fb716-1939-4bdc-8e48-948e5bcab646";
    private static final String _secretKey = "98848059008560441735960375228163";

/*
     public static void main(String[] args) {
         String endpoint = "http://localhost:5121/api/Log";

         // Call the logErrorAsync method
         CompletableFuture<Void> logFuture = logInfoAsync(endpoint, "Info123 ", "Entry Source", "Entry Operation","care","connect","ckey","cval","mkey", "mval");
         System.out.println("Logs Sent Successfully");
         logFuture.join();
     }
*/

    public static CompletableFuture<Void> logInfoAsync(String endpoint, String message, String source,
                                                       String operation, String applicationName,
                                                       String moduleName, String contextKey, String contextValue,
                                                       String metadataKey, String metadataValue) {

        LogEntryDTO logEntry = createLogEntry(message, source, operation, applicationName, moduleName);
        String logRequest;

        try {
            logRequest = createLogRequest("Info", logEntry, contextKey, contextValue,
                    metadataKey, metadataValue);
        } catch (Exception e) {
            System.err.println("Error creating log request: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        // Ensure the logRequest is properly escaped for JSON
        String jsonString = "\"" + logRequest.replace("\"", "\\\"") + "\"";

        System.out.println(jsonString); // Log the JSON request

        // Create an HttpRequest with the JSON body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("SecondaryApiKey", _secondaryApiKey)
                .header("Content-Type", "application/json") // Keep content type as application/json
                .POST(BodyPublishers.ofString(jsonString)) // Send JSON string directly
                .build();

        // Send the request asynchronously
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 201) {
                        System.err.println("Response Body: " + response.body()); // Log response body for debugging
                        throw new RuntimeException("Failed to log error: " + response.statusCode());
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Error occurred: " + ex.getMessage());
                    return null;
                });
    }
    public static CompletableFuture<Void> logDebugAsync(String endpoint, String message, String source,
                                                        String operation, String applicationName,
                                                        String moduleName, String contextKey, String contextValue,
                                                        String metadataKey, String metadataValue) {

        LogEntryDTO logEntry = createLogEntry(message, source, operation, applicationName, moduleName);
        String logRequest;

        try {
            logRequest = createLogRequest("Debug", logEntry, contextKey, contextValue,
                    metadataKey, metadataValue);
        } catch (Exception e) {
            System.err.println("Error creating log request: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        // Ensure the logRequest is properly escaped for JSON
        String jsonString = "\"" + logRequest.replace("\"", "\\\"") + "\"";

        System.out.println(jsonString); // Log the JSON request

        // Create an HttpRequest with the JSON body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("SecondaryApiKey", _secondaryApiKey)
                .header("Content-Type", "application/json") // Keep content type as application/json
                .POST(BodyPublishers.ofString(jsonString)) // Send JSON string directly
                .build();

        // Send the request asynchronously
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 201) {
                        System.err.println("Response Body: " + response.body()); // Log response body for debugging
                        throw new RuntimeException("Failed to log error: " + response.statusCode());
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Error occurred: " + ex.getMessage());
                    return null;
                });
    }

    public static CompletableFuture<Void> logWarningAsync(String endpoint, String message, String source,
                                                          String operation, String applicationName,
                                                          String moduleName, String contextKey, String contextValue,
                                                          String metadataKey, String metadataValue) {

        LogEntryDTO logEntry = createLogEntry(message, source, operation, applicationName, moduleName);
        String logRequest;

        try {
            logRequest = createLogRequest("Warning", logEntry, contextKey, contextValue,
                    metadataKey, metadataValue);
        } catch (Exception e) {
            System.err.println("Error creating log request: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        // Ensure the logRequest is properly escaped for JSON
        String jsonString = "\"" + logRequest.replace("\"", "\\\"") + "\"";

        System.out.println(jsonString); // Log the JSON request

        // Create an HttpRequest with the JSON body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("SecondaryApiKey", _secondaryApiKey)
                .header("Content-Type", "application/json") // Keep content type as application/json
                .POST(BodyPublishers.ofString(jsonString)) // Send JSON string directly
                .build();

        // Send the request asynchronously
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 201) {
                        System.err.println("Response Body: " + response.body()); // Log response body for debugging
                        throw new RuntimeException("Failed to log error: " + response.statusCode());
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Error occurred: " + ex.getMessage());
                    return null;
                });
    }

    public static CompletableFuture<Void> logErrorAsync(String endpoint, String message, String source,
                                                        String operation, String applicationName,
                                                        String moduleName, String exceptionType,
                                                        String exceptionMessage, String exceptionStackTrace,
                                                        String contextKey, String contextValue,
                                                        String metadataKey, String metadataValue) {

        LogEntryDTO logEntry = createLogEntry(message, source, operation, applicationName, moduleName);
        String logRequest;

        try {
            logRequest = createLogRequestForError("Error", logEntry, contextKey, contextValue,
                    metadataKey, metadataValue, exceptionType,
                    exceptionMessage, exceptionStackTrace);
        } catch (Exception e) {
            System.err.println("Error creating log request: " + e.getMessage());
            return CompletableFuture.failedFuture(e);
        }

        // Ensure the logRequest is properly escaped for JSON
        String jsonString = "\"" + logRequest.replace("\"", "\\\"") + "\"";

        System.out.println(jsonString); // Log the JSON request

        // Create an HttpRequest with the JSON body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("SecondaryApiKey", _secondaryApiKey)
                .header("Content-Type", "application/json") // Keep content type as application/json
                .POST(BodyPublishers.ofString(jsonString)) // Send JSON string directly
                .build();

        // Send the request asynchronously
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 201) {
                        System.err.println("Response Body: " + response.body()); // Log response body for debugging
                        throw new RuntimeException("Failed to log error: " + response.statusCode());
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Error occurred: " + ex.getMessage());
                    return null;
                });
    }

    private static LogEntryDTO createLogEntry(String message, String source, String operation, String applicationName, String moduleName) {
        LogEntryDTO LogEntry = new LogEntryDTO();
        LogEntry.setTimestamp(ZonedDateTime.now());
        LogEntry.setMessage(message);
        LogEntry.setSource(source);
        LogEntry.setOperation(operation);
        LogEntry.setApplicationName(applicationName);
        LogEntry.setModuleName(moduleName);

        return LogEntry;
    }

    private static String createLogRequest(String logLevel, LogEntryDTO logEntry,
                                           String contextKey, String contextValue,
                                           String metadataKey, String metadataValue) throws Exception {

        List<LogContextDTO> Context = new ArrayList<>();
        if (contextKey != null && !contextKey.isEmpty() && contextValue != null && !contextValue.isEmpty()) {
            LogContextDTO LogContext = new LogContextDTO();
            LogContext.setContextKey(contextKey);
            LogContext.setContextValue(contextValue);
            Context.add(LogContext);
        }

        List<LogMetadataDTO> Metadata = new ArrayList<>();
        if (metadataKey != null && !metadataKey.isEmpty() && metadataValue != null && !metadataValue.isEmpty()) {
            LogMetadataDTO LogMetadata = new LogMetadataDTO();
            LogMetadata.setKey(metadataKey);
            LogMetadata.setValue(metadataValue);
            Metadata.add(LogMetadata);
        }

        LogRequest LogRequest = new LogRequest();
        LogRequest.setLogLevel(logLevel);
        LogRequest.setLogEntry(logEntry);
        LogRequest.setContext(Context);
        LogRequest.setMetaData(Metadata);


        // Define and configure ObjectMapper with custom serializer and date format
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the module
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        // Custom serializer to capitalize field names
        JsonSerializer<Object> serializer = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                for (Field field : value.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object fieldValue = field.get(value);
                        String fieldName = field.getName();
                        String capitalizedFieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                        gen.writeObjectField(capitalizedFieldName, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new IOException("Error accessing field: " + field.getName(), e);
                    }
                }
                gen.writeEndObject();
            }
        };

        SimpleModule module = new SimpleModule();
        module.addSerializer(LogRequest.class, serializer);
        module.addSerializer(LogEntryDTO.class, serializer);
        module.addSerializer(LogContextDTO.class, serializer);
        module.addSerializer(LogMetadataDTO.class, serializer);
        module.addSerializer(ExceptionDTO.class, serializer);
        objectMapper.registerModule(module);

        String requestBody = objectMapper.writeValueAsString(LogRequest);

        // Log the plain JSON request body
        System.out.println("Plain JSON Request Body: " + requestBody);

        // Encrypt the request body using the secret key
        String logRequestEncrypted = encryptString(requestBody, _secretKey);
        System.out.println("Encrypted Request: " + logRequestEncrypted.length());
        // Return the encrypted request body directly
        return logRequestEncrypted;
    }
    private static String createLogRequestForError(String logLevel, LogEntryDTO logEntry,
                                                   String contextKey, String contextValue,
                                                   String metadataKey, String metadataValue,
                                                   String exceptionType, String exceptionMessage,
                                                   String exceptionStackTrace) throws Exception {

        List<LogContextDTO> Context = new ArrayList<>();
        if (contextKey != null && !contextKey.isEmpty() && contextValue != null && !contextValue.isEmpty()) {
            LogContextDTO LogContext = new LogContextDTO();
            LogContext.setContextKey(contextKey);
            LogContext.setContextValue(contextValue);
            Context.add(LogContext);
        }

        List<LogMetadataDTO> Metadata = new ArrayList<>();
        if (metadataKey != null && !metadataKey.isEmpty() && metadataValue != null && !metadataValue.isEmpty()) {
            LogMetadataDTO LogMetadata = new LogMetadataDTO();
            LogMetadata.setKey(metadataKey);
            LogMetadata.setValue(metadataValue);
            Metadata.add(LogMetadata);
        }

        LogRequest LogRequest = new LogRequest();
        LogRequest.setLogLevel(logLevel);
        LogRequest.setLogEntry(logEntry);
        LogRequest.setContext(Context);
        LogRequest.setMetaData(Metadata);

        if (exceptionType != null && !exceptionType.isEmpty() &&
                exceptionMessage != null && !exceptionMessage.isEmpty() &&
                exceptionStackTrace != null && !exceptionStackTrace.isEmpty()) {
            ExceptionDTO exceptionDTO = new ExceptionDTO();
            exceptionDTO.setExceptionType(exceptionType);
            exceptionDTO.setMessage(exceptionMessage);
            exceptionDTO.setStackTrace(exceptionStackTrace);
            LogRequest.setException(exceptionDTO);
        }

        // Define and configure ObjectMapper with custom serializer and date format
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the module
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));

        // Custom serializer to capitalize field names
        JsonSerializer<Object> serializer = new JsonSerializer<>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                for (Field field : value.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object fieldValue = field.get(value);
                        String fieldName = field.getName();
                        String capitalizedFieldName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                        gen.writeObjectField(capitalizedFieldName, fieldValue);
                    } catch (IllegalAccessException e) {
                        throw new IOException("Error accessing field: " + field.getName(), e);
                    }
                }
                gen.writeEndObject();
            }
        };

        SimpleModule module = new SimpleModule();
        module.addSerializer(LogRequest.class, serializer);
        module.addSerializer(LogEntryDTO.class, serializer);
        module.addSerializer(LogContextDTO.class, serializer);
        module.addSerializer(LogMetadataDTO.class, serializer);
        module.addSerializer(ExceptionDTO.class, serializer);
        objectMapper.registerModule(module);

        String requestBody = objectMapper.writeValueAsString(LogRequest);

        // Log the plain JSON request body
        System.out.println("Plain JSON Request Body: " + requestBody);

        // Encrypt the request body using the secret key
        String logRequestEncrypted = encryptString(requestBody, _secretKey);
        System.out.println("Encrypted Request: " + logRequestEncrypted.length());

        // Return the encrypted request body directly
        return logRequestEncrypted;
    }



    private static String encryptString(String plainText, String secretKey) throws Exception {
        // Ensure the key is in bytes and the appropriate length for AES (16, 24, or 32 bytes)
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        if (key.length != 32) {
            throw new IllegalArgumentException("Secret key must be 32 bytes for AES-256 encryption.");
        }
        byte[] iv = "0000000000000000".getBytes(StandardCharsets.UTF_8); // 16-byte IV (zeros)

        // Create AES cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParams);

        // Encrypt the data
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Return the encrypted data as a Base64 string
        return Base64.getEncoder().encodeToString(encrypted);
    }
}
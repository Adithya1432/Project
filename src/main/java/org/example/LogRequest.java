package org.example;

import java.time.ZonedDateTime;
import java.util.List;


public class LogRequest {
    private String LogLevel;
    private LogEntryDTO LogEntry;
    private List<LogContextDTO> Context;
    private List<LogMetadataDTO> MetaData;
    private ExceptionDTO Exception;

    // Getters and Setters
    public String getLogLevel() {
        return LogLevel;
    }

    public void setLogLevel(String LogLevel) {
        this.LogLevel = LogLevel;
    }

    public LogEntryDTO getLogEntry() {
        return LogEntry;
    }

    public void setLogEntry(LogEntryDTO LogEntry) {
        this.LogEntry = LogEntry;
    }

    public List<LogContextDTO> getContext() {
        return Context;
    }

    public void setContext(List<LogContextDTO> Context) {
        this.Context = Context;
    }

    public List<LogMetadataDTO> getMetaData() {
        return MetaData;
    }

    public void setMetaData(List<LogMetadataDTO> MetaData) {
        this.MetaData = MetaData;
    }

    public ExceptionDTO getException() {
        return Exception;
    }

    public void setException(ExceptionDTO Exception) {
        this.Exception = Exception;
    }
}

class LogEntryDTO {
    private ZonedDateTime Timestamp;
    private String Message;
    private String Source;
    private String Operation;
    private String ApplicationName;
    private String ModuleName;

    // Getters and Setters
    public ZonedDateTime getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(ZonedDateTime Timestamp) {
        this.Timestamp = Timestamp;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String Operation) {
        this.Operation = Operation;
    }

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setApplicationName(String ApplicationName) {
        this.ApplicationName = ApplicationName;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String ModuleName) {
        this.ModuleName = ModuleName;
    }
}

class LogContextDTO {
    private String ContextKey;
    private String ContextValue;

    // Getters and Setters
    public String getContextKey() {
        return ContextKey;
    }

    public void setContextKey(String ContextKey) {
        this.ContextKey = ContextKey;
    }

    public String getContextValue() {
        return ContextValue;
    }

    public void setContextValue(String ContextValue) {
        this.ContextValue = ContextValue;
    }
}

class LogMetadataDTO {
    private String Key;
    private String Value;

    // Getters and Setters
    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }
}

class ExceptionDTO {
    private String ExceptionType;
    private String Message;
    private String StackTrace;

    // Getters and Setters
    public String getExceptionType() {
        return ExceptionType;
    }

    public void setExceptionType(String ExceptionType) {
        this.ExceptionType = ExceptionType;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getStackTrace() {
        return StackTrace;
    }

    public void setStackTrace(String StackTrace) {
        this.StackTrace = StackTrace;
    }
}
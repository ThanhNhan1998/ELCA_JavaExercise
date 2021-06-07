package com.elca.exception;

public class FileExtensionUnformatted extends Exception{
    @Override
    public String getMessage() {
        return "file extension unformatted";
    }
}

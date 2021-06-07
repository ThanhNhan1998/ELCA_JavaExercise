package com.elca.exception;

public class FileExtensionUnSupported extends Exception{
    public FileExtensionUnSupported(){
        super("This file extension is unsupported");
    }
}

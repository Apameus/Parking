package org.example;

@FunctionalInterface
public interface GUIAction {
    String execute(String input) throws GUIException;

}

package org.example;

public interface Command {
    void execute(Repository repository, String[] args) throws Exception;
}

package org.main.commands;

public interface ICommand {

    /**
     * @param args unstructured array of String arguments.
     *             Make sure to document what each argument index corresponds to.
     */
    void execute(String[] args);

    /**
     * Should perform the exact opposite of tasks in the execute method.
     * Make sure to notify users what is being undone.
     * Undo method is required to be implemented.
     * Implement it, but leave the definition empty if your command doesn't need to undo anything
     */
    void undo();
}

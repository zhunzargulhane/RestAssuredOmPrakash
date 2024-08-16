package com.wba.pojo.workspace;

public class WorkspaceRoot {
    private WorkspaceNestedPOJO workspace;

    public WorkspaceRoot() {
    }

    public WorkspaceRoot(WorkspaceNestedPOJO workspace) {
        this.workspace = workspace;
    }

    public WorkspaceNestedPOJO getWorkspace() {
        return workspace;
    }

    public void setWorkspace(WorkspaceNestedPOJO workspace) {
        this.workspace = workspace;
    }


}

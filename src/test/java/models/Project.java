package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.NotNull;
import enums.AccessType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

public class Project {

    @Data
    @ToString
    @Accessors(chain = true)
    public class ProjectModel {
        @Expose(serialize = false)
        private int id;
        @NotNull
        @Expose
        private String title;
        @NotNull
        @Expose
        private String code;
        @Expose(serialize = false)
        private String description;
        @Expose(serialize = false)
        private AccessType access;
        @Expose(serialize = false)
        private String group;

    }
}

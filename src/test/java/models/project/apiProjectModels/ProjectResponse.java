package models.project.apiProjectModels;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class ProjectResponse {

    @NotNull
    @Expose
    private boolean status;

    @NotNull
    @Expose
    private Result result;
}

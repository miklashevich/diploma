package models.project.apiProjectModels;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class Result {

    @NotNull
    @Expose
    private String title;

    @NotNull
    @Expose
    private String code;

    public String getCode() {
        return code;
    }
}

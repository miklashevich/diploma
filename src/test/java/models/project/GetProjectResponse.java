package models.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.NotNull;
import enums.AccessType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class GetProjectResponse {

    @NotNull
    @Expose
    private boolean status;

    @NotNull
    @Expose
    private Result result;
}

package models.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sun.istack.NotNull;
import enums.AccessType;
import io.restassured.mapper.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Builder
@Accessors(chain = true)
public class Project {

    @NotNull
    @Expose
    @EqualsAndHashCode.Exclude
    int id;
    @NotNull
    @Expose
    private String title;
    @NotNull
    @Expose
    private String code;
    @Expose
    private String description;
    @NotNull
    @Expose
    private AccessType access;
    @Expose
    @SerializedName(value = "group")

    /**
     Required if access param is set to 'group'
     **/
    private String groupAccess;
}

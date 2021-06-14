package models.project;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import enums.AccessType;
import io.restassured.mapper.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Builder
@Accessors(chain = true)
public class Project {

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
    /**
     Required if access param is set to 'group'
     **/
    private String group;
}

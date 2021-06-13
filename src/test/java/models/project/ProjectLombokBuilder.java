package models.project;

import enums.ProjectType;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;


@Data
@Builder
@ToString
@Accessors(chain = true)
public class ProjectLombokBuilder {

     String name;
     String code;
     String description;
     ProjectType type;

}
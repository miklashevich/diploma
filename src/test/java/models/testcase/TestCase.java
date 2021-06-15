package models.testcase;

import com.google.gson.annotations.Expose;
import com.sun.istack.NotNull;
import enums.AccessType;
import enums.testCaseAttributes.AutomationStatusAttribute;
import enums.testCaseAttributes.PriorityAttribute;
import enums.testCaseAttributes.SeverityAttribute;
import enums.testCaseAttributes.TypeAttribute;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Builder
@Accessors(chain = true)
public class TestCase {


    @NotNull
    @Expose
    private String title;
    @Expose
    private String code;
    @Expose
    private SeverityAttribute severity;
    @Expose
    private PriorityAttribute priority;
    @Expose
    private TypeAttribute type;
    @Expose
    private AutomationStatusAttribute automationStatus;

}

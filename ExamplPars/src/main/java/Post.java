import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @JsonAlias("mi.mitnumber")
    String mitNumber;
    @JsonAlias("mi.number")
    String number;
    @JsonAlias("valid_date")
    LocalDate validDate;
    @JsonAlias("verification_date")
    LocalDate verificationDate;
    @JsonAlias("mi.mitype")
    String miType;
    @JsonAlias("org_title")
    String orgTitle;
    //    @JsonAlias("applicability")
    Boolean applicability;
    @JsonAlias("vri_id")
    String id;


}

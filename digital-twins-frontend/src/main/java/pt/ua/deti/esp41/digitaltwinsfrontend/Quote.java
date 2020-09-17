package pt.ua.deti.esp41.digitaltwinsfrontend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Quote {
    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private Value value;
}

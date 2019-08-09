package starter.model.goods2;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 2019/8/9 11:16
 *
 * @author caishiyu
 */
@Data
public class Property {
    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String value;
}

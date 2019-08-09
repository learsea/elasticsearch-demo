package starter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "goods-test", type = "goods")
public class Goods2 {
    /**
     * {
     * "id": 1,
     * "title": "皇家小奶罐幼猫粮奶糕1-4月孕猫离乳期断奶猫粮BK34/400g*3袋",
     * "supplier": "皇家宠物食品旗舰店 ",
     * "properties": [{
     * "property": "品牌",
     * "propertyValue": "ROYAL CANIN/皇家"
     * },
     * {
     * "property": "适用阶段",
     * "propertyValue": "幼猫"
     * },
     * {
     * "property": "包装体积",
     * "propertyValue": "0.2"
     * },
     * {
     * "property": "毛重",
     * "propertyValue": "300g"
     * }
     * <p>
     * ],
     * <p>
     * "stock": 3000
     * }
     **/
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Nested)
    private List<Property> propertyList;

    @Field(type = FieldType.Keyword)
    private String supplier;

    private Float stock;
}

package starter.model.goods2;

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
@Document(indexName = "goods2", type = "goods")
public class Goods2 {
    /**
     {
     "id": 1,
     "title": "皇家小奶罐幼猫粮奶糕1-4月孕猫离乳期断奶猫粮BK34/400g*3袋",
     "supplier": "皇家宠物食品旗舰店 ",
     "propertyList": [{
     "name": "品牌",
     "value": "ROYAL CANIN/皇家"
     },
     {
     "name": "适用阶段",
     "value": "幼猫"
     },
     {
     "name": "包装体积",
     "value": "0.2"
     },
     {
     "name": "毛重",
     "value": "300g"
     }
     ],
     "stock": 3000
     }
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

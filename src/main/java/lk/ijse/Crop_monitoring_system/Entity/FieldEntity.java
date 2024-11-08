package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Field")
public class FieldEntity {
    @Id
    @Column(name = "field_id" , length = 45)
    private String FieldId;

    @Column(name = "field_name")
    private String FieldName;

    private String Field;

    @Column(name = "extent_size_of_the_field")
    private Double ExtentSizeOfTheField;
   /* private List<> crops;
    private Staff<> staff;
    private LongText FieldImage1;
    private Longtext FieldImage2;*/


}

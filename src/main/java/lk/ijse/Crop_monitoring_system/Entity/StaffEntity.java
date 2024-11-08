package lk.ijse.Crop_monitoring_system.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Staff")
public class StaffEntity {
    @Id
    @Column(name = "staff_id" , length = 45)

    private String StaffId;
    private String FirstName;
    private String LastName;
    private String Designation;
    private Enum Gender;
    private Date JoinedDate;
    private Date DOB;
    private String AddressLine01;
    private String AddressLine02;
    private String AddressLine03;
    private String AddressLine04;
    private String AddressLine05;
    private String ContactNo;
    private String Email;
    private String Role;
    private String Field;
    private String Vehicle;





}

package Entity;


import javax.persistence.*;

@Entity
@Table(name = "InstructorDetail")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cid")
    private Instructor instructor;


    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorDetail(String hobby) {
        this.hobby = hobby;
    }

    public InstructorDetail() {
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}

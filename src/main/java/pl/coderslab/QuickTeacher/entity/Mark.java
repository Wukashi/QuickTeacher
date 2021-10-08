package pl.coderslab.QuickTeacher.entity;

import javax.persistence.*;

@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public Mark setId(Long id) {
        this.id = id;
        return this;
    }
}




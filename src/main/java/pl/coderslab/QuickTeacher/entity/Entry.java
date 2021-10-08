package pl.coderslab.QuickTeacher.entity;

import javax.persistence.*;
@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

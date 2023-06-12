package com.codestates.todoApp.todo.entity;

import com.codestates.todoApp.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Entity(name="TODOS")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long todoId;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;


    @Column(nullable = false)
    private String title;

    @Column
    private int todoOrder;

    @Column
    private boolean completed;

    public Todo(String title, int todoOrder, boolean completed) {
        this.title = title;
        this.todoOrder = todoOrder;
        this.completed = completed;
    }

    public void addMember(Member member){
        this.member=member;
    }
}

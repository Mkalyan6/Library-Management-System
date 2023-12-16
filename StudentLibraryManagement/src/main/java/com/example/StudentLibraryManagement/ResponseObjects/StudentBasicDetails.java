package com.example.StudentLibraryManagement.ResponseObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentBasicDetails {
    private  String name;
    private Integer age;
    private String maiId;
}

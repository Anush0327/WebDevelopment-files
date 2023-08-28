package com.prodapt.learning.model.Rankings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.student.Student;

public class RankingModel {
	List<Student> students;
	public RankingModel() {
		students = new ArrayList<>();
	}
	
	public void addStudent(String name,String score) {
		Student s = new Student();
		s.setName(name);
		s.setScore(Integer.parseInt(score));
		students.add(s);
	}
	
	public void updateStudent(int index, String name, String score) {
	    if (index >= 0 && index < students.size()) {
	        Student student = students.get(index);
	        student.setName(name);
	        student.setScore(Integer.parseInt(score));
	    }
	}
	
	public void sort() {
		students.sort((s1,s2)-> s2.getScore()-s1.getScore());
	}
	
	public List<Student> getList(){
		return students;
	}
	
	public void setRanks() {
		sort();
		int rank = 1;
        students.get(0).setRank(rank);
        
        for (int i = 1; i < students.size(); i++) {
            if (students.get(i).getScore() == students.get(i - 1).getScore()) {
                students.get(i).setRank(rank);
            } else {
                students.get(i).setRank(i + 1);
                rank = i + 1;
            }
        }
	}
}

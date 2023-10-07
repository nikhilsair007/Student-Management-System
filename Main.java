/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
class Student {
    private String name;
    private String rollNumber;
    private String grade;
    private Map<String, String> additionalDetails;
    public Student(String name, String rollNumber, String grade, Map<String, String> additionalDetails)
        {this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.additionalDetails = additionalDetails;
        }
    public String toString()
        {return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + ", Additional Details: " + additionalDetails;
        }
    public String getRollNumber()
        {return rollNumber;
        }
    }
class StudentManagementSystem
    {private StudentManagementSystem sms;
    private List<Student> students;
    public StudentManagementSystem()
        {sms = this;
        students = new ArrayList<>();
        }

    public void addStudent(Student student)
        {if (isRollNumberUnique(student.getRollNumber()))
            {students.add(student);
            JOptionPane.showMessageDialog(null, "Student added successfully.");
            }
            else
            {
            JOptionPane.showMessageDialog(null, "Student with the same roll number already exists.");
            }
        }

    private boolean isRollNumberUnique(String rollNumber)
        {for (Student student : students)
            {if (student.getRollNumber().equals(rollNumber))
                {return false;
                }
            }
        return true;
        }

    public void removeStudent(String rollNumber)
        {students.removeIf(student -> student.getRollNumber().equals(rollNumber));
        }

    public Student searchStudent(String rollNumber)
        {for (Student student : students)
            {if (student.getRollNumber().equals(rollNumber))
                {return student;
                }
            }
        return null;
        }

    public List<Student> getAllStudents()
        {return students;
        }

    public void createAndShowGUI()
        {JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add Student", createAddStudentPanel());
        tabbedPane.addTab("Search Student", createSearchStudentPanel());
        tabbedPane.addTab("Remove Student", createRemoveStudentPanel());
        tabbedPane.addTab("Display Students", createDisplayStudentsPanel());
        tabbedPane.setBackground(Color.lightGray);
        tabbedPane.setForeground(Color.darkGray);
        frame.add(tabbedPane);
        frame.pack();
        frame.setVisible(true);
        }

    private JPanel createAddStudentPanel()
        {JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBackground(Color.white);
        panel.setForeground(Color.black);
        JTextField nameField = new JTextField();
        JTextField rollNumberField = new JTextField();
        JTextField gradeField = new JTextField();
        JTextField detailsField = new JTextField();
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Roll Number:"));
        panel.add(rollNumberField);
        panel.add(new JLabel("Grade:"));
        panel.add(gradeField);
        panel.add(new JLabel("Additional Details (key-value pairs separated by ';'):"));
        panel.add(detailsField);
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener()
            {public void actionPerformed(ActionEvent e)
                {String name = nameField.getText();
                String rollNumber = rollNumberField.getText();
                String grade = gradeField.getText();
                Map<String, String> additionalDetails = new HashMap<>();
                String detailsInput = detailsField.getText();
                String[] detailPairs = detailsInput.split(";");
                for (String pair : detailPairs)
                    {String[] keyValue = pair.split("=");
                    if (keyValue.length == 2)
                        {additionalDetails.put(keyValue[0], keyValue[1]);
                        }
                    }
                Student newStudent = new Student(name, rollNumber, grade, additionalDetails);
                sms.addStudent(newStudent);
                }
            });
        panel.add(addButton);
        return panel;
        }

    private JPanel createSearchStudentPanel()
        {JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JTextField searchRollNumberField = new JTextField(10);
        panel.add(new JLabel("Enter roll number to search:"));
        panel.add(searchRollNumberField);
        panel.setBackground(Color.white);
        panel.setForeground(Color.black);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener()
            {public void actionPerformed(ActionEvent e)
                {String searchRollNumber = searchRollNumberField.getText();
                Student foundStudent = sms.searchStudent(searchRollNumber);
                if (foundStudent != null)
                    {JOptionPane.showMessageDialog(null, "Student found: " + foundStudent.toString());
                    }
                else
                    {JOptionPane.showMessageDialog(null, "No student found with roll number: " + searchRollNumber);
                    }
                }
            });
        panel.add(searchButton);
        return panel;
        }

    private JPanel createRemoveStudentPanel()
        {JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JTextField removeRollNumberField = new JTextField(10);
        panel.add(new JLabel("Enter roll number to remove:"));
        panel.add(removeRollNumberField);
        panel.setBackground(Color.white);
        panel.setForeground(Color.black);
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener()
            {public void actionPerformed(ActionEvent e)
                {String removeRollNumber = removeRollNumberField.getText();
                sms.removeStudent(removeRollNumber);
                JOptionPane.showMessageDialog(null, "Student with roll number " + removeRollNumber + " has been removed.");
                }
            });
        panel.add(removeButton);
        return panel;
        }

    private JPanel createDisplayStudentsPanel()
        {JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBackground(Color.white);
        panel.setForeground(Color.black);
        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener()
            {public void actionPerformed(ActionEvent e)
                {textArea.setText("");
                for (Student student : sms.getAllStudents())
                    {textArea.append(student.toString() + "\n");
                    }
                }
            });
        panel.add(displayButton, BorderLayout.SOUTH);
        return panel;
        }
    }

public class Main
    {public static void main(String[] args)
        {SwingUtilities.invokeLater(new Runnable()
            {public void run()
                {StudentManagementSystem gui = new StudentManagementSystem();
                gui.createAndShowGUI();
                }
            });
        }
    }

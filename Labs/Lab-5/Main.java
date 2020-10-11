import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Roster roster = new Roster("Main");

        ArrayList<String> records = new ArrayList<String>();
        ArrayList<String> queries = new ArrayList<String>();
        ArrayList<String> queryResults = new ArrayList<String>();

        int numOfRecords = sc.nextInt();

        for (int i = 0; i <= numOfRecords; i++) {

            if (sc.hasNextLine()) {

                String studentRecord = sc.nextLine();
                records.add(studentRecord);
            }
        }

        while (sc.hasNextLine()) {

            String query = sc.nextLine();

            if (query.isEmpty()) {

                break;

            } else {
                queries.add(query);
            }
        }

        for (String studentRecord : records) {

            if (studentRecord.length() <= 0) {
                continue;
            }

            String[] splitStudentRecord = studentRecord.split("\\s+");

            String studentName = splitStudentRecord[0];
            String moduleName = splitStudentRecord[1];
            String assessmentName = splitStudentRecord[2];
            String assessmentGrade = splitStudentRecord[3];

            Optional<Student> existingStudent = roster.get(studentName);

            existingStudent.ifPresentOrElse((student) -> { 
                 
                Optional<Module> existingModule = student.get(moduleName);

                existingModule.ifPresentOrElse((module) -> {

                    Optional<Assessment> existingAssessment = module.get(assessmentName);

                    existingAssessment.ifPresentOrElse((ass) -> {

                        if (ass.getGrade() != assessmentGrade) {

                            // replace old grade w updated grade
                            ass.put(assessmentGrade);
                        } 

                    }, () -> {

                        Assessment newAssessment = new Assessment(assessmentName, assessmentGrade);
                        module.put(newAssessment);
                    });

                }, () -> {

                    Module module = createNewModuleFromRecord(moduleName, 
                                                            assessmentName, 
                                                            assessmentGrade);

                    student.put(module);
                });

            }, () -> {

                Student student = createNewStudentFromRecord(studentName, 
                                                            moduleName, 
                                                            assessmentName, 
                                                            assessmentGrade);

                roster.put(student);
            });
        }

        for (String query : queries) {

            String[] splitQuery = query.split("\\s+");

            String studentName = splitQuery[0];
            String moduleName = splitQuery[1];
            String assessmentName = splitQuery[2];

            String result = roster.getGrade(studentName, moduleName, assessmentName);

            queryResults.add(result);
        }

        for (String result : queryResults) {
            System.out.println(result);
        }
    }

    public static Student createNewStudentFromRecord(String studentName, String moduleName, String assessmentName, String assessmentGrade) {

        Assessment assessment = new Assessment(assessmentName, assessmentGrade);
        Module module = new Module(moduleName).put(assessment);
        Student student = new Student(studentName).put(module);

        return student;
    }

    public static Module createNewModuleFromRecord(String moduleName, String assessmentName, String assessmentGrade) {

        Assessment assessment = new Assessment(assessmentName, assessmentGrade);
        Module module = new Module(moduleName).put(assessment);

        return module;
    }
}

package MVC;

public class StudentController {
    
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }

    // the following methods are useful in order to write data in a viwe
    public void setStudentName(String name){
        model.setName(name);		
    }

    public String getStudentName(){
        return model.getName();		
    }

    public void setStudentRollNo(String rollNo){
        model.setRollNo(rollNo);		
    }

    public String getStudentRollNo(){
        return model.getRollNo();		
    }
    //ES ... create a ViewInsert Class

    public void updateView(){				
        view.printStudentDetails(model.getName(), model.getRollNo());
    } 	
}

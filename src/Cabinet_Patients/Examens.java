package Cabinet_Patients;

	
public class Examens {
	private Object typeExamens;
	private String dateExamens;
	private int id_exam;
	int identifiant;
	private int id_Patient_Exam;
	private int id_Patient;
	private boolean estExamens;
	private String  dateLibelle= "Date: ";
	private int examIsDel;
	private int examDel;






public Examens() {

	typeExamens="";
	dateExamens="";
	id_exam=0;
	estExamens=true;
	examIsDel=1;
	examDel=0;
}

public Examens(int id_exam) {
	identifiant=id_exam;
	typeExamens="";
	dateExamens="";
	id_exam=0;
	estExamens=true;
	examIsDel=1;
	examDel=0;
}


public int getExamIsDel() {
	return examIsDel;
}

public void setExamIsDel(int examIsDel) {
	this.examIsDel = examIsDel;
}

public int getId_Patient_Exam() {
		return identifiant;
	}

	public int setId_Patient_Exam(int id_Patient_Exam) {
		return this.identifiant = id_Patient_Exam;
	}
	
public Object getTypeExamens() {
		return typeExamens;
	}




	public void setTypeExamens(Object typeExamens) {
		this.typeExamens = typeExamens;
	}




	public String getDateExamens() {
		return dateExamens;
	}




	public void setDateExamens(String dateExamens) {
		this.dateExamens = dateExamens;
	}




	public int getId_exam() {
		return id_exam;
	}




	public void setId_exam(int id_exam) {
		this.id_exam = id_exam;
	}




	public int getIdentifiant() {
		return identifiant;
	}




	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public int getId_Patient() {
		return id_Patient;
	}

	public void setId_Patient(int id_Patient) {
		this.id_Patient = id_Patient;
	}
	public boolean isEstExamens() {
		return estExamens;
	}

	public void setEstExamens(boolean estExamens) {
		this.estExamens = estExamens;
	}


	
	public int getExamDel() {
		return examDel;
	}

	public void setExamDel(int examDel) {
		this.examDel = examDel;
	}

	public String toString(){
		
		return identifiant+","+id_Patient+","+examIsDel+","+typeExamens+","+dateExamens;
	}


}
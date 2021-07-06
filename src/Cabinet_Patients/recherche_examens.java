package Cabinet_Patients;

import java.util.ArrayList;

public class recherche_examens {
	
	public static  <arrayExamens> ArrayList<Examens> recherche(ArrayList<Examens> arrayExamens, Object ID_Local, int supprim){
	
		Examens examens_temp;
		ArrayList<Examens> arrayExamens_temp=new ArrayList<Examens>();
		

		//arrayExamens_temp.clear();										// on efface l'array 'arrayExamens_temp'
		for (int i=0; i <arrayExamens.size();i++) {
		
				examens_temp = arrayExamens.get(i);								// recupère la ligne de 'arrayExamens' et le stocke dans 'examens_temp'
			if (examens_temp.getExamIsDel()!=0) {
				/*
				 * Si l(ID_Local est égale à -1 alors on affiche tous les examens
				 */
				if (ID_Local.equals(-1)) {
					examens_temp.setId_Patient_Exam(examens_temp.getId_Patient_Exam()); 
					examens_temp.setId_exam(examens_temp.getId_exam());	
					examens_temp.setExamIsDel(examens_temp.getExamIsDel());	
	
					Object type= examens_temp.getTypeExamens();
					examens_temp.setTypeExamens(type);
					examens_temp.setDateExamens(examens_temp.getDateExamens());
					//panelExam.setSelectedIndex(i);
					arrayExamens_temp.add(examens_temp);
				}
				
				/*
				 * sinon, affiche uniquement les examens correspondant au Patient
				 */
				else {
					
				
					if (ID_Local.equals(examens_temp.getId_Patient())) {		//si l'ID du patient sélectionné est égale à l'ID du fichier examens
										
					if (supprim==1) {										// si supprim==1 on met un 0 pour signaler que l'examens du Patient choisit est supprimé
						arrayExamens.get((examens_temp.getId_Patient_Exam()-1)).setExamIsDel(0); 
										}
	
					/*
					 * on stocke dans 'exam_temp' les donnees du fichier 'examens.txt'
					 * qui corespondent à la section du patient.
					 */
		
					examens_temp.setId_Patient_Exam(examens_temp.getId_Patient_Exam()); 
					examens_temp.setId_exam(examens_temp.getId_exam());	
					examens_temp.setExamIsDel(examens_temp.getExamIsDel());	
	
					Object type= examens_temp.getTypeExamens();
					examens_temp.setTypeExamens(type);
					examens_temp.setDateExamens(examens_temp.getDateExamens());
					//panelExam.setSelectedIndex(i);
					arrayExamens_temp.add(examens_temp);
	
	
				}
				}
		
		}
		}
		return arrayExamens_temp;

		
	}

}

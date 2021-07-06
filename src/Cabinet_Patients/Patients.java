package Cabinet_Patients;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Patients {
	//déclaration de variables

	int identifiant;
	
	private String prenom ;
	private String NSS;
	
	String pattern = "dd-mm-yyyy";
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    private String dateNaissance = dateFormat.format(new Date());
    
	
    SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern);
    private String dateEntree = dateFormat1.format(new Date());
    
    SimpleDateFormat dateFormat2 = new SimpleDateFormat(pattern);
    private String dateSortie = dateFormat2.format(new Date());
	
	private String telephone;
	
	private boolean sexeMasculin;

	private String TextSexe;
	private String nom ;
	private int patientIsDel;


public Patients(int id){
identifiant=id;;
nom ="";
prenom ="";
NSS="";
dateEntree = "" ;
dateSortie ="";
telephone ="";
TextSexe="";
patientIsDel=1;
}

public Patients(){
	identifiant=0;
	nom ="";
	prenom ="";
	NSS="";
	dateEntree = "" ;
	dateSortie ="";
	telephone ="";
	TextSexe="";
	patientIsDel=1;
}

public int getPatientIsDel() {
	return patientIsDel;
}

public void setPatientIsDel(int patientIsDel) {
	this.patientIsDel = patientIsDel;
}

public int setID(int IDUtilisateur){
	return this.identifiant=IDUtilisateur;
}
public int getID(){
	return identifiant;
}

public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public String getPrenom() {
	return prenom;
}


public void setPrenom(String prenom) {
	this.prenom = prenom;
}





public String getNSS() {
	return NSS;
}

public void setNSS(String nSS) {
	NSS = nSS;
}

public String getDateNaissance() {
	return dateNaissance;
}


public void setDateNaissance(String dateNaissance) {
	this.dateNaissance = dateNaissance;
}


public String getDateEntree() {
	return dateEntree;
}


public void setDateEntree(String dateEntree) {
	this.dateEntree = dateEntree;
}


public String getDateSortie() {
	return dateSortie;
}


public void setDateSortie(String dateSortie) {
	this.dateSortie = dateSortie;
}


public String getTelephone() {
	return telephone;
}


public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public boolean isSexeMasculin() {
	return sexeMasculin;
}

public void setSexeMasculin(boolean sexeMasculin) {
	this.sexeMasculin = sexeMasculin;
}
public String getTextSexe() {
	return TextSexe;
}

public void setTextSexe(String textSexe) {
	TextSexe = textSexe;
}
public String toString(){
	return identifiant+","+patientIsDel+","+nom+","+prenom+","+TextSexe+","+NSS+","+dateNaissance+","+dateEntree+","+dateSortie+","+telephone;
}
}

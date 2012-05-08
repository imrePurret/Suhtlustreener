
public class MotivationalSphere {
	private int resources; //res
	private int pleasant; //ple
	private int unpleasant; //unp
	private int useful; //use
	private int harmful; //har
	private int mandatory; //man
	private int penaltyNeg; //pen
	private int penaltyPos; //pep
	private int prohibited; //pro
	
	
	public MotivationalSphere(int resources, int pleasant, int unpleasant, int useful, int harmful, int mandatory, int penaltyNeg, int penaltyPos, int prohibited){
		this.resources = resources;
		this.pleasant = pleasant;
		this.unpleasant = unpleasant;
		this.useful = useful;
		this.harmful = harmful;
		this.mandatory = mandatory;
		this.penaltyNeg = penaltyNeg;
		this.penaltyPos = penaltyPos;
		this.prohibited = prohibited;
	}


	public MotivationalSphere() {
	}

	public void MotivationalSphereToNull(){
		this.resources = 0;
		this.pleasant = 0;
		this.unpleasant = 0;
		this.useful = 0;
		this.harmful = 0;
		this.mandatory = 0;
		this.penaltyNeg = 0;
		this.penaltyPos = 0;
		this.prohibited = 0;
	}

	public int getResources() {
		return resources;
	}


	public void setResources(int resources) {
		this.resources = resources;
	}


	public int getPleasant() {
		return pleasant;
	}


	public void setPleasant(int pleasant) {
		this.pleasant = pleasant;
	}


	public int getUnpleasant() {
		return unpleasant;
	}


	public void setUnpleasant(int unpleasant) {
		this.unpleasant = unpleasant;
	}


	public int getUseful() {
		return useful;
	}


	public void setUseful(int useful) {
		this.useful = useful;
	}


	public int getHarmful() {
		return harmful;
	}


	public void setHarmful(int harmful) {
		this.harmful = harmful;
	}


	public int getMandatory() {
		return mandatory;
	}


	public void setMandatory(int mandatory) {
		this.mandatory = mandatory;
	}



	public int getPenaltyNeg() {
		return penaltyNeg;
	}


	public void setPenaltyNeg(int penaltyNeg) {
		this.penaltyNeg = penaltyNeg;
	}


	public int getPenaltyPos() {
		return penaltyPos;
	}


	public void setPenaltyPos(int penaltyPos) {
		this.penaltyPos = penaltyPos;
	}


	public int getProhibited() {
		return prohibited;
	}


	public void setProhibited(int prohibited) {
		this.prohibited = prohibited;
	}

	@Override
	public String toString() {
		return "MotivationalSphere [resources=" + resources + ", pleasant="
				+ pleasant + ", unpleasant=" + unpleasant + ", useful="
				+ useful + ", harmful=" + harmful + ", mandatory=" + mandatory
				+ ", penaltyNeg=" + penaltyNeg + ", penaltyPos=" + penaltyPos
				+ ", prohibited=" + prohibited + "]";
	}

}

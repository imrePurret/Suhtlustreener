
public class MotivationalSphere {
	private int resources; //res
	private int pleasant; //ple
	private int unpleasant; //unp
	private int useful; //use
	private int harmful; //har
	private int mandatory; //man
	private int prohibited; //pro
	private int penalty; //pen
	
	
	public MotivationalSphere(int resources, int pleasant, int unpleasant, int useful, int harmful, int mandatory, int prohibited, int penalty){
		this.resources = resources;
		this.pleasant = pleasant;
		this.unpleasant = unpleasant;
		this.useful = useful;
		this.harmful = harmful;
		this.mandatory = mandatory;
		this.prohibited = prohibited;
		this.penalty = penalty;
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
		this.prohibited = 0;
		this.penalty = 0;
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


	public int getProhibited() {
		return prohibited;
	}


	public void setProhibited(int prohibited) {
		this.prohibited = prohibited;
	}


	public int getPenalty() {
		return penalty;
	}


	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}


	@Override
	public String toString() {
		return "motivationalSphere [resources=" + resources + ", pleasant="
				+ pleasant + ", unpleasant=" + unpleasant + ", useful="
				+ useful + ", harmful=" + harmful + ", mandatory=" + mandatory
				+ ", prohibited=" + prohibited + ", penalty=" + penalty + "]";
	}
}

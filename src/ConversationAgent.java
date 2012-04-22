import java.util.*;


public class ConversationAgent {
	MotivationalSphere motivationalSphere = new MotivationalSphere();
	
	public ConversationAgent() {
		
	}
	
	public void scales( String hinnatav ){
		List<Information> informationList =new InformationReader().getInformationList();
		List<Answer> answerList = new AnswerReader().getAnswerList();
		List<String> zeroAnswers = new ArrayList();
		for(Information information : informationList){
			int value = 0;
			if (hinnatav.matches(".*(^| )"+information.getKeyWord()+".{0,4}( |$).*")){
				value++;
				List<String> answers = new ArrayList();
				for(Answer answer : answerList){
					if(answer.getAnswerIndex()==information.getAnswerIndex()){
						answers.add(answer.getAnswer());
					}
				}
				answering(answers);
				System.out.println(answers.size());
				renewMotivationalSphere(information.getSphereClass());
			}
			for(Answer answer : answerList){
				if(answer.getAnswerIndex()==0){
					zeroAnswers.add(answer.getAnswer());
				}
			}
			if (value>0){
				System.out.println(motivationalSphere);
				break;
			}
		}
		if(zeroAnswers.size() > 0){
			answering(zeroAnswers);
		}

	}
	private void renewMotivationalSphere(String sphereClass) {
		if("res".equals(sphereClass)){
			motivationalSphere.setResources(1);
		}
		if("ple".equals(sphereClass)){
			motivationalSphere.setPleasant(1);
		}
		if("unp".equals(sphereClass)){
			motivationalSphere.setUnpleasant(1);
		}
		if("use".equals(sphereClass)){
			motivationalSphere.setUseful(1);
		}
		if("har".equals(sphereClass)){
			motivationalSphere.setHarmful(1);
		}
		if("man".equals(sphereClass)){
			motivationalSphere.setMandatory(1);
		}
		if("pro".equals(sphereClass)){
			motivationalSphere.setProhibited(1);
		}
		if("pen".equals(sphereClass)){
			motivationalSphere.setPenalty(1);
		}
		
	}

	private void answering(List<String> answers) {
		Random generator = new Random();
		int r = generator.nextInt(answers.size());
		mainClass.dialog.addTextToTextArea(answers.get(r));
	}

	// Pleasant is bigger than unpleasant.
	public boolean procedureWish(MotivationalSphere motSphere){
		if (motSphere.getResources()==0){
			return false;
		}
		if (motSphere.getPleasant()>motSphere.getUnpleasant()+motSphere.getHarmful()){
			if (motSphere.getProhibited()==0){
				return true;
			}
			else{
				if(motSphere.getPleasant()>motSphere.getUnpleasant()+motSphere.getHarmful()+motSphere.getPenalty()){
					return true;
				}
				if(motSphere.getPleasant()+motSphere.getUseful()>motSphere.getUnpleasant()+motSphere.getHarmful()+motSphere.getPenalty()){
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(motSphere.getPleasant()+motSphere.getUseful()>motSphere.getUnpleasant()+motSphere.getHarmful()){
				if(motSphere.getProhibited()==0){
					return true;
				}
				else{
					if(motSphere.getPleasant()+motSphere.getUseful()>motSphere.getUnpleasant()+motSphere.getHarmful()+motSphere.getPenalty()){
						return true;
					}
					else{
						return false;
					}
				}
			}
			else{
				if(motSphere.getMandatory()==0){
					return true;
				}
				else{
					if(motSphere.getPleasant()+motSphere.getUseful()+1-motSphere.getPenalty()>motSphere.getHarmful()+motSphere.getPenalty()){
						return true;
					}
					else{
						return false;
					}
				}
			}
		}
	}
	// Useful is bigger than harmful.
	public boolean procedureNeed(MotivationalSphere motShpere){
		if(motShpere.getResources()==0){
			return false;
		}
		if(motShpere.getPleasant()>motShpere.getUnpleasant()){
			if(motShpere.getProhibited()==0){
				return true;
			}
			if(motShpere.getPleasant()+motShpere.getUseful()>motShpere.getUnpleasant()+motShpere.getHarmful()+motShpere.getPenalty()){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			if(motShpere.getMandatory()==0){
				return true;
			}
			if(motShpere.getPleasant()+motShpere.getUseful()+1-motShpere.getPenalty()>motShpere.getUnpleasant()+motShpere.getHarmful()){
				return true;	
			}
			else{
				return false;
			}
		}	
	}
	// Activity is mandatory.
	public boolean procedureMust(MotivationalSphere motShpere){
		if(motShpere.getResources()==0){
			return false;
		}
		if(motShpere.getPleasant()>motShpere.getUnpleasant()){
			if(motShpere.getPleasant()+1-motShpere.getPenalty()>motShpere.getUnpleasant()+motShpere.getHarmful()){
				return true;
			}
			if(motShpere.getPleasant()+motShpere.getUseful()+1-motShpere.getPenalty()>motShpere.getUnpleasant()+motShpere.getHarmful()){
				return true;
			}
			else{
				return false;
			}
				
		}
		else{
			if(motShpere.getPleasant()+motShpere.getUseful()+1-motShpere.getPenalty()>motShpere.getUnpleasant()){
				return true;
			}
			if(motShpere.getPleasant()+motShpere.getUseful()+1-motShpere.getPenalty()>motShpere.getUnpleasant()+motShpere.getHarmful()){
				return true;
			}
			else{
				return false;
			}
		}
	}

}

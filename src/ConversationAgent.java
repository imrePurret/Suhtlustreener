import java.util.*;

import javax.swing.JOptionPane;

public class ConversationAgent {
	MotivationalSphere motivationalSphere = mainClass.motivationalSphere;

	public ConversationAgent() {

	}

	public void scales(String hinnatav) {
		List<Information> informationList = new InformationReader("informatsioon")
				.getInformationList();
		List<Answer> answerList = new AnswerReader("vastused").getAnswerList();
		List<String> zeroAnswers = new ArrayList<String>();
		List<String> winAnswers = new ArrayList<String>();
		int value = 0;
		for (Information information : informationList) {
			if (hinnatav.matches(".*(^| )" + information.getKeyWord()
					+ ".{0,3}( |$).*")) {
				value++;
				List<String> answers = new ArrayList<String>();
				for (Answer answer : answerList) {
					if (answer.getAnswerIndex() == information.getAnswerIndex()) {
						answers.add(answer.getAnswer());
					}
				}
				renewMotivationalSphere(information.getSphereClass());
				if (procedureWish(motivationalSphere)
						|| procedureNeed(motivationalSphere)
						|| procedureMust(motivationalSphere)) {
					for (Answer answer : answerList) {
						if (answer.getAnswerIndex() == 9999) {
							winAnswers.add(answer.getAnswer());
						}
					}
					break;
				}
				if (!answering(answers)) {
					value = 0;
				}
			}
			if (value > 0) {
				break;
			}
			for (Answer answer : answerList) {
				if (answer.getAnswerIndex() == 0) {
					zeroAnswers.add(answer.getAnswer());
				}
			}
		}
		if (zeroAnswers.size() > 0 && value == 0) {
			answering(zeroAnswers);
		}
		if (winAnswers.size() > 0) {
			answering(winAnswers);
			mainClass.dialog.getTextField().setEditable(false);
			JOptionPane
					.showMessageDialog(
							null,
							"Teil õnnestus arvutit veenda taimetoitlaseks hakkama!\n"
									+ "Uue dialoogi alustamiseks valige menüüst alustaja ja seejärel "
									+ "\"Üldine\" ja \"Uus\".", "Õnnitlus!", 1);
		}

	}

	public void tactic(String hinnatav) {
		List<Information> informationList = new InformationReader("taktikasisend")
				.getInformationList();
		System.out.println(mainClass.dialog.getTacticName());
		List<String> zeroAnswers = new ArrayList<String>();
		List<String> winAnswers = new ArrayList<String>();
		int value = 0;
		for (Information information : informationList) {
			if (hinnatav.matches(".*(^| )" + information.getKeyWord()
					+ ".{0,3}( |$).*")) {
				value++;
				List<String> answers = new ArrayList<String>();
				for (Answer answer : mainClass.dialog.getArgumentList()) {
					if (answer.getAnswerIndex() == information.getAnswerIndex()) {
						answers.add(answer.getAnswer());
					}
				}
				renewMotivationalSphere(information.getSphereClass());
				if (procedureWish(motivationalSphere)
						|| procedureNeed(motivationalSphere)
						|| procedureMust(motivationalSphere) || information.getAnswerIndex()==9999) {
					for (Answer answer : mainClass.dialog.getArgumentList()) {
						if (answer.getAnswerIndex() == 9999) {
							winAnswers.add(answer.getAnswer());
						}
					}
					break;
				}
				if (!answering(answers)) {
					value = 0;
				}
			}
			if (value > 0) {
				break;
			}
			for (Answer answer : mainClass.dialog.getArgumentList()) {
				if (answer.getAnswerIndex() == 0) {
					zeroAnswers.add(answer.getAnswer());
				}
			}
		}
		if (zeroAnswers.size() > 0 && value == 0) {
			answering(zeroAnswers);
		} else if(value == 0){
			mainClass.dialog.getTextField().setEditable(false);
			JOptionPane
					.showMessageDialog(
							null,
							"Dialoog Teie ja agendi vahel on lõppenud! Kahju, et Teid veenda ei õnnestunud!\n"
									+ "Uue dialoogi alustamiseks valige menüüst alustaja ja seejärel "
									+ "\"Üldine\" ja \"Uus\".", "Dialoogi lõpp", 1);
		}
		if (winAnswers.size() > 0) {
			answering(winAnswers);
			mainClass.dialog.getTextField().setEditable(false);
			JOptionPane
					.showMessageDialog(
							null,
							"Dialoog Teie ja agendi vahel on lõppenud! Tore, et otsustasite taimetoidu kasuks!\n"
									+ "Uue dialoogi alustamiseks valige menüüst alustaja ja seejärel "
									+ "\"Üldine\" ja \"Uus\".", "Dialoogi lõpp", 1);
		}

	}
	
	private void renewMotivationalSphere(String sphereClass) {
		if ("res".equals(sphereClass)) {
			motivationalSphere.setResources(1);
		}
		if ("ple".equals(sphereClass)) {
			motivationalSphere.setPleasant(1);
		}
		if ("unp".equals(sphereClass)) {
			motivationalSphere.setUnpleasant(1);
		}
		if ("use".equals(sphereClass)) {
			motivationalSphere.setUseful(1);
		}
		if ("har".equals(sphereClass)) {
			motivationalSphere.setHarmful(1);
		}
		if ("man".equals(sphereClass)) {
			motivationalSphere.setMandatory(1);
		}
		if ("pro".equals(sphereClass)) {
			motivationalSphere.setProhibited(1);
		}
		if ("pen".equals(sphereClass)) {
			motivationalSphere.setPenaltyNeg(1);
		}
		if ("pep".equals(sphereClass)) {
			motivationalSphere.setPenaltyPos(1);
		}

	}

	private boolean answering(List<String> answers) {
		if (answers.size() > 0) {
			Random generator = new Random();
			int r = generator.nextInt(answers.size());
			mainClass.dialog.addTextToTextArea(answers.get(r));
			if (mainClass.dialog.getArgumentList() != null) {
				for (Answer argument : mainClass.dialog.getArgumentList()) {
					if (argument.getAnswer().equals(answers.get(r))) {
						mainClass.dialog.getArgumentList().remove(argument);
						break;
					}
				}
			}
			return true;
		}
		return false;
	}
	// Pleasant is bigger than unpleasant.
	public boolean procedureWish(MotivationalSphere motSphere) {
		if (motSphere.getPleasant() > motSphere.getUnpleasant()) {
			if (motSphere.getResources() == 0) {
				return false;
			}
			if (motSphere.getPleasant() > motSphere.getUnpleasant()
					+ motSphere.getHarmful()) {
				if (motSphere.getProhibited() == 0) {
					return true;
				}
				if (motSphere.getPleasant() > motSphere.getUnpleasant()
						+ motSphere.getHarmful() + motSphere.getPenaltyNeg()) {
					return true;
				}
				if (motSphere.getPleasant() + motSphere.getUseful() > motSphere
						.getUnpleasant()
						+ motSphere.getHarmful()
						+ motSphere.getPenaltyNeg()) {
					return true;
				}
				return false;
			}
			if (motSphere.getPleasant() + motSphere.getUseful() > motSphere
					.getUnpleasant() + motSphere.getHarmful()) {
				if (motSphere.getProhibited() == 0) {
					return true;
				}
				if (motSphere.getPleasant() + motSphere.getUseful() > motSphere
						.getUnpleasant()
						+ motSphere.getHarmful()
						+ motSphere.getPenaltyNeg()) {
					return true;
				}
				return false;
			}
			if (motSphere.getMandatory() == 0) {
				return true;
			}
			if (motSphere.getPleasant() + motSphere.getUseful()
					+ motSphere.getPenaltyPos() > motSphere.getHarmful()
					+ motSphere.getUnpleasant()) {
				return true;
			}
			return false;
		}
		return false;
	}

	// Useful is bigger than harmful.
	public boolean procedureNeed(MotivationalSphere motShpere) {
		if (!(motShpere.getUseful() > motShpere.getHarmful())) {
			return false;
		}
		if (motShpere.getResources() == 0) {
			return false;
		}
		if (motShpere.getPleasant() > motShpere.getUnpleasant()) {
			if (motShpere.getProhibited() == 0) {
				return true;
			}
			if (motShpere.getPleasant() + motShpere.getUseful() > motShpere
					.getUnpleasant()
					+ motShpere.getHarmful()
					+ motShpere.getPenaltyNeg()) {
				return true;
			}
			return false;
		}
		if (motShpere.getMandatory() == 0) {
			return true;
		}
		if (motShpere.getPleasant() + motShpere.getUseful()
				+ motShpere.getPenaltyPos() > motShpere.getUnpleasant()
				+ motShpere.getHarmful()) {
			return true;
		}
		return false;
	}

	// Activity is mandatory.
	public boolean procedureMust(MotivationalSphere motShpere) {
		if (motShpere.getMandatory() == 0) {
			return false;
		}
		if (motShpere.getResources() == 0) {
			return false;
		}
		if (motShpere.getPleasant() > motShpere.getUnpleasant()) {
			if (motShpere.getPleasant() + motShpere.getPenaltyPos() > motShpere
					.getUnpleasant() + motShpere.getHarmful()) {
				return true;
			}
			if (motShpere.getPleasant() + motShpere.getUseful()
					+ motShpere.getPenaltyPos() > motShpere.getUnpleasant()
					+ motShpere.getHarmful()) {
				return true;
			}
			return false;

		}
		if (motShpere.getPleasant() + motShpere.getUseful()
				+ motShpere.getPenaltyPos() > motShpere.getUnpleasant()) {
			return true;
		}
		if (motShpere.getPleasant() + motShpere.getUseful()
				+ motShpere.getPenaltyPos() > motShpere.getUnpleasant()
				+ motShpere.getHarmful()) {
			return true;
		}
		return false;
	}

}
